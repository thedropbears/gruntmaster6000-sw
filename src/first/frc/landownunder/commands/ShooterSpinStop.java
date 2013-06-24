/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

/**
 *
 * @author Developer
 */
public class ShooterSpinStop extends CommandBase
{
    private boolean finnished;
  
  public ShooterSpinStop()
  {
    super("ShooterSpinStop");
    requires(shooter);
  } // public ShooterSpinSlow()
  
  public void initialize() {
  finnished = false;
          
  }
  public void execute()
  {
    shooter.stop();
    finnished = true;
  }
  public boolean isFinished()
  {
    return finnished;
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command