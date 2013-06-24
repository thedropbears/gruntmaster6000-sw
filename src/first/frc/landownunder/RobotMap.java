package first.frc.landownunder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
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
    //servo angles
    public static double OPEN_SERVO_ANGLE = (double) 120;
    public static double CLOSE_SERVO_ANGLE = (double) 0;
    public static double OPEN_BOTTOMSERVO_ANGLE = (double) 150;
    public static double CLOSE_BOTTOMSERVO_ANGLE = (double) 30;
    public static double FIRE_PIN_SPEED = (double) -0.5;
    //TODO check actual angle!!!
    public static double DRAWBRIDGE_SERVO_ANGLE = (double) 0;
    
    ////Button Definitions
    //public static final Button resetButton = new JoystickButton(joyDrv, 7);
    public static final Button fireButton = new JoystickButton(joyOpr, 6);
    public static final Button slowButton = new JoystickButton(joyOpr, 7);
    public static final Button fastButton = new JoystickButton(joyOpr, 8);
    public static final Button stopButton = new JoystickButton(joyOpr, 1);
    public static final Button topButton = new JoystickButton(joyOpr, 4);
    public static final Button bottomButton = new JoystickButton(joyOpr, 2);
    public static final Button drawBridgeButton = new JoystickButton(joyOpr, 3);
    // Actuator definitions
    //// Shooter Motors
    public static final Victor firePinMotor = new Victor(5);
    public static final Victor shooterMotorFront = new Victor(4);
    public static final Victor shooterMotorBack = new Victor(6);
    //// Hopper Servos
    public static final Servo hopperServoTop = new Servo(8);
    public static final Servo hopperServoBottom = new Servo(7);
    public static final Servo hopperDrawBridgeServo = new Servo(9);
    // Sensors
    //// Firing Pin Limit Switches
    public static final DigitalInput firePinMax = new DigitalInput(8);
    public static final DigitalInput firePinMin = new DigitalInput(7);
}
