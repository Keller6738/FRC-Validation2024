// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class RollerGripperConstants {
        public static final int LEADER_ID = 11;
        public static final int FOLLOWER_ID = 12;
        public static final int RG_BUTTON_CHANNEL = 0;
        public static final double RG_INTAKE_SPEED = 0.4;
        public static final double RG_EJECT_SPEED = 0.4;
    }

    public static final class IntakeConstants {
        public static final int INTAKE_LEADER_ID = 21;
        public static final int INTAKE_FOLLOWER_ID = 22;
        public static final int PISTON_FORWARD_CHANNEL = 1;
        public static final int PISTON_REVERSE_CHANNEL = 2;
        public static final int INTAKE_BUTTON_CHANNEL = 1;
        public static final double INTAKE_SPEED = 0.3;
    }
}
