/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package first.frc.landownunder;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import first.frc.landownunder.commands.CommandBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class GruntMaster6000 extends IterativeRobot
{  
  Command autonomousCommand = null;
  
  /**
   * Robot initialization
   */
  public void robotInit()
  {
    CommandBase.init();
  }
  
  /**
   * This function is called once each time robot enter autonomous.
   */
  public void autonomousInit()
  {
    // schedule the autonomous command
  }
  
  /**
   * This function is called periodically during autonomous.
   */
  public void autonomousPeriod()
  {
    Scheduler.getInstance().run();
  }
  
  /**
   * This function is called once each time the robot enters operator control.
   */
  public void teleopInit()
  {
    // Ensure autonomous command is cancelled
    if(autonomousCommand != null)
    {
      autonomousCommand.cancel();
    }
  }
  
  /**
   * This function is called periodically during operator control.
   */
  public void teleopPeriodic()
  {
    Scheduler.getInstance().run();
  } // public void operatorControl()

} // public class RobotTemplate extends IterativeRobot
