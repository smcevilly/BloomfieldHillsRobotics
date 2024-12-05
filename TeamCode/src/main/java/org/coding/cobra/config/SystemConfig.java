package org.coding.cobra.config;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;

import org.coding.cobra.config.helpers.CRServoConfig;
import org.coding.cobra.config.helpers.DCMotorConfig;
import org.coding.cobra.config.helpers.LimelightConfig;

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

    public int [] armExtenderPlacement = {1300, 1400, 1200};




    // DCMotor
    public static DCMotorConfig HANGING_ARM = new DCMotorConfig(
            "hangingArm",
            0,
            0,
            2600,
            200,
            DCMotorConfig.MotorDirection.REVERSE,
            2,
            2,
            0,
            1400,
            1600,
            600,
            1700,
            375,
            500
    );
    // DCMotor
    public static DCMotorConfig ARM_EXTENDER = new DCMotorConfig(
            "rightExtender",
            0,
            0,
            2600,
            600,
            DCMotorConfig.MotorDirection.REVERSE,
            2,
            2,
            0,
            1400,          // automation v2 object hang
            1600,                  // automation v2 object hang for second time
            600,                   // autonomous object hang complete
            1700,                  // automation hang on bar
            375,                  // automation v2 object pickup from human player
            500          //
    );

    public static DCMotorConfig Left_Elevator = new DCMotorConfig(
            "leftElevator",
            450,
            250,
            5700,
            600,
            DCMotorConfig.MotorDirection.REVERSE,
            1,
            2,
            700,            // preset for pickup
            2500,                   // preset for autonomous drop   // 5650
            2600,                   // preset for autonomous second drop   // 5650
            3050,                   // autonomous bar hang pull level
            5600,
            1550,           // autonomous pickup specimen from wall
            2850                    // autonomous elevator drop height

            //2424

    );

    public static DCMotorConfig Right_Elevator = new DCMotorConfig(
            "rightElevator",
            500,
            250,
            5700,
            600 ,
            DCMotorConfig.MotorDirection.FORWARD,
            2,
            2,
            700,
            2500,           //preset for autonomous drop
            2600,                   // preset for autonomous second drop   // 5650
            3050,
            5600,
            1550,           // autonomous pickup specimen from wall
            2850                    // autonomous elevator drop height

    );

    // Servo
    public static DCMotorConfig CLAW_ROTATOR = new DCMotorConfig(
            "clawRotator",
            0.2,
            0.07,
            1,
            0.01,
            DCMotorConfig.MotorDirection.FORWARD,
            5,
            5,
            0.34,// face straight - little tilt
            0.65,
            0.1,
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
            "limelight",
            0,
            2.1
    );


}


