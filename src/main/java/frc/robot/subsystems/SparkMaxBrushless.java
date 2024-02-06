// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.text.DecimalFormat;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SparkMaxBrushless extends SubsystemBase {
  private final String m_name;
  private final CANSparkMax m_CANSparkMax;
  private final RelativeEncoder m_RelativeEncoder;
  private final SparkPIDController m_SparkPIDController;
  private final double m_encoderRotationsPerFinalRotation;
  private double m_initialEncoderPosition;
  private int m_SmartMotionSlot = 0;
  private DecimalFormat df2 = new DecimalFormat("#.00");
  private double m_desiredPosition = 0.0;
  
  /** Creates a new SparkMaxBrushless. */
  public SparkMaxBrushless(int canId, double encoderRotationsPerFinalRotation, String name) {
    m_name = name;
    m_encoderRotationsPerFinalRotation =  encoderRotationsPerFinalRotation;
    m_CANSparkMax = new CANSparkMax(canId, MotorType.kBrushless);
    m_CANSparkMax.restoreFactoryDefaults();
    m_RelativeEncoder = m_CANSparkMax.getEncoder();
    m_SparkPIDController = m_CANSparkMax.getPIDController();
    setCurrentPositionAsInitialEncoderPosition();
  }

  /**
   * Set zero point for encode to its current position.  This may be wanted in case
   * code started before robot was in its initial configuration.
   */
  public void setCurrentPositionAsInitialEncoderPosition(){
    System.out.print("Changing initialEncoderPosition from " + df2.format(m_initialEncoderPosition));
    m_initialEncoderPosition = m_RelativeEncoder.getPosition();
    System.out.println(" to " + df2.format(m_initialEncoderPosition));
  }
  /**
   * @return
   * Position of thing being rotated.  Units = rotations of that thing.
   */
  public double getPosition(){
    return (m_RelativeEncoder.getPosition() - m_initialEncoderPosition) / m_encoderRotationsPerFinalRotation;
  }

  /**
   * @return
   * Velocity of thing being rotated.  Units = rotations of that thing per minute
   */
  public double getVelocity(){
    return m_RelativeEncoder.getVelocity() / m_encoderRotationsPerFinalRotation;
  }

  /**
   * @return
   * The encoder associated with this motor controller.  You may use this
   * if you wish to use motor-oriented units for position and velocity.
   */
  public RelativeEncoder getEncoder(){
    return m_RelativeEncoder;
  }

  public String getName(){
    return m_name;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber(getName() + " pos", getPosition());
    SmartDashboard.putNumber(getName() + " vel", getVelocity());
    SmartDashboard.putNumber(getName() + " amps", m_CANSparkMax.getOutputCurrent());
    SmartDashboard.putNumber(getName() + " desired pos", m_desiredPosition);
  }

  /**
   * @param percent: desired 'speed' on a scale of -1.0 to 1.0
   */
  public void setPercentSpeed(double percent){
    m_CANSparkMax.set(percent);
  }
  public void setRPM(double rpm){
    double encoderRpm = rpm * m_encoderRotationsPerFinalRotation;
    m_SparkPIDController.setReference(encoderRpm, ControlType.kVelocity);
  }
  // We use encoder-centric PID parameters so we can copy them from test/tuning program
  public void setPIDCoefficients(double kP, double kI, double kD, double kIZone,
    double kFeedForward, double kMinOutput, double kMaxOutput) {
      m_SparkPIDController.setP(kP);
      m_SparkPIDController.setI(kI);
      m_SparkPIDController.setD(kD);
      m_SparkPIDController.setIZone(kIZone);
      m_SparkPIDController.setFF(kFeedForward);
      m_SparkPIDController.setOutputRange(kMinOutput, kMaxOutput);
    }

  // Here we use position, velocities, and acceleration from point of view of thing being rotated.  Time unit is minute. 
  public void doSmartMotion(double desiredPosition, double maxVelocity, double minVelocity,
    double maxAcceleration, double allowedClosedLoopError){
      m_desiredPosition = desiredPosition;
      m_SparkPIDController.setSmartMotionMaxVelocity(maxVelocity * m_encoderRotationsPerFinalRotation, m_SmartMotionSlot);
      m_SparkPIDController.setSmartMotionMinOutputVelocity(minVelocity * m_encoderRotationsPerFinalRotation, m_SmartMotionSlot);
      m_SparkPIDController.setSmartMotionMaxAccel(maxAcceleration * m_encoderRotationsPerFinalRotation, m_SmartMotionSlot);
      m_SparkPIDController.setSmartMotionAllowedClosedLoopError(allowedClosedLoopError/m_encoderRotationsPerFinalRotation, m_SmartMotionSlot); // what units?
      double desiredEncoderPosition = m_desiredPosition * m_encoderRotationsPerFinalRotation + m_initialEncoderPosition;
      System.out.println("Going from encoder position " + df2.format(m_RelativeEncoder.getPosition()) + " to " + df2.format(desiredEncoderPosition));
      m_SparkPIDController.setReference(desiredEncoderPosition, CANSparkMax.ControlType.kSmartMotion);
    }

    public void holdCurrentPosition(){
      // TODO: Use raw (not "smart motion") position control.  Must use different set of PID values.
      // Should we use "slots": one for velocity and one for position? 
      // Also, we may want to use more intelligent feedforward (depending on angle of arm) while holding.
    }
}
