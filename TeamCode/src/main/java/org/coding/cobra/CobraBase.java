package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;

public  abstract class CobraBase extends LinearOpMode {

    SystemConfig sysConfig = new SystemConfig();

    MecanumDriveEx mecanumDrive;
    DCMotorControllerEx armExtenderMotorRight;

    ServoMotorControllerEx clawRotator, flexiClawLeft, flexiClawRight;
    LimelightEx cameclaw,ra;
    //DCMotorControllerEx armExtenderMotor;
    //ServoMotorControllerEx claw;
    DCMotorControllerEx leftElevator;
    DCMotorControllerEx rightElevator;

    public void initialize () {
        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, sysConfig.ROBOT_START_POSITION);
        leftElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Left_Elevator);
        rightElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Right_Elevator);
        //leftElevator.resetEncoders();
        //rightElevator.resetEncoders();
        //claw = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_MOTOR);
        armExtenderMotorRight = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.ARM_EXTENDER_RIGHT);
        clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);
        flexiClawLeft = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_L);
        flexiClawRight = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_R);
    }

}
