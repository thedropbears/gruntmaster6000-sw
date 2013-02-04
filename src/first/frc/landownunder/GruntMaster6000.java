/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package first.frc.landownunder;

import edu.wpi.first.wpilibj.Joystick;
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
public class GruntMaster6000 extends SimpleRobot
{
  /**
   * Driver joystick dead zone for x-axis. Default 0.05
   */
  static double JOY_DRV_DEAD_X = (double) 0.05;
  
  /**
   * Driver joystick dead zone for y-axis. Default 0.05
   */
  static double JOY_DRV_DEAD_Y = (double) 0.05;
  
  /**
   * Operator joystick dead zone for x-axis. Default 0.05
   */
  static double JOY_OPR_DEAD_X = (double) 0.05;
  
  /**
   * Operator joystick dead zone for y-axis. Default 0.05
   */
  static double JOY_OPR_DEAD_Y = (double) 0.05;
  
  /**
   * Delay for timer events. Default = 0.01 (100Hz)
   */
  static double TIMER_DELAY = (double) 0.01; 
  
  /**
   * RobotDrive.
   * TODO: extend robot drive to use 3 wheel holonomic
   */
  KiwiDrive kd;
  
  /**
   * Joystick for the driver.
   */
  Joystick joyDrv = new Joystick(1);
  
  /**
   * Joystick for the operator.
   */
  Joystick joyOpr = new Joystick(2);
  
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
  /*
  Victor motorA;
  Victor motorB;
  Victor motorC;
  */
  
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
    kd = new KiwiDrive(1,2,3);
    vA = 0;
    vB = 0;
    vC = 0;
    indexer  = new Victor(1, 4);
    shooterA = new Victor(1, 5);
    shooterB = new Victor(1, 6);
    climber  = new Victor(1, 7);
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
    kd.setSafetyEnabled(true);
    
    double drvThrottle;
    double oprThrottle;
    SmartDashboard.putNumber("Opr Throttle", 0.00);

    while (isOperatorControl() && isEnabled())
    {
      drvThrottle = ( (1+joyDrv.getRawAxis(3))/2 );
      // oprThrottle = ( (1+joyOpr.getRawAxis(3))/2 );
      // use KiwiDrive class for driving
      SmartDashboard.putNumber("Drv X", joyDrv.getX());
      SmartDashboard.putNumber("Drv Y", joyDrv.getY());
      SmartDashboard.putNumber("Drv Throttle", drvThrottle);
      
      SmartDashboard.putNumber("Opr X", joyOpr.getX());
      SmartDashboard.putNumber("Opr Y", joyOpr.getY());
      oprThrottle = SmartDashboard.getNumber("Opr Throttle",0.00);
      
      
      kd.drive(joyDrv.getX(), joyDrv.getY(), 0, drvThrottle );
      
      // TODO: change me to start / stop for real code. getThrottle() just for
      // bench testing.
      shooterA.set(-oprThrottle);
      shooterB.set(-oprThrottle);

      // DO NOT PLACE ANYTHING AFTER THIS LINE IN operatorControl() !!
      Timer.delay(TIMER_DELAY);
    } // while (isOperatorControl() && isEnabled())
  } // public void operatorControl()

  /**
   * This function is called once each time the robot enters test mode.
   */
  public void test()
  {
    // TODO: implement me
  } // public void test()
} // public class RobotTemplate extends SimpleRobot
