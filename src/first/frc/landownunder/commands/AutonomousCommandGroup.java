/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author carneeki/McKendry
 */
public class AutonomousCommandGroup extends CommandGroup
{
  private final int frisbeeCount;
  
  private final double waitTime;
  private final double driveWaitTime;
  private boolean isFinished = false;
  
  public AutonomousCommandGroup()
  {
    requires(CommandBase.chassis);
    requires(CommandBase.shooter);
    requires(CommandBase.firingPin);
    frisbeeCount = Preferences.getInstance().getInt("AUTONOMOUS_FRISBEE_COUNT", 3);
    waitTime     = Preferences.getInstance().getDouble("AUTONOMOUS_WAIT_TIME", 1);
    driveWaitTime= Preferences.getInstance().getDouble("AUTONOMOUS_DRIVE_WAIT_TIME", 2);
    // Spin up shooter
    addSequential(new ShooterSpinFast());
    
    
      addSequential(new WaitCommand(waitTime));
      addSequential(new ShooterFire());
      addSequential(new OpenBottomServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new CloseBottomServo());
      addSequential(new OpenTopServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new CloseTopServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new ShooterFire());
      addSequential(new OpenBottomServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new CloseBottomServo());
      addSequential(new OpenTopServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new CloseTopServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new ShooterFire());
      addSequential(new OpenBottomServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new CloseBottomServo());
      addSequential(new OpenTopServo());
      addSequential(new WaitCommand(waitTime));
      addSequential(new CloseTopServo());
 
    
    
    // move to somewhere smart on the field
    addSequential(new AutonomousArcadeDriveForward());
    addSequential(new WaitCommand(driveWaitTime));
    addSequential(new AutonomousArcadeDriveStop());
    addSequential(new AutonomousArcadeSpinRight());
    addSequential(new WaitCommand(driveWaitTime));
    addSequential(new AutonomousArcadeDriveStop());
    
    
    // set our finished boolean to true...
    isFinished = true;
  }
  
  public void initialize() {}
  public void execute() {}
  public boolean isFinished() {
    return isFinished;
  } // public boolean isFinished()
} // public class AutonomousCommandGroup extends CommandGroup
