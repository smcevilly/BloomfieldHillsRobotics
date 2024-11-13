package org.coding.cobra;

import android.content.SharedPreferences;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.coding.cobra.ext.ServoMotorControllerEx;

import org.coding.cobra.ext.MoveToPresetAction;

@Config
public abstract class AbstractCobraAutoSpecimen extends CobraBase  {

    TrajectoryActionBuilder trajectoryMoveCloserToBar;
    TrajectoryActionBuilder straffeObject1OnGround;

    public void operateRunMode() {

        // hold the initial object
        flexiClawLeft.handlePresets(1);
        flexiClawRight.handlePresets(1);

        Action actionMoveCloserToBar = trajectoryMoveCloserToBar.build();
        waitForStart();

        clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);
        clawRotator.handlePresets(0);

        telemetry.addData("Extending Arm ",  mecanumDrive.pose);
        telemetry.update();


        Actions.runBlocking(
                new SequentialAction(
                        actionMoveCloserToBar,
                        new MoveToPresetAction(leftElevator, rightElevator, 2, 2),
                        new MoveToPresetAction(armExtenderMotor, 2),
                        new SleepAction(0.7),
                        new MoveToPresetAction(leftElevator, rightElevator, 3, 3),
                        new SleepAction(0.3),
                        new MoveToPresetAction(armExtenderMotor, 3),
                        new SleepAction(0.3),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 0, 0)
                )
        );

        telemetryOutput ();
        //Return to home position

        Actions.runBlocking(
                new SequentialAction(
                        new SleepAction(0.2),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0),
                        new MoveToPresetAction(clawRotator, 0)

        ));

        telemetryOutput ();


        Action pushSample = straffeObject1OnGround.fresh()
                .build();

        Action trajectoryActionPushSample;
        trajectoryActionPushSample = straffeObject1OnGround.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionPushSample //
                )
        );

        /*
        // Pickup the object from ground

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0), // level down
                        new MoveToPresetAction(clawRotator, 1), // rotate claw to pickup
                        new SleepAction(1.5),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight, 1,1), // pickup
                        new MoveToPresetAction(leftElevator, rightElevator, 1, 1),  // level up
                        new MoveToPresetAction(clawRotator, 0) // rotate claw to face straing
                )
        );
       */

        telemetry.addData("Resetting to postion", "");
        telemetry.update();
        telemetryOutput ();

        leftElevator.resetToZeroPosition();
        rightElevator.resetToZeroPosition();


        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Persisting position:

        //Pose2d = mecanumDrive.getPoseEstimate()
        mecanumDrive.updateRobotPose();

        editor.putFloat("x", (float)mecanumDrive.pose.position.x);
        editor.putFloat("y", (float)mecanumDrive.pose.position.y);
        editor.putFloat("heading", (float)mecanumDrive.pose.heading.toDouble());

        editor.apply();

        while (opModeIsActive() && !isStopRequested()) {

        }

    }
}