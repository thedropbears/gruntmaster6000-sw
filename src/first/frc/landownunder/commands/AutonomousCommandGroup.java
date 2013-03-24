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
 * @author carneeki
 */
public class AutonomousCommandGroup extends CommandGroup
{
  private final int frisbeeCount;
  
  private final double waitTime;
  
  private boolean isFinished = false;
  
  public AutonomousCommandGroup()
  {
    requires(CommandBase.chassis);
    requires(CommandBase.shooter);
    requires(CommandBase.firingPin);
    frisbeeCount = Preferences.getInstance().getInt("AUTONOMOUS_FRISBEE_COUNT", 3);
    waitTime     = Preferences.getInstance().getDouble("AUTONOMOUS_WAIT_TIME", 0.5);
    
    // Spin up shooter
    addSequential(new ShooterSpinFast());
    
    for(int i=1; i <= frisbeeCount; i++)
    {
      // wait the preset amount of time before firing
      addSequential(new WaitCommand(waitTime));
      addSequential(new ShooterFire());
    }
    
    // move to somewhere smart on the field
    
    // set our finished boolean to true...
    isFinished = true;
  }
  
  public void initialize() {}
  public void execute() {}
  public boolean isFinished() {
    return isFinished;
  } // public boolean isFinished()
} // public class AutonomousCommandGroup extends CommandGroup
