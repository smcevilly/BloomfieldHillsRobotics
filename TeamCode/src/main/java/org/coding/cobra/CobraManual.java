package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;

@TeleOp(name = "Manual Cobra")
public class CobraManual extends LinearOpMode {

    SystemConfig sysConfig = new SystemConfig();

    MecanumDriveEx mecanumDrive;
    //DCMotorControllerEx armExtenderMotor;
    //ServoMotorControllerEx claw;
    DCMotorControllerEx leftElevator;
    DCMotorControllerEx rightElevator;


    public void initialize () {
        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, sysConfig.ROBOT_START_POSITION);
        leftElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Left_Elevator);
        rightElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Right_Elevator);
        leftElevator.resetEncoders();
        rightElevator.resetEncoders();
        //claw = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_MOTOR);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();
        //This is telling the robot to wait until start is clicked on the driver hub.

        // Continuous loop
        while (opModeIsActive()) {
             /*
                Handle the events on gamepad
                 */
            handleGamepadEvents();
            if (isStopRequested()) return;
        }
    }


    public void handleGamepadEvents () {
        // Apply desired axes motions to the drivetrain.
        mecanumDrive.moveRobot(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x,sysConfig.DRIVE_POWER_FACTOR);
        leftElevator.handleEvents(gamepad2.left_stick_y);
        rightElevator.handleEvents(gamepad2.left_stick_y);
        //claw.handleEvents(gamepad2.dpad_up, gamepad2.dpad_down);
        //claw.handlePresets(gamepad2.a, false, false);
    }

}
