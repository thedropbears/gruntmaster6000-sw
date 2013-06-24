package first.frc.landownunder.commands;

import edu.wpi.first.wpilibj.Preferences;
import first.frc.landownunder.RobotMap;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author carneeki
 */
public class AutonomousArcadeDriveStop extends CommandBase
{
  private final double waitTime;
  
  public AutonomousArcadeDriveStop()
  {
    requires(chassis);
    waitTime     = Preferences.getInstance().getDouble("AUTONOMOUS_WAIT_TIME", 0.5);

  }

  protected void initialize() { 
  }

  protected void execute()
  {
    chassis.drive(0, 0, 0, 0 );
  }
  
  protected boolean isFinished()
  {
    return false;
  }

  protected void end() { }

  protected void interrupted()
  {
    end();
  }
}
