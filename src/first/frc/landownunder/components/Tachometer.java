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
  private int divider;
  
  public Tachometer(DigitalInput tachoPin, int divider)
  {
    super(tachoPin);
    this.divider = divider;
  }
  
  public Tachometer(DigitalInput tachoPin)
  {
    super(tachoPin);
    this.divider = 1;
  }
          
  /**
   * @return return output in RPM since last count
   */
  public double pidGet()
  {
    return 60/(divider * getPeriod());
  }
}
