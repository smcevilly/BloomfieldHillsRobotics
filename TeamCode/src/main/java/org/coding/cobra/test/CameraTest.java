package org.coding.cobra.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.CRServoControllerEx;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.ServoMotorControllerEx;

@TeleOp(name = "Camera Test")
public class CameraTest extends LinearOpMode {
    LimelightEx camera;
    SystemConfig sysConfig = new SystemConfig();

    public void initialize () {
        camera = new LimelightEx(hardwareMap, telemetry, sysConfig.CAMERA);
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
        // Allow for changing pipeline
        camera.handleEvents(gamepad1.dpad_up, gamepad1.dpad_down, gamepad1.dpad_left);
    }

}
