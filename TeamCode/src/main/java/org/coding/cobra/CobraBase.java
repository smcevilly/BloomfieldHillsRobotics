package org.coding.cobra;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.coding.cobra.config.LimelightConfig;
import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public  abstract class CobraBase extends LinearOpMode {


    Pose3D botpose;
    SystemConfig sysConfig = new SystemConfig();

    MecanumDriveEx mecanumDrive;
    DCMotorControllerEx armExtenderMotor;

    ServoMotorControllerEx clawRotator, flexiClawLeft, flexiClawRight;
    LimelightEx camera;
    //DCMotorControllerEx armExtenderMotor;
    //ServoMotorControllerEx claw;
    DCMotorControllerEx leftElevator;
    DCMotorControllerEx rightElevator;

    public SharedPreferences sharedPreferences;

    public void loadPersistance () {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);
    }


    public void initialize (Pose2d startPosition) {


        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, startPosition);
        leftElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Left_Elevator);
        rightElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Right_Elevator);
        armExtenderMotor = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.ARM_EXTENDER);
        // To delay the start of the claw rotator movement after init is pressed
        //
        // clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);
        flexiClawLeft = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_L);
        flexiClawRight = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_R);
        camera = new LimelightEx(hardwareMap, telemetry,  sysConfig.CAMERA);
        camera.init();
    }

    public void telemetryOutput () {
        armExtenderMotor.outputTelemetry();
        leftElevator.outputTelemetry();
        rightElevator.outputTelemetry();
        clawRotator.outputTelemetry();
        flexiClawLeft.outputTelemetry();
        flexiClawRight.outputTelemetry();

        mecanumDrive.updateRobotPose();
        telemetry.addData("Pos  x:",
                mecanumDrive.pose.position.x + " y:" + mecanumDrive.pose.position.y + " heading:" + mecanumDrive.pose.heading.toDouble() + " (deg) " +Math.toDegrees( mecanumDrive.pose.heading.toDouble()));
        if (botpose != null) {
            telemetry.addData("Botpose ", botpose.toString());
        }
        telemetry.update();
    }


}
