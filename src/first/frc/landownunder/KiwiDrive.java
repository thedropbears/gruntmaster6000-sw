package first.frc.landownunder;
/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.parsing.IUtility;

/**
 * Utility class for handling Robot drive based Kiwi Drive. Motor channel
 * numbers are passed supplied on creation of the class. Those are used for
 * either the drive function (intended for hand created drive code, such as
 * autonomous) or with the Tank/Arcade functions intended to be used for
 * Operator Control driving.
 * 
 * @author Adam Carmichael <carneeki@carneeki.net>
 * @since 2013-01-23 15:41 AEDT
 * @basedon RobotDrive FRC2013
 */
public class KiwiDrive implements MotorSafety, IUtility
{

  protected MotorSafetyHelper m_safetyHelper;

  /**
   * The location of a motor on the robot for the purpose of driving
   */
  public static class MotorType
  {

    /**
     * The integer value representing this enumeration
     */
    public final int value;
    static final int kA_val = 0;
    static final int kB_val = 1;
    static final int kC_val = 2;
    static final int kRearRight_val = 3;
    
    /**
     * motor type: top motor
     */
    public static final MotorType kA = new MotorType(kA_val);
    /**
     * motor type: right motor
     */
    public static final MotorType kB = new MotorType(kB_val);
    /**
     * motor type: left motor
     */
    public static final MotorType kC = new MotorType(kC_val);

    private MotorType(int value)
    {
      this.value = value;
    }
  }
  
  public static final double kDefaultExpirationTime = 0.1;
  public static final double kDefaultSensitivity = 0.5;
  public static final double kDefaultMaxOutput = 1.0;
  protected static final int kMaxNumberOfMotors = 3;
  protected final int m_invertedMotors[] = new int[3];
  protected double m_sensitivity;
  protected double m_maxOutput;
  protected Victor m_AMotor;
  protected Victor m_BMotor;
  protected Victor m_CMotor;
  protected boolean m_allocatedSpeedControllers;
  protected boolean m_isCANInitialized = true;
  protected static boolean kArcadeRatioCurve_Reported = false;
  protected static boolean kTank_Reported = false;
  protected static boolean kArcadeStandard_Reported = false;

  /**
   * Constructor for RobotDrive with 3 motors specified with channel numbers.
   * Set up parameters for a three wheel omni drive system where the motor pwm
   * channels are specified in the call. This call assumes Jaguars for
   * controlling the motors.
   *
   * @param aMotorChannel The PWM channel number on the default digital
   * module that drives the top motor.
   * @param bMotorChannel The PWM channel number on the default digital
   * module that drives the right motor.
   * @param cMotorChannel The PWM channel number on the default digital
   * module that drives the left motor.
   */
  public KiwiDrive(final int aMotorChannel, final int bMotorChannel,
                      final int cMotorChannel)
  {
    m_sensitivity = kDefaultSensitivity;
    m_maxOutput = kDefaultMaxOutput;
    m_AMotor = new Victor(1,aMotorChannel);
    m_BMotor = new Victor(1,bMotorChannel);
    m_CMotor = new Victor(1,cMotorChannel);
    for (int i = 0; i < kMaxNumberOfMotors; i++)
    {
      m_invertedMotors[i] = 1;
    }
    m_allocatedSpeedControllers = true;
    setupMotorSafety();
    //drive(0,0,0,0);
  }

  /**
   * Drive the motors at "speed" and "curve".
   *
   * The speed and curve are -1.0 to +1.0 values where 0.0 represents stopped
   * and not turning. The algorithm for adding in the direction attempts to
   * provide a constant turn radius for differing speeds.
   *
   * This function will most likely be used in an autonomous routine.
   * 
   * @param vX vector of x-axis
   * @param vY vector of y-axis
   * @param vR vector of rotation
   * @param throttle throttle speed
   */
  public void drive(double vX, double vY, double vR, double throttle)
  {
    double vA, vB, vC = 0;
    // set MotorA
    vA = vX;
    vB = (-vX / 2) - (Math.sqrt(3) / 2 * vY);
    vC = (-vX / 2) + (Math.sqrt(3) / 2 * vY);
    
    vR = limit(vR);
    throttle = limit(throttle);
    
    vA = limit(vA * throttle);
    vB = limit(vB * throttle);
    vC = limit(vC * throttle);

    m_AMotor.set(vA);
    m_BMotor.set(-vB);
    m_CMotor.set(-vC);
  } // private void drive(double vX, double vY)
  /**
   * Limit motor values to the -1.0 to +1.0 range.
   */
  protected static double limit(double num)
  {
    if (num > 1.0)
    {
      return 1.0;
    }
    if (num < -1.0)
    {
      return -1.0;
    }
    return num;
  }

