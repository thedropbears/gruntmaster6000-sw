/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

import edu.wpi.first.wpilibj.command.Command;
import first.frc.landownunder.subsystems.Shooter;

/**
 *
 * @author carneeki
 */
public class ShooterSpinup extends Command
{
  Shooter shooter = new Shooter();
  
  public ShooterSpinup()
  {
    super("ShooterSpinUp");
    requires(shooter);
  }
  
  public void initialize() {}
  public void execute() {}
  public boolean isFinished()
  {
    return shooter.atSetpoint();
  }

  protected void end() {}

  protected void interrupted() {}
}
