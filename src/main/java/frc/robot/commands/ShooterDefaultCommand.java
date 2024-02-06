// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
/**
 * Set the RPM (revolutions per minute) of structure rotated by brushless motor controlled by Spark Max.
 * This takes into account gear ratio.
 */
public class ShooterDefaultCommand extends Command {
  private final Shooter m_Shooter;
  private final XboxController m_Controller;
  /** Creates a new BrushlessSetRPM. */
  public ShooterDefaultCommand(Shooter shooter, XboxController controller) {
    m_Shooter = shooter;
    m_Controller = controller;
    addRequirements(m_Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double desiredRPM = SmartDashboard.getNumber("Input: RPM", 0.0);
    m_Shooter.setRPM(desiredRPM);
    SmartDashboard.putNumber("Input: RPM", desiredRPM);
    //SmartDashboard.putNumber("Actual: Left RPM", m_Shooter.getLeftVelocity());
    //SmartDashboard.putNumber("Actual: Right RPM", m_Shooter.getRightVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
