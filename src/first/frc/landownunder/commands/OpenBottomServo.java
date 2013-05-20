/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

/**
 *
 * @author Developer
 */
public class OpenBottomServo extends CommandBase
{
    private boolean finnished;
  
  public OpenBottomServo()
  {
    super("OpenBottomServo");
    requires(bottomServo);
  } // public ShooterSpinup()
  
  public void initialize() {
  finnished = false;
  }
  public void execute()
  {
    bottomServo.open();
    finnished = true;
  }
  public boolean isFinished()
  {
    return finnished;
  } // public boolean isFinished()

  protected void end() {}

  protected void interrupted() {}
} // public class ShooterSpinup extends Command