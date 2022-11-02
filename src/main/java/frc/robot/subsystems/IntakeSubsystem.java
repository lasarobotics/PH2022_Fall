// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  public static class Hardware {
    private WPI_TalonSRX armMotor;
    private WPI_VictorSPX rollerMotor;
    private WPI_TalonSRX internalMotor;

    public Hardware(WPI_TalonSRX armMotor, 
                    WPI_VictorSPX rollerMotor,
                    WPI_TalonSRX internalMotor) {
      this.armMotor = armMotor;
      this.rollerMotor = rollerMotor;
      this.internalMotor = internalMotor;
    }
  }


  private final WPI_TalonSRX m_armMotor;
  private final WPI_VictorSPX m_rollerMotor;
  private final WPI_TalonSRX m_internalMotor;


  /** Creates a new DriveSubsystem. */
  public IntakeSubsystem(Hardware intakeHardware) {
    this.m_armMotor = intakeHardware.armMotor;
    this.m_rollerMotor = intakeHardware.rollerMotor;
    this.m_internalMotor = intakeHardware.internalMotor;
  }

  public static Hardware initializeHardware() {
    Hardware intakeHardware = new Hardware(new WPI_TalonSRX(Constants.ARM_MOTOR_PORT),
                                           new WPI_VictorSPX(Constants.ROLLER_MOTOR_PORT),
                                           new WPI_TalonSRX(Constants.INTERNAL_MOTOR_PORT));
    return intakeHardware;
  }

  public void intake() {
    m_rollerMotor.set(ControlMode.PercentOutput, +Constants.SPIN_MOTOR_SPEED);
    m_internalMotor.set(ControlMode.PercentOutput, +Constants.INTERNAL_MOTOR_SPEED);
  }

  public void intakeAndOutakeStop() {
    m_rollerMotor.stopMotor();
    m_internalMotor.stopMotor();
  }
   
  public void outtake() {
    m_rollerMotor.set(ControlMode.PercentOutput, -Constants.SPIN_MOTOR_SPEED);
    m_internalMotor.set(ControlMode.PercentOutput, -Constants.INTERNAL_MOTOR_SPEED);
  }

  public void armDown() {
    m_armMotor.set(ControlMode.PercentOutput, +Constants.ARM_MOTOR_SPEED);
  }

  public void armUp() {
    m_armMotor.set(ControlMode.PercentOutput, -Constants.ARM_MOTOR_SPEED);
  }

  public void armStop() {
    m_armMotor.stopMotor();
  }

  public void close() {
    m_armMotor.close();
    m_rollerMotor.close();
    m_internalMotor.close();
  }
}
