package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import static com.revrobotics.CANSparkBase.IdleMode.kBrake;
import static com.revrobotics.CANSparkLowLevel.MotorType.kBrushed;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
import static edu.wpi.first.wpilibj.PneumaticsModuleType.CTREPCM;
import static frc.robot.Constants.IntakeConstants.*;

public class Intake extends SubsystemBase {
    private final CANSparkMax intakeMotor = new CANSparkMax(INTAKE_MOTOR_ID, kBrushed);
    private final DoubleSolenoid intakePiston = new DoubleSolenoid(CTREPCM, PISTON_FORWARD_CHANNEL, PISTON_REVERSE_CHANNEL);

    private final DigitalInput button = new DigitalInput(INTAKE_BUTTON_CHANNEL);
    private Trigger buttonTrigger = new Trigger(() -> button.get());

    public Intake() {
        intakeMotor.setIdleMode(kBrake);
    }

    public Command intakeCommand() {
        return new FunctionalCommand(
                () -> intakePiston.set(kForward),
                () -> intakeMotor.set(INTAKE_SPEED),
                (__) -> {
                    intakeMotor.stopMotor();
                    intakePiston.set(kReverse);
                },
                buttonTrigger,
                this
        );
    }
}
