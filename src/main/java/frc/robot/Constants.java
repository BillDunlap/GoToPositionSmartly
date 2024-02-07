// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class Operator {
    public static final int kDriverControllerPort = 0; // The port for the XBox controller
  }
  public static class TestBrushless {
    public static final int canId = 12;
    public static final double encoderRotationsPerFinalRotation = 1.0;
    public static final String name = "Test Brushless";
    // velocity PID values
    public static final double kP = 0.000080;
    public static final double kI = 0.000000;
    public static final double kD = 0.000000;
    public static final double kFeedForward = 0.000170;
    public static final double kIZone = 0.000000;
    public static final double kMinOutput = -1.0;
    public static final double kMaxOutput = +1.0;
  }
  public static class ShooterPID {
    public static final double encoderRotationsPerFinalRotation = 1.0;
    // velocity PID values
    public static final double kP = 0.000100;
    public static final double kI = 0.000000;
    public static final double kD = 0.000000;
    public static final double kFeedForward = 0.000170 * 1.07;
    public static final double kIZone = 0.000000;
    public static final double kMinOutput = -1.0;
    public static final double kMaxOutput = +1.0;
  }  
  public static class ShooterKickerPID {
    public static final double encoderRotationsPerFinalRotation = 10.0;
    // velocity PID values
    public static final double kP = 0.000050;
    public static final double kI = 0.000000;
    public static final double kD = 0.001000;
    public static final double kFeedForward = 0.000088;
    public static final double kIZone = 0.000000;
    public static final double kMinOutput = -1.0;
    public static final double kMaxOutput = +1.0;
  }
  public static class LeftShooterMotor{
    public static final int canId = 11;
    public static final String name = "Left Shooter";
  }
  public static class RightShooterMotor{
    public static final int canId = 53;
    public static final String name = "RightShooter";
  }
  public static class LeftShooterKickerMotor {
    public static final int canId = 58;
    public static final String name = "Left Kicker";
  }
  public static class RightShooterKickerMotor {
    public static final int canId = 57;
    public static final String name = "Right Kicker";
  }
}
