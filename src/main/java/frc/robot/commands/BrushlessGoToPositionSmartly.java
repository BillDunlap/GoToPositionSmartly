// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SparkMaxBrushless;

public class BrushlessGoToPositionSmartly extends Command {
  private final SparkMaxBrushless m_motor;
  private final double m_desiredPosition;
  // velocity PID coefficients should have already been set in the motor object
  // smart motion coefficients
  private final double m_maxVelocity, m_minVelocity, m_maxAcceleration, m_allowedClosedLoopError;


  /** Creates a new BrushlessGoToPositionSmartly. */
  public BrushlessGoToPositionSmartly(SparkMaxBrushless motor, double desiredPosition,
    double maxVelocity, double minVelocity, double maxAcceleration, double allowedClosedLoopError) {
    m_motor = motor;
    m_desiredPosition = desiredPosition;
    m_maxVelocity = maxVelocity; m_minVelocity = minVelocity; m_maxAcceleration = maxAcceleration; m_allowedClosedLoopError = allowedClosedLoopError; 
    addRequirements(m_motor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // we could reset to factory defaults and set pid coefs here
    m_motor.doSmartMotion(m_desiredPosition, m_maxVelocity, m_minVelocity, m_maxAcceleration, m_allowedClosedLoopError);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
