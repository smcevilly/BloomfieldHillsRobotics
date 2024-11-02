package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;

public abstract class  AbstractCobraManual extends CobraBase {

    public abstract void automationSpecimenHang();

    public void executeOpMode() throws InterruptedException {

        waitForStart();


        //This is telling the robot to wait until start is clicked on the driver hub.

        // Continuous loop
        while (opModeIsActive()) {

            if (gamepad1.share) {
                resetRobot();
                return;
            }

             /*
                Handle the events on gamepad
                 */
            handleGamepadEvents();

            botpose = camera.getRobotPosition();
            if (isStopRequested()) return;
            telemetryOutput();
        }

        // Bring to home position

        //armExtenderMotor.shutdown();
        //leftElevator.shutdown();
        //rightElevator.shutdown();

        //Thread.sleep(3000);

    }


    public void handleGamepadEvents () {
        // Apply desired axes motions to the drivetrain.
        mecanumDrive.moveRobot(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x,sysConfig.DRIVE_POWER_FACTOR);
        leftElevator.handleEvents(-gamepad2.left_stick_y);
        rightElevator.handleEvents(-gamepad2.left_stick_y);
        //claw.handleEvents(gamepad2.dpad_up, gamepad2.dpad_down);
        //claw.handlePresets(gamepad2.a, false, false);
        armExtenderMotor.handleEvents(-gamepad2.right_stick_y);

        flexiClawLeft.handleEvents(gamepad2.dpad_left, gamepad2.dpad_right);
        flexiClawRight.handleEvents(gamepad2.dpad_left, gamepad2.dpad_right);
        clawRotator.handleEvents(gamepad2.dpad_up, gamepad2.dpad_down);


        // presets
//       clawRotator.handlePresets(gamepad2.x, gamepad2.b, false, false, false);
//        leftElevator.handlePresets(gamepad2.a, gamepad2.y, false, false, false);
//        rightElevator.handlePresets(gamepad2.a, gamepad2.y, false, false, false);


        clawRotator.handlePresets(gamepad2.x, gamepad2.b, false, false, false);
        leftElevator.handlePresets(gamepad2.left_bumper, gamepad2.right_bumper, gamepad2.right_trigger>0, gamepad2.left_trigger>0, gamepad2.left_bumper);
        rightElevator.handlePresets(gamepad2.left_bumper, gamepad2.right_bumper, gamepad2.right_trigger>0, gamepad2.left_trigger>0, gamepad2.left_bumper);

        if (gamepad2.start) {
            automationSpecimenHang();
        }

        //  camera.handleEvents(gamepad1.dpad_left, gamepad1.dpad_right);

    }

    public void resetRobot () {
        telemetry.addData("wait", "all encoders are getting reset");
        armExtenderMotor.resetEncoders();
        rightElevator.resetEncoders();
        leftElevator.resetEncoders();
    }

    public void telemetryOutput () {
        armExtenderMotor.outputTelemetry();
        leftElevator.outputTelemetry();
        rightElevator.outputTelemetry();
        clawRotator.outputTelemetry();
        flexiClawLeft.outputTelemetry();
        flexiClawRight.outputTelemetry();
        telemetry.addData("Pos  x:", mecanumDrive.pose.position.x + " y:" + mecanumDrive.pose.position.y + " heading:" + mecanumDrive.pose.heading.toDouble());
        if (botpose != null) {
            telemetry.addData("Botpose ", botpose.toString());
        }
        telemetry.update();
    }



}
