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
public class ShooterSpinSlow extends Command
{
  Shooter shooter = new Shooter();
  
  public ShooterSpinSlow()
  {
    super("ShooterSpinSlow");
    requires(shooter);
  } // public ShooterSpinSlow()
  
  public void initialize() {}
  public void execute()
  {
    shooter.slow();
  }
  public boolean isFinished()
  {
    return false;
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command