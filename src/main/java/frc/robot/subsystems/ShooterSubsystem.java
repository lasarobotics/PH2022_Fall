package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ShooterSubsystem extends SubsystemBase {

  TalonSRX m_ShooterMotor;

  public ShooterSubsystem() {
    m_ShooterMotor = new TalonSRX(Constants.SHOOTER_MOTOR_PORT);
  }

  public void shoot(double speed) {
    m_ShooterMotor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}