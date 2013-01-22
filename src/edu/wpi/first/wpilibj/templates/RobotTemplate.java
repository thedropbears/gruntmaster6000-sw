/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot
{
  /**
   * Driver joystick deadzone for x-axis. Default 0.05
   */
  static double JOY_DRV_DEAD_X = (double) 0.05;
  
  /**
   * Driver joystick deadzone for y-axis. Default 0.05
   */
  static double JOY_DRV_DEAD_Y = (double) 0.05;
  
  /**
   * Operator joystick deadzone for x-axis. Default 0.05
   */
  static double JOY_OPR_DEAD_X = (double) 0.05;
  
  /**
   * Operator joystick deadzone for y-axis. Default 0.05
   */
  static double JOY_OPR_DEAD_Y = (double) 0.05;
  
  /**
   * Delay for timer events. Default = 0.01 (100Hz)
   */
  static double TIMER_DELAY = (double) 0.01; 
  
  /**
   * Maximum speed of VIC. Default = 1.00
   */
  static double VIC_SPEED_MAX = (double) 1.00;
  
  /**
   * Minimum speed of VIC. Default = 0.05
   */
  static double VIC_SPEED_MIN = (double) 0.05;
  
  /**
   * Stop speed of VIC. Default = 0
   */
  static double VIC_SPEED_ZERO = (double) 0;
  
  /**
   * RobotDrive.
   * TODO: extend robot drive to use 3 wheel holonomic
   */
  RobotDrive rdDrive;
  
  /**
   * Joystick for the driver.
   */
  Joystick joystickDriver;
  
  /**
   * Joystick for the operator.
   */
  Joystick joystickOperator;
  
  /**
   * Velocity for motors A, B, C. Valid velocity range is between -1.00 and 1.00
   * 
   * Motor positions: 
   *          A
   *          | = vX
   *          .
   * 
   *      C       B
   *      |       | = -vX/2 - sqrt(3)/2 * vY
   *      |
   *      | = -vX/2 + sqrt(3)/2 * vY
   * 
   */
  double vA, vB, vC; // velocity for motors A, B, C
  
  /**
   * Motor controller objects for A, B, C drive motors.
   */
  Victor motorA;
  Victor motorB;
  Victor motorC;
  
  /**
   * Motor controller objects for rest of robot:
   */
  Victor indexer;
  Victor shooterA;
  Victor shooterB;
  Victor climber;
  
  
  /**
   * Robot initialization
   */
  public void robotInit()
  {
    rdDrive = new RobotDrive(9, 10);
    joystickDriver = new Joystick(1);
    joystickOperator = new Joystick(2);
    vA = 0;
    vB = 0;
    vC = 0;
    motorA = new Victor(1, 1);
    motorB = new Victor(1, 2);
    motorC = new Victor(1, 3);
    indexer = new Victor(1, 4);
    shooterA = new Victor(1, 5);
    shooterB = new Victor(1 ,6);
    climber = new Victor(1,7);
  }

  
  //////////////////////////////////////////////////////////////////////////////
  //
  // Place methods here
  //  
  //////////////////////////////////////////////////////////////////////////////
  
  /**
   * This function is called once each time the robot enters autonomous mode.
   */
  public void autonomous()
  {
    // TODO: implement me
  }

  /**
   * This function is called once each time the robot enters operator control.
   */
  public void operatorControl()
  {
    rdDrive.setSafetyEnabled(true);

    while (isOperatorControl() && isEnabled())
    {
      drive(joyDrvX(), joyDrvY()); // drive with 
      
      // DO NOT PLACE ANYTHING AFTER THIS LINE IN operatorControl() !!
      Timer.delay(TIMER_DELAY);
    } // while (isOperatorControl() && isEnabled())
  } // public void operatorControl()

  /**
   * This function is called once each time the robot enters test mode.
   */
  public void test()
  {
  } // public void test()

  /**
   * control motor drive train concept based on Vector_Drive from CMU
   * implementation at http://www.cs.cmu.edu/~pprk/Download/robot.cpp and
   * http://www.rjmcnamara.com/VIC_SPEED_MIN12/06/holonomic-platforms-robotc/
   *
   * @param vX x position of joystick
   * @param vY y pos of joystick
   * @author Adam Carmichael <carneeki@carneeki.net>
   * @since VIC_SPEED_MIN13-01-19 12:30AEDT
   */
  private void drive(double vX, double vY)
  {
    SmartDashboard.putNumber("DRV JOY X", vX);
    SmartDashboard.putNumber("DRV JOY Y", vY);
    // set MotorA
    vA = vX;
    vB = (-vX / 2) - (Math.sqrt(3) / 2 * vY);
    vC = (-vX / 2) + (Math.sqrt(3) / 2 * vY);

    // do deadzone corrections
    vA = victorCorrection(vA);
    vB = victorCorrection(vB);
    vC = victorCorrection(vC);

    motorA.set(vA);
    SmartDashboard.putNumber("Motor A", vA);
    
    motorB.set(vB);
    SmartDashboard.putNumber("Motor B", vB);
    
    motorC.set(vC);
    SmartDashboard.putNumber("Motor C", vC);
    
  } // private void drive(double vX, double vY)

  /**
   * correct the values sent to VIC such that deadzones and speed boundaries are
   * respected
   *
   * @author Adam Carmichael <carneeki@carneeki.net>
   * @since 2013-01-19 18:35 AEDT
   * @param vN double speed
   * @return double corrected value
   */
  private double victorCorrection(double speed)
  {
    // in deadzone > 0
    if ((speed > VIC_SPEED_ZERO) && (speed < VIC_SPEED_MIN))
    {
      return VIC_SPEED_ZERO;
    }

    // in deadzone < 0
    if ((speed > -VIC_SPEED_MIN) && (speed < VIC_SPEED_ZERO))
    {
      return VIC_SPEED_ZERO;
    }

    // minimum boundary exceeded
    if (speed < -VIC_SPEED_MAX)
    {
      return -VIC_SPEED_MAX;
    }

    // maximum boundary exceeded
    if (speed > VIC_SPEED_MAX)
    {
      return VIC_SPEED_MAX;
    }
    
    return speed;
  } // private double victorCorrection(double speed)
  
  /**
   * Driver Joystick Deadzone correction X axis
   */
  public double joyDrvX()
  {
    // joystick deadzone for driver X
    if( (Math.abs(joystickDriver.getX()) - JOY_DRV_DEAD_X ) < 0 )
    {
      return 0;
    }

    return joystickDriver.getX();
  }
  
  
  /**
   * Driver Joystick Deadzone correction Y axis
   */
  public double joyDrvY()
  {
    // joystick deadzone for driver Y
    if( (Math.abs(joystickDriver.getY()) - JOY_DRV_DEAD_Y ) < 0 )
    {
      return 0;
    }

    return joystickDriver.getY();
  }
  
} // public class RobotTemplate extends SimpleRobot