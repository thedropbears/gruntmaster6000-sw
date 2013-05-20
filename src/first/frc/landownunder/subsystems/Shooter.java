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

  /**
   * Back motor controller
   */
  private SpeedController m_Back;
  private PIDController pid_Back;
  private Tachometer tacho_Back;
  
  public Shooter()
  {
    m_Front = RobotMap.shooterMotorFront;
    m_Back  = RobotMap.shooterMotorBack;
  }
  
  public void stop()
  {
    m_Front.set(0.0);
    m_Back.set(0.0);
    //m_Front.disable();
    //m_Back.disable();
  }
  
  public void slow()
  {
      m_Front.set(-0.1);
      m_Back.set(-0.1);
  }
  
  public void fast()
  {
      m_Front.set(-0.4);
      m_Back.set(-0.4);
  }

  protected void initDefaultCommand()
  {
    
  }
}
