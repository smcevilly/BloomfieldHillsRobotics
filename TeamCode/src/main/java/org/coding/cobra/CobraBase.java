package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.coding.cobra.config.LimelightConfig;
import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public  abstract class CobraBase extends LinearOpMode {

    SystemConfig sysConfig = new SystemConfig();

    MecanumDriveEx mecanumDrive;
    DCMotorControllerEx armExtenderMotor;

    ServoMotorControllerEx clawRotator, flexiClawLeft, flexiClawRight;
    LimelightEx camera;
    //DCMotorControllerEx armExtenderMotor;
    //ServoMotorControllerEx claw;
    DCMotorControllerEx leftElevator;
    DCMotorControllerEx rightElevator;


    public void initialize () {
        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, sysConfig.ROBOT_START_POSITION);
        leftElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Left_Elevator);
        rightElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Right_Elevator);
        armExtenderMotor = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.ARM_EXTENDER);
        clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);
        flexiClawLeft = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_L);
        flexiClawRight = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_R);
        camera = new LimelightEx(hardwareMap, telemetry,  sysConfig.CAMERA);
        camera.init();
    }

}
