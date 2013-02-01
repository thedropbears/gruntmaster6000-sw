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
 * 
 * firePin creates new firePin class
 */
public class firePin
{
 
  Victor motor;
  DigitalInput max;
  DigitalInput min;
  
  private static double motorSpeed = 0.1;

  /**
   * 
   * @param myMotor the motor that runs the firing pin
   * @param myMax a limit switch that stops the motor from turning too far forward
   * @param myMin a limit switch that stops the motor from turning too far backward
   */
  public firePin(Victor myMotor, DigitalInput myMax, DigitalInput myMin)
  {
    motor = myMotor;
    max = myMax;
    min = myMin;
    motor.set(0);
  }
  /**
   * turns the motor moving the firing pin forward stopping when i reaches the max limit switch
   */
  public void fire()
  {
    while (max.get() == false)
    {
        motor.set(motorSpeed);
    }
    motor.set(0);
    
  }
  
  /**
   * turns the motor moving the firing pin backward stopping when i reaches the min limit switch
   */
  public void reset()
  {
    while (min.get() == false)
    {
        motor.set(-motorSpeed);
    }
    motor.set(0);
    
  }

}
