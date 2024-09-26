package org.coding.cobra.test;


import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MecanumDriveEx;

/*
 * Simple mecanum drive hardware implementation for REV hardware.
 */
@TeleOp(name = "Mecanum Drive Test")
public class MecanumDriveTest extends LinearOpMode {

    MecanumDriveEx mecanumDrive;
    SystemConfig sysConfig = new SystemConfig();


    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d startPos = new Pose2d(0, 0, 0);
        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, startPos);

        waitForStart();
        //This is telling the robot to wait until start is clicked on the driver hub.


        // Continuous loop
        while (opModeIsActive()) {
             /*
                Handle the events on gamepad
                 */
            handleGamepadEvents (gamepad1);
            if (isStopRequested()) return;
        }


    }

    public void handleGamepadEvents (Gamepad gamePad) {

        // Apply desired axes motions to the drivetrain.
        mecanumDrive.moveRobot(-gamePad.left_stick_y, -gamePad.left_stick_x, -gamePad.right_stick_x,sysConfig.DRIVE_POWER_FACTOR);
    }

}
