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
  private boolean isfiring;
  private boolean isreseting;

  public ShooterFire()
  {
    requires(firingPin);
  }
  
  protected void initialize() {
      System.out.println("ShooterFire:Initiliaze" +
              " isFired = " + firingPin.isFired() +
              ", isReset = " + firingPin.isReset());
      isfiring = true;
      isreseting = false;
      setTimeout(5);
  }

  protected void execute()
  {
    System.out.println("ShooterFire:Execute" +
            " isFired = " + firingPin.isFired() +
            ", isReset = " + firingPin.isReset());
    if (isfiring){
        if (firingPin.isFired()){
            System.out.println("ShooterFire:Reset");
            isfiring = false;
            isreseting = true;
        } else {
            firingPin.fire();          
        }
    } else if (isreseting){
        if (firingPin.isReset()){
            System.out.println("ShooterFire:Done");
            isreseting = false;
        } else {
            firingPin.reset();
        }
    }
  }

  protected boolean isFinished()
  {
    return ((isfiring == false) && (isreseting == false))
        || isTimedOut();
  }

  protected void end()
  {
      System.out.println("ShooterFire:End timeout=" + isTimedOut());
      firingPin.stop();
  }

  protected void interrupted()
  {
      System.out.println("ShooterFire:Interrupted");
      firingPin.stop();
  }
}
