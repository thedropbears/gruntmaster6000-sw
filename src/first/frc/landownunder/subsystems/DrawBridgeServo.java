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
public class DrawBridgeServo extends Subsystem {
    public DrawBridgeServo(){
      
    }
    public void open(){
    RobotMap.hopperDrawBridgeServo.setAngle(RobotMap.DRAWBRIDGE_SERVO_ANGLE);
    }
    public void close(){
    RobotMap.hopperDrawBridgeServo.setAngle(RobotMap.DRAWBRIDGE_SERVO_ANGLE);
    }
   

    protected void initDefaultCommand() {
    }
}