
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.SparkMax;

public class ClimberSubsystem extends SubsystemBase {
  public static class Hardware {
    private SparkMax winchMotor;

    public Hardware(SparkMax winchMotor){
      this.winchMotor = winchMotor;
    }
  }

  private SparkMax m_winchMotor;

  /** Creates a new ExampleSubsystem. */
  public ClimberSubsystem(Hardware climberHardware) {
    this.m_winchMotor = climberHardware.winchMotor;
  }

  public static Hardware initializeHardware() {
    Hardware climberHardware = new Hardware(new SparkMax(Constants.CLIMBER_WINCH_MOTOR_PORT, MotorType.kBrushless));
    return climberHardware;
  }

  public void initialize() {
    winchSetPosition(0.0);
  }

  public void winchUp() {
    winchSetPosition(Constants.CLIMBER_WINCH_MOTOR_MAX);
  }

  public void winchDown() {
    winchSetPosition(Constants.CLIMBER_WINCH_MOTOR_MIN);
  }

  public void winchStop() {
    m_winchMotor.stopMotor();
  }

  public void winchSetPosition(double position) {
    position = MathUtil.clamp(position, Constants.CLIMBER_WINCH_MOTOR_MIN, Constants.CLIMBER_WINCH_MOTOR_MAX);
    m_winchMotor.set(position);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void close() {
    m_winchMotor.close();
  }
}
