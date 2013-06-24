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
public class ShooterSpinFast extends CommandBase
{
    private boolean finnished;
  
  public ShooterSpinFast()
  {
    super("ShooterSpinFast");
    requires(shooter);
  } // public ShooterSpinup()
  
  public void initialize() {
  finnished = false;
  }
  public void execute()
  {
    shooter.fast();
    finnished = true;
  }
  public boolean isFinished()
  {
    return finnished;
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command