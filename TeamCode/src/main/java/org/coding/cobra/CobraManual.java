package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.CRServoControllerEx;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;

@TeleOp(name = "Manual Cobra")
public class CobraManual extends LinearOpMode {

    SystemConfig sysConfig = new SystemConfig();

    MecanumDriveEx mecanumDrive;
    DCMotorControllerEx armExtenderMotorLeft;
    DCMotorControllerEx armExtenderMotorRight;

    ServoMotorControllerEx claw, flexiClawLeft, flexiClawRight;
    CRServoControllerEx intake;
    LimelightEx camera;
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
        armExtenderMotorLeft = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.ARM_EXTENDER_LEFT);
        armExtenderMotorRight = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.ARM_EXTENDER_RIGHT);
        claw = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_MOTOR);
        flexiClawLeft = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_L);
        flexiClawRight = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_R);

        intake = new CRServoControllerEx(hardwareMap, telemetry, sysConfig.INTAKE);
       // camera = new LimelightEx(hardwareMap, telemetry, sysConfig.CAMERA);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        waitForStart();


        //This is telling the robot to wait until start is clicked on the driver hub.

        // Continuous loop
        while (opModeIsActive()) {

            if (gamepad1.right_bumper) {
                resetRobot();
                return;
            }

             /*
                Handle the events on gamepad
                 */
            handleGamepadEvents();
            if (isStopRequested()) return;
            telemetry.update();
        }
    }


    public void handleGamepadEvents () {
        // Apply desired axes motions to the drivetrain.
        mecanumDrive.moveRobot(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x,sysConfig.DRIVE_POWER_FACTOR);
        leftElevator.handleEvents(-gamepad2.left_stick_y);
        rightElevator.handleEvents(-gamepad2.left_stick_y);
        //claw.handleEvents(gamepad2.dpad_up, gamepad2.dpad_down);
        //claw.handlePresets(gamepad2.a, false, false);
        armExtenderMotorLeft.handleEvents(-gamepad2.right_stick_y);
        armExtenderMotorRight.handleEvents(-gamepad2.right_stick_y);

        flexiClawLeft.handleEvents(gamepad2.dpad_down, gamepad2.dpad_up);
        flexiClawRight.handleEvents(gamepad2.dpad_down, gamepad2.dpad_up);

        claw.handleEvents(gamepad1.dpad_down, gamepad1.dpad_up);
        claw.handlePresets(gamepad1.a, false, false);




        intake.handlePresets(gamepad2.right_bumper, gamepad2.left_bumper);
      //  camera.handleEvents(gamepad1.dpad_left, gamepad1.dpad_right);

    }

    public void resetRobot () {
        telemetry.addData("wait", "all encoders are getting reset");
        armExtenderMotorRight.resetEncoders();
        armExtenderMotorLeft.resetEncoders();
        rightElevator.resetEncoders();
        leftElevator.resetEncoders();
    }

}
