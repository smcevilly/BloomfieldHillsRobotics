package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.CRServoControllerEx;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name = "Manual Cobra")
public class CobraManual extends CobraBase {

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

            Pose3D botpose = camera.getRobotPosition();
            if (botpose != null) {
                telemetry.addData("Botpose ", botpose.toString());
            }

            if (isStopRequested()) return;
            telemetry.update();
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
        clawRotator.handlePresets(gamepad2.x, gamepad2.b, false);
        leftElevator.handlePresets(gamepad2.a, gamepad2.y, false);
        rightElevator.handlePresets(gamepad2.a, gamepad2.y, false);

     //  camera.handleEvents(gamepad1.dpad_left, gamepad1.dpad_right);

    }

    public void resetRobot () {
        telemetry.addData("wait", "all encoders are getting reset");
        armExtenderMotor.resetEncoders();
        rightElevator.resetEncoders();
        leftElevator.resetEncoders();
    }

}
