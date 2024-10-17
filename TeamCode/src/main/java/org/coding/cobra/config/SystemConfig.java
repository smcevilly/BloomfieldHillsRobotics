package org.coding.cobra.config;

import com.acmerobotics.roadrunner.Pose2d;

import org.coding.cobra.config.helpers.CRServoConfig;
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
            0,
            0,
            2200,
            800,
            DCMotorConfig.MotorDirection.FORWARD,
            1,
            1,
            200,
            300,
            400
    );


    // DCMotor
    public static DCMotorConfig ARM_EXTENDER_RIGHT = new DCMotorConfig(
            "rightExtender",
            0,
            0,
            2200,
            800,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            1,
            200,
            300,
            400
    );

    public static DCMotorConfig Left_Elevator = new DCMotorConfig(
            "leftElevator",
            0,
            0,
            6500,
            1500,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            1,
            500,
            800,
            900
    );

    public static DCMotorConfig Right_Elevator = new DCMotorConfig(
            "rightElevator",
            0,
            0,
            6500,
            1500 ,
            DCMotorConfig.MotorDirection.FORWARD,
            1,
            1,
            500,
            800,
            900
    );

    // Servo
    public static DCMotorConfig CLAW_MOTOR = new DCMotorConfig(
            "claw",
            0.04,
            0.04,
            0.78,
            0.001,
            DCMotorConfig.MotorDirection.FORWARD,
            5,
            5,
            0.25,
            0.5,
            0.5
    );


    // Servo
    public static DCMotorConfig FLEXI_CLAW_MOTOR_L = new DCMotorConfig(
            "flexiClawLeft",
            0.7,
            0.2,
            0.85,
            0.005,
            DCMotorConfig.MotorDirection.FORWARD,
            10,
            10,
            0.25,
            0.5,
            0.5
    );

    // Servo
    public static DCMotorConfig FLEXI_CLAW_MOTOR_R = new DCMotorConfig(
            "flexiClawRight",
            0.7,
            0.2,
            0.85,
            0.005,
            DCMotorConfig.MotorDirection.REVERSE,
            10,
            10,
            0.25,
            0.5,
            0.5
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


