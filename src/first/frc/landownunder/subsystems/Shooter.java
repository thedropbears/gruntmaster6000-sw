/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package first.frc.landownunder.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import first.frc.landownunder.RobotMap;
import first.frc.landownunder.commands.ShooterSpinup;
import first.frc.landownunder.components.Tachometer;

/**
 * Shooter subsystem consisting of two wheels, front and back
 * which are controlled by independent PID loops. Rear wheel can be set to spin
 * faster / slower by a relative factor. Feedback from the PID loop is a
 * DigitalInput for both front and back wheels acting as a tachometer.
 * @author carneeki
 * @since 2012-02-03 03:02AEDT
 */
public class Shooter extends Subsystem
{
  /**
   * Front motor controller.
   */
  private SpeedController m_Front;
  private PIDController pid_Front;
  private Tachometer tacho_Front;
  
  /**
   * How many RPM front wheel should spin at.
   */
  private double ShooterRPM;
  
  // pull out PID vars from preferences
  private double FKp;
  private double FKi;
  private double FKd;
  private double Fkf;
  
  /**
   * Back motor controller
   */
  private SpeedController m_Back;
  private PIDController pid_Back;
  private Tachometer tacho_Back;
  
  // pull out PID vars from preferences
  private double BKp;
  private double BKi;
  private double BKd;
  private double Bkf;
  
  /**
   * Back wheel speed difference. States how much faster / slower the rear wheel
   * should turn (in RPM) relative to the front wheel setPoint.
   * Default: -0.35
   */
  private final double backWheelDifference = -0.35; 

  protected void initDefaultCommand()
  {
    setDefaultCommand(new ShooterSpinup());
  }

  public boolean atSetpoint()
  {
    return true;
  }
  
  public Shooter()
  {
    m_Front = RobotMap.shooterMotorFront;
    tacho_Front = RobotMap.tachoFront;
    
    ShooterRPM = Preferences.getInstance().getDouble("ShooterRPM", 60.0);
    
    // pull out PID vars from preferences
    FKp = Preferences.getInstance().getDouble("ShooterTP", 10);
    FKi = Preferences.getInstance().getDouble("ShooterTI", 0.1);
    FKd = Preferences.getInstance().getDouble("ShooterTD", 50);
    Fkf = Preferences.getInstance().getDouble("ShooterTF", 1/1500);
    
    pid_Front = new PIDController(FKp, FKi, FKd, tacho_Front, m_Front);
    pid_Front.setOutputRange(0, 1);
    pid_Front.disable();
    pid_Front.setInputRange(0, 1500);
    pid_Front.setPercentTolerance(15);
    pid_Front.setSetpoint(ShooterRPM);
    
    // pull out PID vars from preferences
    BKp = Preferences.getInstance().getDouble("ShooterBP", 0.001);
    BKi = Preferences.getInstance().getDouble("ShooterBI", 0);
    BKd = Preferences.getInstance().getDouble("ShooterBD", 0.01);
    Bkf = Preferences.getInstance().getDouble("ShooterBF", 1/1500);
    
    pid_Back = new PIDController(BKp, BKi, BKd, tacho_Back, m_Back);
    pid_Back.setOutputRange(0, 1);
    pid_Back.disable();
    pid_Back.setInputRange(0, 1500);
    pid_Front.setPercentTolerance(15);
    pid_Front.setSetpoint( ShooterRPM * (1.0 + backWheelDifference) );
  }
}
