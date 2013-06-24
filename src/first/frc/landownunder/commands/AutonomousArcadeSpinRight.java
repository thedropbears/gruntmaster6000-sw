package first.frc.landownunder.commands;

import edu.wpi.first.wpilibj.Preferences;

/**
 *
 * @author carneeki
 */
public class AutonomousArcadeSpinRight extends CommandBase
{
  private final double waitTime;
  
  public AutonomousArcadeSpinRight()
  {
    requires(chassis);
    waitTime     = Preferences.getInstance().getDouble("AUTONOMOUS_WAIT_TIME", 0.5);
  }

  protected void initialize() { 
  }

  protected void execute()
  {
    chassis.drive(0, 1, 0, 1 );
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