  /**
   * Normalize all wheel speeds if the magnitude of any wheel is greater than
   * 1.0.
   */
  protected static void normalize(double wheelSpeeds[])
  {
    double maxMagnitude = Math.abs(wheelSpeeds[0]);
    int i;
    for (i = 1; i < kMaxNumberOfMotors; i++)
    {
      double temp = Math.abs(wheelSpeeds[i]);
      if (maxMagnitude < temp)
      {
        maxMagnitude = temp;
      }
    }
    if (maxMagnitude > 1.0)
    {
      for (i = 0; i < kMaxNumberOfMotors; i++)
      {
        wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
      }
    }
  }

  /**
   * Rotate a vector in Cartesian space.
   */
  protected static double[] rotateVector(double x, double y, double angle)
  {
    double cosA = Math.cos(angle * (3.14159 / 180.0));
    double sinA = Math.sin(angle * (3.14159 / 180.0));
    double out[] = new double[2];
    out[0] = x * cosA - y * sinA;
    out[1] = x * sinA + y * cosA;
    return out;
  }

  /**
   * Invert a motor direction. This is used when a motor should run in the
   * opposite direction as the drive code would normally run it. Motors that are
   * direct drive would be inverted, the drive code assumes that the motors are
   * geared with one reversal.
   *
   * @param motor The motor index to invert.
   * @param isInverted True if the motor should be inverted when operated.
   */
  public void setInvertedMotor(MotorType motor, boolean isInverted)
  {
    m_invertedMotors[motor.value] = isInverted ? -1 : 1;
  }

  /**
   * Set the turning sensitivity.
   *
   * This only impacts the drive() entry-point.
   *
   * @param sensitivity Effectively sets the turning sensitivity (or turn radius
   * for a given value)
   */
  public void setSensitivity(double sensitivity)
  {
    m_sensitivity = sensitivity;
  }

  /**
   * Configure the scaling factor for using RobotDrive with motor controllers in
   * a mode other than PercentVbus.
   *
   * @param maxOutput Multiplied with the output percentage computed by the
   * drive functions.
   */
  public void setMaxOutput(double maxOutput)
  {
    m_maxOutput = maxOutput;
  }

  /**
   * Free the speed controllers if they were allocated locally
   */
  public void free()
  {
    if (m_allocatedSpeedControllers)
    {
      if (m_AMotor != null)
      {
        ((PWM) m_AMotor).free();
      }
      if (m_BMotor != null)
      {
        ((PWM) m_BMotor).free();
      }
      if (m_CMotor != null)
      {
        ((PWM) m_CMotor).free();
      }
    }
  }

  public void setExpiration(double timeout)
  {
    m_safetyHelper.setExpiration(timeout);
  }

  public double getExpiration()
  {
    return m_safetyHelper.getExpiration();
  }

  public boolean isAlive()
  {
    return m_safetyHelper.isAlive();
  }

  public boolean isSafetyEnabled()
  {
    return m_safetyHelper.isSafetyEnabled();
  }

  public void setSafetyEnabled(boolean enabled)
  {
    m_safetyHelper.setSafetyEnabled(enabled);
  }

  public String getDescription()
  {
    return "Robot Drive";
  }

  public void stopMotor()
  {
    if (m_AMotor != null)
    {
      m_AMotor.set(0.0);
    }
    if (m_BMotor != null)
    {
      m_BMotor.set(0.0);
    }
    if (m_CMotor != null)
    {
      m_CMotor.set(0.0);
    }
  }

  private void setupMotorSafety()
  {
    m_safetyHelper = new MotorSafetyHelper(this);
    m_safetyHelper.setExpiration(kDefaultExpirationTime);
    m_safetyHelper.setSafetyEnabled(true);
  }

  protected int getNumMotors()
  {
    int motors = 0;
    if (m_AMotor != null)
    {
      motors++;
    }
    if (m_BMotor != null)
    {
      motors++;
    }
    if (m_CMotor != null)
    {
      motors++;
    }
    return motors;
  }
}
