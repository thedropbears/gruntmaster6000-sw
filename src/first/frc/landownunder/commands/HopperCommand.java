/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 * @author loughlan mckendry
 */
public class HopperCommand extends CommandBase {
    
    protected void execute() {
        
    }

    protected void initialize() {
        //SmartDashboard.putData("hello", "hello");
        SmartDashboard.putNumber("start has been called on hoppercommand", 1);
        firingPin.openTopServo();
        
        
    }

    protected boolean isFinished() {
           return true;
    }

    protected void end() {
        SmartDashboard.putNumber("finished loading hopper", 3);
    }

    protected void interrupted() {
       
    }
    
}