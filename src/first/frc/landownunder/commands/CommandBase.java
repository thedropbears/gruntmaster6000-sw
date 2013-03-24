/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import first.frc.landownunder.OI;
import first.frc.landownunder.subsystems.Chassis;
import first.frc.landownunder.subsystems.FiringPin;
import first.frc.landownunder.subsystems.Shooter;

/**
 *
 * @author carneeki
 */
public abstract class CommandBase extends Command
{
  public static OI oi;
  
  // set up subsystems
  public static Chassis chassis = new Chassis();
  public static FiringPin firingPin = new FiringPin();
  public static Shooter shooter = new Shooter();
  
  
  public static void init()
  {
    oi = new OI();
    
    SmartDashboard.putData("SchedulerData",Scheduler.getInstance());
    
    SmartDashboard.putData(chassis);
    SmartDashboard.putData(firingPin);
    SmartDashboard.putData(shooter);
    
  }
  
  // default constructors per page 41 of cookbook
  public CommandBase(String name)
  {
    super(name);
  }
  
  public CommandBase()
  {
    super();
  }
}
