/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

/**
 *
 * @author Developer
 */
public class CloseBottomServo extends CommandBase
{
    private boolean finnished;
  
  public CloseBottomServo()
  {
    super("CloseBottomServo");
    requires(bottomServo);
    setTimeout(2);
  } // public ShooterSpinup()
  
  public void initialize() {
  finnished = false;
  }
  public void execute()
  {
    bottomServo.close();
    finnished = true;
  }
  public boolean isFinished()
  {
    return finnished || isTimedOut();
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command
