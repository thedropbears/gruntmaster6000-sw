/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot
{
  
  RobotDrive rdDrive = new RobotDrive(9,10);
  
  Joystick joystickLeft = new Joystick(1);
  Joystick joystickRight = new Joystick(2);
  
  /*
   * Motor positions: 
   *          A
   *          |=vX
   *          .
   * 
   *      C       B
   *      |       |=-vX/2 - sqrt(3)/2 * vY
   *      |=-vX/2 + sqrt(3)/2 * vY
   */
  double vA, vB, vC = 0; // velocity for motors A, B, C
  
  Victor motorA = new Victor(1,1);
  Victor motorB = new Victor(1,2);
  Victor motorC = new Victor(1,3);
  
  /**
   * This function is called once each time the robot enters autonomous mode.
   */
  public void autonomous()
  {
    // TODO: implement me
  }

  /**
   * This function is called once each time the robot enters operator control.
   */
  public void operatorControl()
  {
    rdDrive.setSafetyEnabled(true);
    
    while( isOperatorControl() && isEnabled() )
    {
      this.VectorDrive(joystickLeft.getX(), joystickLeft.getY());
      Timer.delay(0.01);
    }
  }

  /**
   * This function is called once each time the robot enters test mode.
   */
  public void test()
  {
  }
  
  /**
   * concept based on Vector_Drive from CMU implementation at
   * http://www.cs.cmu.edu/~pprk/Download/robot.cpp
   * and
   * http://www.rjmcnamara.com/2012/06/holonomic-platforms-robotc/
   * @param vX x position of joystick
   * @param vY y pos of joystick
   * @author Adam Carmichael <carneeki@carneeki.net>
   * @since 2013-01-19 12:30AEDT
   */
  private void VectorDrive(double vX, double vY)
  {
    
    // set MotorA
    vA = vX;
    if(vA < 20  && vA >    0) { vA =   0; } // Set Minimum MotorA's Forward Power
    if(vA < 0   && vA >  -20) { vA =   0; } // Set Minimum MotorA's Reverse Power
    if(vA > 100 || vA < -100) { vA = 100; } // Set Maximum Motor Power Level at 100
    
    // set MotorB
    vB = (-vX / 2) - ( Math.sqrt(3) /2 * vY );
    if(vB <  20 && vB >    0) { vB =   0; } // Set Minimum MotorB's Forward Power
    if(vB <   0 && vB >  -20) { vB =   0; } // Set Minimum MotorB's Reverse Power
    if(vB > 100 || vB < -100) { vB = 100; } // Set Maximum Motor Power Level at 100
    
    // set MotorC
    vC = (-vX / 2) + ( Math.sqrt(3) /2 * vY );
    if(vC <  20 && vC >    0) { vC =   0; } // Set Minimum MotorC's Forward Power
    if(vC <   0 && vC >  -20) { vC =   0; } // Set Minimum MotorC's Reverse Power
    if(vC > 100 || vC < -100) { vC = 100; } // Set Maximum Motor Power Level at 100
    
    motorA.set(vA);
    motorB.set(vB);
    motorC.set(vC);
  }
}