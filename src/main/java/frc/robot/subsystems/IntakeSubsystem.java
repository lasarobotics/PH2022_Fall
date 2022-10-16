// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  public static class Hardware {
    private CANSparkMax lMotor, rMotor, spinMotor;

    public Hardware(CANSparkMax rMotor, 
                    CANSparkMax lMotor,
                    CANSparkMax spinMotor) {
      this.lMotor = lMotor;
      this.rMotor = rMotor;
      this.spinMotor = spinMotor;
    }
  }


  public enum WantedAction {
    NONE, INTAKE, REVERSE, REJECT
  }

  public enum CurrentAction {
    IDLE, INTAKING, REVERSING, REJECTING
  }

  public CurrentAction mState = CurrentAction.IDLE;

  public void setAction(WantedAction wantedAction) {
    switch (wantedAction) {
        case NONE:
            if (mState != CurrentAction.IDLE) {
                mState = CurrentAction.IDLE;
            }
            break;
        case INTAKE:
            if (mState != CurrentAction.INTAKING) {
                mState = CurrentAction.INTAKING;
            }
            break;
        case REVERSE:
            if (mState != CurrentAction.REVERSING) {
                mState = CurrentAction.REVERSING;
            }
            break;
        case REJECT:
            if (mState != CurrentAction.REJECTING) {
                mState = CurrentAction.REJECTING;
            }
            break;
    }
}

  private final CANSparkMax m_lMotor, m_rMotor, m_spinMotor;

  /** Creates a new DriveSubsystem. */
  public IntakeSubsystem(Hardware intakeHardware) {
    this.m_lMotor = intakeHardware.lMotor;
    this.m_rMotor = intakeHardware.rMotor;
    this.m_spinMotor = intakeHardware.spinMotor;
  }

  public static Hardware initializeHardware() {
    Hardware intakeHardware = new Hardware(new CANSparkMax(Constants.LEFT_MOTOR_PORT, MotorType.kBrushless),
                                               new CANSparkMax(Constants.RIGHT_MOTOR_PORT, MotorType.kBrushless),
                                               new CANSparkMax(Constants.SPIN_MOTOR_PORT, MotorType.kBrushless));
    return intakeHardware;
  }

  public void intake() {
  }

  public void close() {
    m_lMotor.close();
    m_rMotor.close();
    m_spinMotor.close();
  }
}
