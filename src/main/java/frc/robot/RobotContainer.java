// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private static final DriveSubsystem DRIVE_SUBSYSTEM = new DriveSubsystem(DriveSubsystem.initializeHardware(),
                                                                           Constants.CONTROLLER_DEADBAND);
  private static final ClimberSubsystem CLIMBER_SUBSYSTEM = new ClimberSubsystem(ClimberSubsystem.initializeHardware());
  private static final IntakeSubsystem  INTAKE_SUBSYSTEM = new IntakeSubsystem(IntakeSubsystem.initializeHardware());
  private static final ShooterSubsystem SHOOTER_SUBSYSTEM = new ShooterSubsystem(ShooterSubsystem.initializeHardware());
  
  private static final XboxController PRIMARY_CONTROLLER = new XboxController(Constants.PRIMARY_CONTROLLER_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    DRIVE_SUBSYSTEM.setDefaultCommand(
      new RunCommand(
        () -> DRIVE_SUBSYSTEM.teleop(PRIMARY_CONTROLLER.getLeftY(), PRIMARY_CONTROLLER.getLeftX(), PRIMARY_CONTROLLER.getRightX()), 
        DRIVE_SUBSYSTEM
      )
    );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Primary controller buttons
    JoystickButton primaryButtonA = new JoystickButton(PRIMARY_CONTROLLER, Button.kA.value);
    JoystickButton primaryButtonB = new JoystickButton(PRIMARY_CONTROLLER, Button.kB.value);
    JoystickButton primaryButtonX = new JoystickButton(PRIMARY_CONTROLLER, Button.kX.value);
    JoystickButton primaryButtonY = new JoystickButton(PRIMARY_CONTROLLER, Button.kY.value);
    JoystickButton primaryButtonLBumper = new JoystickButton(PRIMARY_CONTROLLER, Button.kLeftBumper.value);
    JoystickButton primaryButtonRBumper = new JoystickButton(PRIMARY_CONTROLLER, Button.kRightBumper.value);
    JoystickButton primaryButtonLStick = new JoystickButton(PRIMARY_CONTROLLER, Button.kLeftStick.value);
    JoystickButton primaryButtonRStick = new JoystickButton(PRIMARY_CONTROLLER, Button.kRightStick.value);
    JoystickButton primaryButtonStart = new JoystickButton(PRIMARY_CONTROLLER, Button.kStart.value);
    JoystickButton primaryButtonBack = new JoystickButton(PRIMARY_CONTROLLER, Button.kBack.value);
    POVButton primaryDPadUp = new POVButton(PRIMARY_CONTROLLER, 0);
    POVButton primaryDPadRight = new POVButton(PRIMARY_CONTROLLER, 90);
    POVButton primaryDPadDown = new POVButton(PRIMARY_CONTROLLER, 180);
    POVButton primaryDPadLeft = new POVButton(PRIMARY_CONTROLLER, 270);
    Trigger primaryTriggerLeft = new Trigger(() -> PRIMARY_CONTROLLER.getLeftTriggerAxis() > Constants.CONTROLLER_DEADBAND);
    Trigger primaryTriggerRight = new Trigger(() -> PRIMARY_CONTROLLER.getRightTriggerAxis() > Constants.CONTROLLER_DEADBAND);

    // Climber controls
    primaryDPadUp.whenPressed(new InstantCommand(() -> CLIMBER_SUBSYSTEM.winchUp(), CLIMBER_SUBSYSTEM));
    primaryDPadDown.whenPressed(new InstantCommand(() -> CLIMBER_SUBSYSTEM.winchDown(), CLIMBER_SUBSYSTEM));
    primaryButtonX.whenPressed(new InstantCommand(() -> CLIMBER_SUBSYSTEM.winchStop(), CLIMBER_SUBSYSTEM));

    // Intake Controls
    primaryButtonA.whenPressed(new InstantCommand(() -> {
        INTAKE_SUBSYSTEM.armUp();
        INTAKE_SUBSYSTEM.intake();
      }
    ));
    primaryButtonA.whenPressed(new InstantCommand(() -> INTAKE_SUBSYSTEM.armDown()));
    primaryButtonY.whenPressed(new InstantCommand(() -> {
        INTAKE_SUBSYSTEM.armUp();
        INTAKE_SUBSYSTEM.outtake();
      }
    ));
    primaryButtonY.whenPressed(new InstantCommand(() -> INTAKE_SUBSYSTEM.armDown()));

    // Shooter controls
    primaryButtonB.whenPressed(new InstantCommand(() -> SHOOTER_SUBSYSTEM.shoot(0.9, 0.5)));
    primaryButtonB.whenReleased(new InstantCommand(() -> SHOOTER_SUBSYSTEM.stop()));
  }

  public void initialize() {
    CLIMBER_SUBSYSTEM.initialize();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}