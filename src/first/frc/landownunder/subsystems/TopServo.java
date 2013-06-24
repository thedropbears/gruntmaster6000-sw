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
public class TopServo extends Subsystem {
    public TopServo(){
        close();
    }
    public void open(){
    RobotMap.hopperServoTop.setAngle(RobotMap.CLOSE_SERVO_ANGLE);
    }
    public void close(){
    RobotMap.hopperServoTop.setAngle(RobotMap.OPEN_SERVO_ANGLE);
    }

    protected void initDefaultCommand() {
    }
}
