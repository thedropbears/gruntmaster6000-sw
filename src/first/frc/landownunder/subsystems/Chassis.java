package first.frc.landownunder.subsystems;
/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008-2012. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import first.frc.landownunder.RobotMap;
import first.frc.landownunder.commands.OperatorArcadeDrive;

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
public class Chassis extends Subsystem
{
  //// Drive Train Motors
  public final Victor driveMotorA = new Victor(1);
  public final Victor driveMotorB = new Victor(2);
  public final Victor driveMotorC = new Victor(3);

  protected void initDefaultCommand()
  {
    setDefaultCommand( new OperatorArcadeDrive() );
  }


  /**
   * Constructor for RobotDrive with 3 motors specified with channel numbers.
   * Set up parameters for a three wheel omni drive system where the motor pwm
   * channels are specified in the call.
   */
  public Chassis()
  {
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
    
    double vA, vB, vC;

    
    vA = vX;
    vB = (-vX / 2) - (Math.sqrt(3) / 2 * vY);
    vC = (-vX / 2) + (Math.sqrt(3) / 2 * vY);
    
    vR = limit(vR);
    throttle = limit(throttle);
    
    vA = limit( (vR + vA) * throttle);
    vB = limit( (vR + vB) * throttle);
    vC = limit( (vR + vC) * throttle);

    System.out.println("vA,vB,vC = " + vA + "," + vB + "," + vC);
    driveMotorA.set(vA);
    driveMotorB.set(-vB);
    driveMotorC.set(-vC);
  } // public void drive(double vX, double vY, double vR, double throttle)
  
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
}
