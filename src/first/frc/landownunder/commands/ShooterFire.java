/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

/**
 *
 * @author carneeki
 */
public class ShooterFire extends CommandBase
{  
  public ShooterFire()
  {
    requires(shooter);
    requires(firingPin);
  }
  
  protected void initialize() { }

  protected void execute()
  {
    firingPin.fire();
  }

  protected boolean isFinished()
  {
    return firingPin.isFired();
  }

  protected void end()
  {
    firingPin.stop();
  }

  protected void interrupted()
  {
    end();
  }
}
