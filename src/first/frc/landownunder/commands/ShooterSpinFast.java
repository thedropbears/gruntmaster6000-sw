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
public class ShooterSpinFast extends Command
{
  Shooter shooter = new Shooter();
  
  public ShooterSpinFast()
  {
    super("ShooterSpinFast");
    requires(shooter);
  } // public ShooterSpinup()
  
  public void initialize() {}
  public void execute()
  {
    shooter.fast();
  }
  public boolean isFinished()
  {
    return false;
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command