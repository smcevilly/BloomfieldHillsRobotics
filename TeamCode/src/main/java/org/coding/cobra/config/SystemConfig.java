package org.coding.cobra.config;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;

import org.coding.cobra.config.helpers.CRServoConfig;
import org.coding.cobra.config.helpers.DCMotorConfig;

/**
 * Class where we will populate system configuration such as motor names
 * motor types, limits, etc
 */
@Config
public class SystemConfig {

//    public static Pose2d ROBOT_START_POSITION = new Pose2d(11.8, 61.7, Math.toRadians(0));

    public static Pose2d ROBOT_START_POSITION = new Pose2d(-12.50, -62.00, Math.toRadians(90.00));

    public static final double DRIVE_POWER_FACTOR = 1;

    // DCMotor


    // DCMotor
    public static DCMotorConfig ARM_EXTENDER = new DCMotorConfig(
            "rightExtender",
            0,
            0,
            2600,
            600,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            1,
            0,
            2600,
            1651,        // autonomous reach bar
            600,                // autonomous object hang complete
            1900,               // automation hang on bar
            0,
            0
    );

    public static DCMotorConfig Left_Elevator = new DCMotorConfig(
            "leftElevator",
            450,
            250,
            5700,
            600,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            1,
            700,            // preset for pickup
            5650,                   // preset for autonomous drop
            3468,                   // autonomous high bar
            3200,                   // autonomous bar hang pull level
            548,
            1900,           // autonomous pickup specimen from wall
            2850                    // autonomous elevator drop height

    );

    public static DCMotorConfig Right_Elevator = new DCMotorConfig(
            "rightElevator",
            500,
            250,
            5700,
            600 ,
            DCMotorConfig.MotorDirection.FORWARD,
            1,
            1,
            700,
            5650,
            3468,        // autonomous high bar
            3200,
            548,
            1900,           // autonomous pickup specimen from wall
            2850                    // autonomous elevator drop height

    );

    // Servo
    public static DCMotorConfig CLAW_ROTATOR = new DCMotorConfig(
            "clawRotator",
            0.8,
            0.07,
            1,
            0.02,
            DCMotorConfig.MotorDirection.FORWARD,
            5,
            5,
            0.6,// face straight
            0.28,
            0.75,
            1,
            0,
            0,
            0

    );
//

    // Servo
    public static DCMotorConfig FLEXI_CLAW_MOTOR_L = new DCMotorConfig(
            "flexiClawLeft",
            0.7,
            0.32,
            1,
            0.025,
            DCMotorConfig.MotorDirection.FORWARD,
            5,
            5,
            0.84,   // open
            0.3,           // hold
            0.75,
            0,
            0,
            0,
            0


    );

    // Servo
    public static DCMotorConfig FLEXI_CLAW_MOTOR_R = new DCMotorConfig(
            "flexiClawRight",
            0.7,
            0.32,
            1,
            0.025,
            DCMotorConfig.MotorDirection.REVERSE,
            8,
            10,
            0.84,   // open
            0.3,           // hold
            0.75,
            0,
            0,
            0,
            0

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


