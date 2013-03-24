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
    requires(firingPin);
  }

  protected void initialize() { }

  protected void execute()
  {
    chassis.drive(oi.getJoyDrvX(), oi.getJoyDrvY(), 0, oi.getJoyDrvThrottle() );
    RobotMap.noneTopServo.whenPressed(this){
            firingPin.opentopservo();
    }
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
