package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ShooterSubsystem extends SubsystemBase {
  public static class Hardware {
    private TalonSRX shooterMotor;
    private TalonSRX beltMotor;

    public Hardware(TalonSRX shooterMotor, TalonSRX beltMotor) {
      this.shooterMotor = shooterMotor;
      this.beltMotor = beltMotor;
    }
  }

  TalonSRX m_ShooterMotor;
  TalonSRX m_BeltMotor;

  public ShooterSubsystem(Hardware shooterHardware) {
    m_ShooterMotor = shooterHardware.shooterMotor;
    m_BeltMotor = shooterHardware.beltMotor;
  }
  
  public static Hardware initializeHardware() {
    Hardware shooterHardware = new Hardware(new TalonSRX(Constants.SHOOTER_MOTOR_PORT),
                                            new TalonSRX(Constants.BELT_MOTOR_PORT));
    return shooterHardware;
  }
  
  public void shoot(double flywheelSpeed, double beltSpeed) {
    m_ShooterMotor.set(ControlMode.PercentOutput, flywheelSpeed);
    m_BeltMotor.set(ControlMode.PercentOutput, beltSpeed);
  }

  public void runBelt(double beltSpeed) {
    m_BeltMotor.set(ControlMode.PercentOutput, beltSpeed);
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