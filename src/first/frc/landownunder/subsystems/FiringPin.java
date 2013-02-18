/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import first.frc.landownunder.RobotMap;

/**
 * @author Loughlan Mckendry <minimckendry@hotmail.com>
 * @since 2013-01-29 15:30 AEDT
 */
public class FiringPin extends Subsystem
{
  private final Victor motor;
  private final DigitalInput max;
  private final DigitalInput min;
  
  private final double motorSpeed;

  public FiringPin()
  {
    motor = RobotMap.firePinMotor;
    max = RobotMap.firePinMax;
    min = RobotMap.firePinMin;
    motorSpeed = Preferences.getInstance().getDouble("firePinSpeed", 0.1);
    
    motor.disable();
  }
  
  public void fire()
  {
    motor.set(motorSpeed);
  }
  
  /**
   * Return state of the maximum limit switch.
   * @return boolean
   */
  public boolean isFired()
  {
    return max.get();
  }
  
  /**
   * Return state of the minimum limit switch.
   * @return boolean
   */
  public boolean isReset()
  {
    return min.get();
  }

  /**
   * Reverse firing pin motor.
   */
  public void reset()
  {
    motor.set(-motorSpeed);
  }
  
  /**
   * Stop firing pin motor.
   */
  public void stop()
  {
    motor.disable();
  }

  protected void initDefaultCommand() { }

}
