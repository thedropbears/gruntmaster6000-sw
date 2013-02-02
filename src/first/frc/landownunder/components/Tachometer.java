/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.components;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * A tachometer using DigitalInput high pulses.
 * @author carneeki
 * @since 2012-02-03 05:05AEDT
 */
public class Tachometer extends Counter implements PIDSource
{
  public Tachometer(DigitalInput tacho)
  {
    super(tacho);
  }
          
  /**
   * @return return output in RPM since last count
   */
  public double pidGet()
  {
    return 60/getPeriod();
  }
}
