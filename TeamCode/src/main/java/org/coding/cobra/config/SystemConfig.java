package org.coding.cobra.config;

import com.acmerobotics.roadrunner.Pose2d;

import org.coding.cobra.config.helpers.CRServoConfig;
import org.coding.cobra.config.helpers.DCMotorConfig;

/**
 * Class where we will populate system configuration such as motor names
 * motor types, limits, etc
 */

public class SystemConfig {

    public static Pose2d ROBOT_START_POSITION = new Pose2d(11.8, 61.7, Math.toRadians(0));
    public static final double DRIVE_POWER_FACTOR = 1;

    // DCMotor


    // DCMotor
    public static DCMotorConfig ARM_EXTENDER = new DCMotorConfig(
            "rightExtender",
            0,
            0,
            2500,
            500,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            1,
            0,
            2500,
            2000
    );

    public static DCMotorConfig Left_Elevator = new DCMotorConfig(
            "leftElevator",
            500,
            500,
            5700,
            500,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            1,
            529,
            5700,
            3000
    );

    public static DCMotorConfig Right_Elevator = new DCMotorConfig(
            "rightElevator",
            500,
            500,
            5700,
            500 ,
            DCMotorConfig.MotorDirection.FORWARD,
            1,
            1,
            529,
            5700,
            900
    );

    // Servo
    public static DCMotorConfig CLAW_ROTATOR = new DCMotorConfig(
            "clawRotator",
            1,
            0.04,
            1,
            0.001,
            DCMotorConfig.MotorDirection.FORWARD,
            5,
            5,
            0.5,
            0.075,
            0.5
    );


    // Servo
    public static DCMotorConfig FLEXI_CLAW_MOTOR_L = new DCMotorConfig(
            "flexiClawLeft",
            0.7,
            0.42,
            0.85,
            0.001,
            DCMotorConfig.MotorDirection.FORWARD,
            10,
            10,
            0.84,   // open
            0.5,           // hold
            0.75
    );

    // Servo
    public static DCMotorConfig FLEXI_CLAW_MOTOR_R = new DCMotorConfig(
            "flexiClawRight",
            0.7,
            0.42,
            0.85,
            0.001,
            DCMotorConfig.MotorDirection.REVERSE,
            10,
            10,
            0.84,   // open
            0.5,           // hold
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


