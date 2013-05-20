/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import first.frc.landownunder.commands.CloseBottomServo;
import first.frc.landownunder.commands.CloseTopServo;
import first.frc.landownunder.commands.OpenBottomServo;
import first.frc.landownunder.commands.OpenTopServo;
import first.frc.landownunder.commands.ShooterFire;
import first.frc.landownunder.commands.ShooterSpinFast;
import first.frc.landownunder.commands.ShooterSpinSlow;
import first.frc.landownunder.commands.ShooterSpinStop;

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
    RobotMap.fastButton.whenPressed(new ShooterSpinFast());
    RobotMap.slowButton.whenPressed(new ShooterSpinSlow());
    RobotMap.fireButton.whenPressed(new ShooterFire());
    RobotMap.stopButton.whenPressed(new ShooterSpinStop());
    RobotMap.topButton.whenActive(new OpenTopServo());
    RobotMap.topButton.whenInactive(new CloseTopServo());
    RobotMap.bottomButton.whenActive(new OpenBottomServo());
    RobotMap.bottomButton.whenInactive(new CloseBottomServo());
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
    return ( (1+joyDrv.getRawAxis(3)) / 2 );
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
    return ( (1+joyOpr.getRawAxis(3)) / 2 );
  }
}
