package org.coding.cobra;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.coding.cobra.ext.ServoMotorControllerEx;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MoveToPresetAction;

@Config
public abstract class CobraAutoSpecimen extends CobraBase  {

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
                        new SleepAction(1.5),
                        new MoveToPresetAction(leftElevator, rightElevator, 3, 3),
                        new SleepAction(1),
                        new MoveToPresetAction(armExtenderMotor, 3),
                        new SleepAction(0.5),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 0, 0)
                )
        );

        //Return to home position

        Actions.runBlocking(
                new SequentialAction(
                        new SleepAction(0.5),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0)
                )
        );



        Action pushSample = straffeObject1OnGround.fresh()

                .build();

        Action trajectoryActionPushSample;
        trajectoryActionPushSample = straffeObject1OnGround.build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionPushSample //
                )
        );

        telemetry.addData("Resetting to postion", "");
        telemetry.update();

        leftElevator.resetToZeroPosition();
        rightElevator.resetToZeroPosition();

        while (opModeIsActive() && !isStopRequested()) {

        }

    }
}