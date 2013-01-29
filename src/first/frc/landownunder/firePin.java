/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Loughlan Mckendry <minimckendry@hotmail.com>
 * @since 2013-01-29 15:30 AEDT
 */
public class firePin
{
 
  Victor motor;
  DigitalInput max;
  DigitalInput min;
  
  private static double motorSpeed = 0.1;

  public firePin(Victor myMotor, DigitalInput myMax, DigitalInput myMin)
  {
    motor = myMotor;
    max = myMax;
    min = myMin;
    motor.set(0);
  }
  
  public void fire()
  {
    while (max.get() == false)
    {
        motor.set(motorSpeed);
    }
    motor.set(0);
    
  }
  public void reset()
  {
    while (min.get() == false)
    {
        motor.set(-motorSpeed);
    }
    motor.set(0);
    
  }

}
