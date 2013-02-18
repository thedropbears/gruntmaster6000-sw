/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package first.frc.landownunder;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class GruntMaster6000 extends IterativeRobot
{
  /**
   * RobotDrive.
   * TODO: extend robot drive to use 3 wheel holonomic
   */
  KiwiDrive kd;
  
  /**
   * Joystick for the driver.
   */
  Joystick joyDrv = new Joystick(1);
  double drvThrottle;
  
  /**
   * Joystick for the operator.
   */
  Joystick joyOpr = new Joystick(2);
  double oprThrottle;
  
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
  
  Victor shooterFront;
  Victor shooterBack;
  Victor fpMotor;
  
  /*
   * firing pin limit switches
   */
  DigitalInput fpMin;
  DigitalInput fpMax;
  
  /**
   * Robot initialization
   */
  public void robotInit()
  {
    kd = new KiwiDrive(1,2,3);
    kd.setSafetyEnabled(false);
    
    fpMotor  = new Victor(1, 4);
    fpMax = new DigitalInput(1,1);
    fpMin = new DigitalInput(1,2);
    fpMotor.setSafetyEnabled(false);
    
    
    shooterFront = new Victor(1, 5);
    shooterBack = new Victor(1, 6);
    shooterFront.setSafetyEnabled(false);
    shooterBack.setSafetyEnabled(false);
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
   * This function is called once operator control period starts
   */
  public void teleopInit()
  {
    SmartDashboard.putNumber("motorA", kd.m_AMotor.get());
    SmartDashboard.putNumber("motorB", kd.m_BMotor.get());
    SmartDashboard.putNumber("motorC", kd.m_CMotor.get());
    
    SmartDashboard.putNumber("shooterFront", shooterFront.get());
    SmartDashboard.putNumber("shooterBack",  shooterBack.get());
    
    SmartDashboard.putNumber("fpMotor", fpMotor.get());
    SmartDashboard.putBoolean("fpMin", fpMin.get());
    SmartDashboard.putBoolean("fpMax", fpMax.get());
  }

  /**
   * This function is called once each time the robot enters operator control.
   */
  public void teleopPeriodic()
  {   
    drvThrottle = ( (1+joyDrv.getRawAxis(3))/2 );
    //oprThrottle = -roundToPercent( (1+joyOpr.getRawAxis(3)/2) );
    oprThrottle = 0.45 * -1;
    
    kd.drive(joyDrv.getX(), joyDrv.getY(), 0, drvThrottle );
    
    shooterFront.set(oprThrottle);
    shooterBack.set(oprThrottle);
    
  } // public void teleopContinuous()

  /**
   * This function is called once each time the robot enters test mode.
   */
  public void testInit()
  {
    LiveWindow.addActuator("drive", "motorA", kd.m_AMotor);
    LiveWindow.addActuator("drive", "motorB", kd.m_BMotor);
    LiveWindow.addActuator("drive", "motorC", kd.m_CMotor);
    
    LiveWindow.addActuator("shooter", "shooterFront", shooterFront);
    LiveWindow.addActuator("shooter", "shooterBack",  shooterBack);
    
    LiveWindow.addActuator("firingPin", "fpMotor", fpMotor);
    LiveWindow.addSensor("firingPin", "fpMin", fpMin);
    LiveWindow.addSensor("firingPin", "fpMax", fpMax);
    // TODO: implement me
  } // public void test()
  
  public void disabledInit()
  {
      kd.setSafetyEnabled(false);
      fpMotor.setSafetyEnabled(false);
      shooterFront.setSafetyEnabled(false);
      shooterBack.setSafetyEnabled(false);
  }
  
  /**
   * rounds a double from -1 to 1 to the nearest p percent. Useful for drive
   * SpeedControllers etc
   * @param input
   * @return 
   */
  private double roundToPercent(double input)
  {
    int p = 10;
    return MathUtils.round(input/p)*p;
  }
} // public class RobotTemplate extends SimpleRobot
