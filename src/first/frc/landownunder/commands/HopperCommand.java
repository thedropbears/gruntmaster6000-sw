/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

import static first.frc.landownunder.commands.CommandBase.firingPin;

/**
 *
 * @author loughlan mckendry
 */
public class HopperCommand extends CommandBase {
    
    protected void execute() {
        
    }

    protected void initialize() {
        firingPin.openTopServo();
    }

    protected boolean isFinished() {
           return true;
    }

    protected void end() {

    }

    protected void interrupted() {
       
    }
    
}