package org.coding.cobra.test;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MecanumDriveEx;

@TeleOp(name = "Auto Test")
public class AutonomousDriveTest extends LinearOpMode {

    MecanumDriveEx mecanumDrive;
    SystemConfig sysConfig = new SystemConfig();


    @Override
    public void runOpMode() throws InterruptedException {

        Pose2d startPos = new Pose2d(0, 0, 0);
        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, startPos);

        waitForStart();
        //This is telling the robot to wait until start is clicked on the driver hub.

        // run once
        moveToPosition();

        // Continuous loop
        while (opModeIsActive()) {
             /*
                Handle the events on gamepad
                 */


            handleGamepadEvents (gamepad1);

            if (isStopRequested()) return;

        }


    }

    public void moveToPosition () {

        /**
         * Sample code to move robot
         */

        Action TrajectoryAction1 = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .lineToX(20)
                .build();

        Action TrajectoryAction2 = mecanumDrive.actionBuilder(new Pose2d(15,20,0))
                .splineTo(new Vector2d(5,5), Math.toRadians(90))
                .build();


        // Step 1 : Linear Motion

        Actions.runBlocking(

                new SequentialAction(
                        TrajectoryAction1, // Example of a drive action

                        new Action() {
                            @Override
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        }

                )



        );

        return;

        /*

        // Step 2 : Complex Motion
            Actions.runBlocking(

                new SequentialAction(
                        TrajectoryAction1, // Example of a drive action

                        // This action and the following action do the same thing
                        new Action() {
                            @Override
                            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                                telemetry.addLine("Action!");
                                telemetry.update();
                                return false;
                            }
                        },
                        // Only that this action uses a Lambda expression to reduce complexity
                        (telemetryPacket) -> {
                            telemetry.addLine("Action!");
                            telemetry.update();
                            return false; // Returning true causes the action to run again, returning false causes it to cease
                        },
                        new ParallelAction( // several actions being run in parallel
                                TrajectoryAction2, // Run second trajectory
                                (telemetryPacket) -> { // Run some action
                                    //motor1.setPower(1);
                                    return false;
                                }
                        ),
                        mecanumDrive.actionBuilder(new Pose2d(15,10,Math.toRadians(125))) // Another way of running a trajectory (not recommended because trajectories take time to build and will slow down your code, always try to build them beforehand)
                                .splineTo(new Vector2d(25, 15), 0)
                                .build()

                )
        );

         */

    }

    public void handleGamepadEvents (Gamepad gamePad) {

        // Apply desired axes motions to the drivetrain.
        mecanumDrive.moveRobot(-gamePad.left_stick_y, -gamePad.left_stick_x, -gamePad.right_stick_x,sysConfig.DRIVE_POWER_FACTOR);
    }
}
