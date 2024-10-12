package org.coding.cobra.config;

import com.acmerobotics.roadrunner.Pose2d;

import org.coding.cobra.config.helpers.DCMotorConfig;

/**
 * Class where we will populate system configuration such as motor names
 * motor types, limits, etc
 */

public class SystemConfig {

    public static Pose2d ROBOT_START_POSITION = new Pose2d(0, 0, 0);

    public static final double DRIVE_POWER_FACTOR = 1;

    // DCMotor
    public static DCMotorConfig ARM_EXTENDER_LEFT = new DCMotorConfig(
            "leftExtender",
            100,
            0,
            2500,
            300,
            DCMotorConfig.MotorDirection.FORWARD,
            100,
            100,
            200,
            300,
            400
    );


    // DCMotor
    public static DCMotorConfig ARM_EXTENDER_RIGHT = new DCMotorConfig(
            "rightExtender",
            100,
            0,
            2500,
            300,
            DCMotorConfig.MotorDirection.REVERSE,
            100,
            100,
            200,
            300,
            400
    );

    public static DCMotorConfig Left_Elevator = new DCMotorConfig(
            "leftElevator",
            100,
            0,
            1000,
            300,
            DCMotorConfig.MotorDirection.REVERSE,
            100,
            100,
            500,
            800,
            900
    );

    public static DCMotorConfig Right_Elevator = new DCMotorConfig(
            "rightElevator",
            100,
            0,
            1000,
            300,
            DCMotorConfig.MotorDirection.FORWARD,
            100,
            100,
            500,
            800,
            900
    );

    // Servo
    public static DCMotorConfig CLAW_MOTOR = new DCMotorConfig(
            "claw",
            0,
            0,
            1,
            0.55,
            DCMotorConfig.MotorDirection.FORWARD,
            10,
            10,
            0.25,
            0.5,
            0.75
    );

    // CrServo
    public static CRServoConfig INTAKE = new CRServoConfig(
            "intake",
            1,
            1,
            -1
    );

    public static LimelightConfig CAMERA = new LimelightConfig(
            "limelight"
    );


}


