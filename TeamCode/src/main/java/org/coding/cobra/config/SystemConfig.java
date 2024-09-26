package org.coding.cobra.config;

import com.acmerobotics.roadrunner.Pose2d;

/**
 * Class where we will populate system configuration such as motor names
 * motor types, limits, etc
 */

public class SystemConfig {

    public static Pose2d ROBOT_START_POSITION = new Pose2d(0, 0, 0);

    public static final double DRIVE_POWER_FACTOR = 1;

    // DCMotor
    public static DCMotorConfig ARM_EXTENDER = new DCMotorConfig(
            "Arm Extender",
            0,
            100,
            1000,
            15,
            DCMotorConfig.MotorDirection.FORWARD,
            1,
            200,
            300,
            400
    );

    // Servo
    public static DCMotorConfig CLAW_MOTOR = new DCMotorConfig(
            "Claw",
            0,
            0,
            1,
            0.55,
            DCMotorConfig.MotorDirection.FORWARD,
            1,
            0.25,
            0.5,
            0.75
    );









}


