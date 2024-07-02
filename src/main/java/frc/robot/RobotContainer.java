// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.RollerGripper;

import static edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior.kCancelIncoming;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final CommandPS5Controller controller = new CommandPS5Controller(0);

    private final RollerGripper rollerGripper = new RollerGripper();
    private final Intake intake = new Intake();

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        controller.circle().onTrue(rollerGripper.intakeCommand());
        controller.triangle().onTrue(rollerGripper.ejectCommand());

        controller.square().onTrue(intake.intakeCommand());
    }

    public Command getAutonomousCommand() {
        return Commands.none();
    }
}
