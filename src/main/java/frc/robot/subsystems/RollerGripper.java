package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static com.revrobotics.CANSparkBase.IdleMode.kBrake;
import static com.revrobotics.CANSparkLowLevel.MotorType.kBrushless;
import static frc.robot.Constants.RollerGripperConstants.*;

public class RollerGripper extends SubsystemBase {
    private final CANSparkMax leader = new CANSparkMax(LEADER_ID, kBrushless);
    private final CANSparkMax follower = new CANSparkMax(FOLLOWER_ID, kBrushless);

    private final DigitalInput button = new DigitalInput(RG_BUTTON_CHANNEL);
    private final Trigger buttonTrigger = new Trigger(() -> button.get());

    public RollerGripper() {
        leader.setIdleMode(kBrake);
        follower.setIdleMode(kBrake);
        follower.follow(leader);
        follower.setInverted(true);
    }

    public Command intakeCommand() {
        return new FunctionalCommand(
                () -> {},
                () -> leader.set(RG_INTAKE_SPEED),
                (__) -> leader.stopMotor(),
                buttonTrigger,
                this
        );
    }

    public Command ejectCommand() {
        return new RunCommand(() -> leader.set(RG_EJECT_SPEED), this)
                .until(buttonTrigger.negate())
                .andThen(new InstantCommand(() -> leader.stopMotor(), this));
    }
}
