package first.frc.landownunder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.HiTechnicCompass;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import first.frc.landownunder.components.Tachometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
  // OI Control
  //// Joysticks
  public static final Joystick joyDrv = new Joystick(1);
  public static final Joystick joyOpr = new Joystick(2);
  
  /**
   * Driver joystick dead zone for x-axis. Default 0.05
   */
  public static double JOY_DRV_DEAD_X = (double) 0.05;
  
  /**
   * Driver joystick dead zone for y-axis. Default 0.05
   */
  public static double JOY_DRV_DEAD_Y = (double) 0.05;
  
  /**
   * Operator joystick dead zone for x-axis. Default 0.05
   */
  public static double JOY_OPR_DEAD_X = (double) 0.05;
  
  /**
   * Operator joystick dead zone for y-axis. Default 0.05
   */
  public static double JOY_OPR_DEAD_Y = (double) 0.05;
  
  
  ////Button Definitions
  public static final JoystickButton fireButton = new JoystickButton(joyOpr, 8);
  public static final JoystickButton resetButton = new JoystickButton(joyOpr, 7);
  
  // Actuator definitions
  //// Drive Train Motors
  public static final Victor driveMotorA = new Victor(1, 1);
  public static final Victor driveMotorB = new Victor(1, 2);
  public static final Victor driveMotorC = new Victor(1, 3);
  
  //// Shooter Motors
  public static final Victor firePinMotor  = new Victor(1, 4);
  public static final Victor shooterMotorFront = new Victor(1, 5);
  public static final Victor shooterMotorBack = new Victor(1, 6);  
  
  // Sensors
  //// Positioning
  public static final HiTechnicCompass compass = new HiTechnicCompass(1);
  public static final Ultrasonic sonarA = new Ultrasonic(1, 1, 1, 2);
  public static final Ultrasonic sonarB = new Ultrasonic(1, 3, 1, 4);
  public static final Ultrasonic sonarC = new Ultrasonic(1, 5, 1, 6);
  
  //// Firing Pin Limit Switches
  public static final DigitalInput firePinMax = new DigitalInput(7);
  public static final DigitalInput firePinMin = new DigitalInput(8);
  
  //// Shooter Tachometers
  public static final Tachometer tachoFront = new Tachometer( new DigitalInput(9), 6 );
  public static final Tachometer tachoBack = new Tachometer( new DigitalInput(10), 6 );
}
