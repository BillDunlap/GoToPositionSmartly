// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
/**
 * Two motor shooter for Notes.
 * Motors should run at same speed.
 */
public class Shooter extends SubsystemBase {
  private final SparkMaxBrushless m_leftMotor;
  private final SparkMaxBrushless m_rightMotor;
  private double m_rpm;
  /** Creates a new Shooter. */
  public Shooter(SparkMaxBrushless leftMotor, SparkMaxBrushless rightMotor) {
    m_leftMotor = leftMotor;
    m_rightMotor = rightMotor;
    m_rpm = 0.0;
  }

  /**
   * @param rpm: desired speed of thing being rotated, in revolutions per minute outward.
   */
  // TODO: is positive rpm clockwise or counterclockwise, looking from motor to shaft or from shaft to motor
  public void setRPM(double rpm){
    m_rpm = rpm;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    m_leftMotor.setRPM(m_rpm);
    m_rightMotor.setRPM(-m_rpm);
  }

  public double getLeftVelocity(){
    return m_leftMotor.getVelocity();
  }
  public double getRightVelocity(){
    return m_rightMotor.getVelocity();
  }
}
