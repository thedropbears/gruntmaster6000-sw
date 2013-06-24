/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

/**
 *
 * @author Developer
 */
public class CloseTopServo extends CommandBase
{
    private boolean finnished;
  
  public CloseTopServo()
  {
    super("CloseTopServo");
    requires(topServo);
  } // public ShooterSpinup()
  
  public void initialize() {
  finnished = false;
  }
  public void execute()
  {
    topServo.close();
    finnished = true;
  }
  public boolean isFinished()
  {
    return finnished;
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command
