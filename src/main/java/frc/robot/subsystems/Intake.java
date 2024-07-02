package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static com.revrobotics.CANSparkBase.IdleMode.kBrake;
import static com.revrobotics.CANSparkLowLevel.MotorType.kBrushless;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
import static edu.wpi.first.wpilibj.PneumaticsModuleType.CTREPCM;
import static frc.robot.Constants.IntakeConstants.*;

public class Intake extends SubsystemBase {
    private final CANSparkMax intakeLeader = new CANSparkMax(INTAKE_LEADER_ID, kBrushless);
    private final CANSparkMax intakeFollower = new CANSparkMax(INTAKE_FOLLOWER_ID, kBrushless);
    private final DoubleSolenoid intakePiston = new DoubleSolenoid(CTREPCM, PISTON_FORWARD_CHANNEL, PISTON_REVERSE_CHANNEL);

    private final DigitalInput beambreak = new DigitalInput(INTAKE_BUTTON_CHANNEL);
    private Trigger beambreakTrigger = new Trigger(() -> beambreak.get());

    public Intake() {
        intakeFollower.follow(intakeLeader);
        intakeFollower.setInverted(true);
    }

    public Command intakeCommand() {
        return new FunctionalCommand(
                () -> intakePiston.set(kForward),
                () -> intakeLeader.set(INTAKE_SPEED),
                (__) -> {
                    intakeLeader.stopMotor();
                    intakePiston.set(kReverse);
                },
                beambreakTrigger,
                this
        );
    }
}
