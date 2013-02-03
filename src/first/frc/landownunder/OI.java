/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author carneeki
 */
public class OI
{
  private final Joystick joyDrv;
  private final Joystick joyOpr;
  
  public OI()
  {
    joyDrv = RobotMap.joyDrv;
    joyOpr = RobotMap.joyOpr;
  }
  
  /**
   * Get a Joystick object for Driver joystick.
   * @return Joystick
   */
  public Joystick getJoyDrv()
  {
    return joyDrv;
  }
  
  /**
   * Get a Jostick object for Operator joystick
   * @return Joystick
   */
  public Joystick getJoyOpr()
  {
    return joyOpr;
  }
  
  /**
   * Apply dead zone parameter to a given joystick value and return the value
   * xor zero.
   * @param joyVal value to test
   * @param deadVal dead zone constant from RobotMap
   * @return double
   */
  private double applyDeadZone(double joyVal, double deadVal)
  {
    if( (joyVal <= -deadVal) || (joyVal >= deadVal) )
    {
      return joyVal;
    }
    
    return 0;
  }
  
  /**
   * Get X value of driver Joystick with applied dead zone.
   * @return double
   */
  public double getJoyDrvX()
  {
    return applyDeadZone(joyDrv.getX(), RobotMap.JOY_DRV_DEAD_X);
  }
  
  /**
   * Get Y value of driver Joystick with applied dead zone.
   * @return double
   */
  public double getJoyDrvY()
  {
    return applyDeadZone(joyDrv.getY(), RobotMap.JOY_DRV_DEAD_Y);
  }
  
  /**
   * Get throttle value of driver joystick.
   * @return double
   */
  public double getJoyDrvThrottle()
  {
    return ( (1+joyDrv.getRawAxis(3)) );
  }
  
  /**
   * Get X value of operator Joystick with applied dead zone.
   * @return double
   */
  public double getJoyOprX()
  {
    return applyDeadZone(joyOpr.getX(), RobotMap.JOY_DRV_DEAD_X);
  }
  
  /**
   * Get Y value of operator Joystick with applied dead zone.
   * @return double
   */
  public double getJoyOprY()
  {
    return applyDeadZone(joyOpr.getY(), RobotMap.JOY_OPR_DEAD_Y);
  }
  
  /**
   * Get throttle value of operator joystick.
   * @return double
   */
  public double getJoyOprThrottle()
  {
    return ( (1+joyOpr.getRawAxis(3)) );
  }
}
