package first.frc.landownunder.commands;

import first.frc.landownunder.RobotMap;

/**
 *
 * @author carneeki
 */
public class OperatorArcadeDrive extends CommandBase
{
  public OperatorArcadeDrive()
  {
    requires(chassis);
  }

  protected void initialize() { 
  }

  protected void execute()
  {
    System.out.println("Drive " + oi.getJoyDrvX() + "," + oi.getJoyDrvY() + "," + oi.getJoyDrvThrottle());
    chassis.drive(oi.getJoyDrvX(), oi.getJoyDrvY(), 0, oi.getJoyDrvThrottle() );
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
