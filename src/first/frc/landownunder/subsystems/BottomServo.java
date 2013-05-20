/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import first.frc.landownunder.RobotMap;

/**
 *
 * @author Developer
 */
public class BottomServo extends Subsystem {
    public BottomServo(){
        close();
    }
    public void open(){
    RobotMap.hopperServoBottom.setAngle(RobotMap.OPEN_SERVO_ANGLE);
    }
    public void close(){
    RobotMap.hopperServoBottom.setAngle(RobotMap.CLOSE_SERVO_ANGLE);
    }

    protected void initDefaultCommand() {
    }
}
