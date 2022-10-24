package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class ShooterSubsystem extends SubsystemBase {
  public static class Hardware {
    private WPI_TalonSRX shooterMotor;
    private WPI_TalonSRX beltMotor;

    public Hardware(WPI_TalonSRX shooterMotor, WPI_TalonSRX beltMotor) {
      this.shooterMotor = shooterMotor;
      this.beltMotor = beltMotor;
    }
  }

  WPI_TalonSRX m_ShooterMotor;
  WPI_TalonSRX m_BeltMotor;

  public ShooterSubsystem(Hardware shooterHardware) {
    m_ShooterMotor = shooterHardware.shooterMotor;
    m_BeltMotor = shooterHardware.beltMotor;
  }
  
  public static Hardware initializeHardware() {
    Hardware shooterHardware = new Hardware(new WPI_TalonSRX(Constants.SHOOTER_MOTOR_PORT),
                                            new WPI_TalonSRX(Constants.BELT_MOTOR_PORT));
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