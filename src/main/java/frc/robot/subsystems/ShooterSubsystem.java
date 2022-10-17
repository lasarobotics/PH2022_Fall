package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ShooterSubsystem extends SubsystemBase {
  public static class Hardware {
    private TalonSRX ShooterMotor;

    public Hardware(TalonSRX ShooterMotor) {
      this.ShooterMotor = ShooterMotor;
    }
  }

  TalonSRX m_ShooterMotor;

  public ShooterSubsystem(Hardware shooterHardware) {
    m_ShooterMotor = shooterHardware.ShooterMotor;
  }
  
  public static Hardware initializeHardware() {
    Hardware shooterHardware = new Hardware(new TalonSRX(Constants.SHOOTER_MOTOR_PORT));
    return shooterHardware;
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