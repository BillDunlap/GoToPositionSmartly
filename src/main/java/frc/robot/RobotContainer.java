// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.BrushlessGoToPositionSmartly;
import frc.robot.commands.ShooterDefaultCommand;
import frc.robot.commands.TestBrushlessDefaultCommand;
import frc.robot.subsystems.Shooter;
// import frc.robot.commands.BrushlessSetPercentSpeed; // could use this in teleop
import frc.robot.subsystems.SparkMaxBrushless;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SparkMaxBrushless m_testBrushless = null; // new SparkMaxBrushless(Constants.TestBrushless.canId, Constants.TestBrushless.encoderRotationsPerFinalRotation, Constants.TestBrushless.name);
  private final SparkMaxBrushless m_lefttMotor = new SparkMaxBrushless(Constants.LeftShooterMotor.canId, 1.0, "Left Shooter");
  private final SparkMaxBrushless m_rightMotor = new SparkMaxBrushless(Constants.RightShooterMotor.canId, 1.0, "Right Shooter");
  private final Shooter m_Shooter = new Shooter(m_lefttMotor, m_rightMotor);
  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final XboxController m_driverController =
      new XboxController(Constants.Operator.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    if (m_testBrushless != null) {
      m_testBrushless.setPIDCoefficients(Constants.TestBrushless.kP,
                                         Constants.TestBrushless.kI, 
                                         Constants.TestBrushless.kD,
                                         Constants.TestBrushless.kIZone, 
                                         Constants.TestBrushless.kFeedForward,
                                         Constants.TestBrushless.kMinOutput,
                                         Constants.TestBrushless.kMaxOutput);
    }
    if (m_lefttMotor != null) {
         m_lefttMotor.setPIDCoefficients(Constants.ShooterPID.kP,
                                         Constants.ShooterPID.kI, 
                                         Constants.ShooterPID.kD,
                                         Constants.ShooterPID.kIZone, 
                                         Constants.ShooterPID.kFeedForward,
                                         Constants.ShooterPID.kMinOutput,
                                         Constants.ShooterPID.kMaxOutput);

    }
    if (m_rightMotor != null) {
         m_rightMotor.setPIDCoefficients(Constants.ShooterPID.kP,
                                         Constants.ShooterPID.kI, 
                                         Constants.ShooterPID.kD,
                                         Constants.ShooterPID.kIZone, 
                                         Constants.ShooterPID.kFeedForward,
                                         Constants.ShooterPID.kMinOutput,
                                         Constants.ShooterPID.kMaxOutput);

    }
    // Configure the trigger bindings
    configureBindings();
    m_Shooter.setDefaultCommand(new ShooterDefaultCommand(m_Shooter, m_driverController));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    if (m_testBrushless != null){
      new JoystickButton(m_driverController, XboxController.Button.kX.value)
        .onTrue(new BrushlessGoToPositionSmartly(m_testBrushless, 0.0, 500.0, 0.0, 1250.0, 0.0));
      new JoystickButton(m_driverController, XboxController.Button.kB.value)
        .onTrue(new BrushlessGoToPositionSmartly(m_testBrushless, 100.0, 500.0, 0.0, 1250.0, 0.0));
      new JoystickButton(m_driverController, XboxController.Button.kA.value)
        .onTrue(new BrushlessGoToPositionSmartly(m_testBrushless, -50., 500.0, 0, 1250.0, 0.0));
      new JoystickButton(m_driverController, XboxController.Button.kStart.value)
        .onTrue(new InstantCommand(() -> m_testBrushless.setCurrentPositionAsInitialEncoderPosition()));
      new Trigger(() -> Math.abs(m_driverController.getLeftY()) > 0.1)
        .whileTrue(new TestBrushlessDefaultCommand(m_testBrushless, m_driverController));
      //new JoystickButton(m_driverController, XboxController.Button.kY.value) // will this hold current position? seems to go to 0
      //  .onTrue(new BrushlessGoToPositionSmartly(m_testBrushless, m_testBrushless.getPosition(), 500., 0, 2500., 0.0));
    }
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return null;
  }
}
