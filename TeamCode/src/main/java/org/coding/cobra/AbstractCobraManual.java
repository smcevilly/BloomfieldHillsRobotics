package org.coding.cobra;


import org.coding.cobra.ext.MoveToPresetAction;
import org.coding.cobra.ext.ServoMotorControllerEx;

public abstract class  AbstractCobraManual extends CobraBase {


    public void executeOpMode() throws InterruptedException {

        waitForStart();

        clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);

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

            //botpose = camera.getRobotPosition();
            if (isStopRequested()) return;
            telemetryOutput();
        }

    }


    public void handleGamepadEvents () {
        // Apply desired axes motions to the drivetrain.
        mecanumDrive.moveRobot(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x,sysConfig.DRIVE_POWER_FACTOR);
        leftElevator.handleEvents(-gamepad2.left_stick_y);
        rightElevator.handleEvents(-gamepad2.left_stick_y);
        hangingArm.handleEvents(gamepad1.dpad_up, gamepad1.dpad_down);
        //claw.handlePresets(gamepad2.a, false, false);
        armExtenderMotor.handleEvents(-gamepad2.right_stick_y);

        flexiClawLeft.handleEvents(gamepad2.dpad_left, gamepad2.dpad_right);
        flexiClawRight.handleEvents(gamepad2.dpad_left, gamepad2.dpad_right);
        clawRotator.handleEvents(gamepad2.dpad_down, gamepad2.dpad_up);


        // presets
//       clawRotator.handlePresets(gamepad2.x, gamepad2.b, false, false, false);
//        leftElevator.handlePresets(gamepad2.a, gamepad2.y, false, false, false);
//        rightElevator.handlePresets(gamepad2.a, gamepad2.y, false, false, false);


        clawRotator.handlePresets(gamepad2.x, gamepad2.b, false, false, false, false, false);

        // old presets
        //leftElevator.handlePresets(gamepad2.left_bumper, gamepad2.right_bumper, gamepad2.right_trigger>0, gamepad2.left_trigger>0, gamepad2.left_bumper, false, false);
        //rightElevator.handlePresets(gamepad2.left_bumper, gamepad2.right_bumper, gamepad2.right_trigger>0, gamepad2.left_trigger>0, gamepad2.left_bumper, false, false);

        // new presets
        leftElevator.handlePresets(false, false, gamepad2.right_trigger>0, gamepad2.left_trigger>0, false, gamepad2.left_bumper, gamepad2.right_bumper);
        rightElevator.handlePresets(false, false, gamepad2.right_trigger>0, gamepad2.left_trigger>0, false, gamepad2.left_bumper, gamepad2.right_bumper);

        if (gamepad2.start) {
            tracePathToBar(AUTO_CONFIG.getTracePathToBarTrajectory(mecanumDrive, false).build(), false);
            automationSpecimenHang(true, false);
        }

        if (gamepad2.share) {
            clawRotator.handlePresets(1);
            automationPickup(true);
        }

        //  camera.handleEvents(gamepad1.dpad_left, gamepad1.dpad_right);

    }

    public void resetRobot () {
        telemetry.addData("wait", "all encoders are getting reset");
        armExtenderMotor.resetEncoders();
        rightElevator.resetEncoders();
        leftElevator.resetEncoders();
        hangingArm.resetEncoders();
    }

}
