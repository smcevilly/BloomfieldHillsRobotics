package org.coding.cobra;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MoveToPresetAsync;

@Config
@Autonomous(name = "Cobra Auto Specimen", group = "Autonomous")
public class CobraAutoSpecimen extends CobraBase  {

    @Override
    public void runOpMode() {

        initialize();;

        // hold the initial object
        clawRotator.handlePresets(0);
        flexiClawLeft.handlePresets(1);
        flexiClawRight.handlePresets(1);


        TrajectoryActionBuilder trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(SystemConfig.ROBOT_START_POSITION_FOR_RED_SPECIMEN)
                .lineToY(-40);

        Action actionMoveCloserToBar = trajectoryMoveCloserToBar.fresh()
                .build();


        telemetry.addData("Determining the Move ",  mecanumDrive.pose);
        telemetry.update();
        waitForStart();

        Action trajectoryActionChosen;
        trajectoryActionChosen = trajectoryMoveCloserToBar.build();




        telemetry.addData("Extending Arm ",  mecanumDrive.pose);
        telemetry.update();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen,
                        new MoveToPresetAsync(leftElevator, rightElevator, 2, 2),
                        new MoveToPresetAsync(armExtenderMotor, 2),
                        new SleepAction(2),
                        new MoveToPresetAsync(leftElevator, rightElevator, 3, 3),
                        new SleepAction(2),
                        new MoveToPresetAsync(armExtenderMotor, 3),
                        new SleepAction(2)

                )
        );

        flexiClawLeft.handlePresets(true, false, false, false, false);
        flexiClawRight.handlePresets(true, false, false, false, false);

        telemetry.addData("Dropping the object ",  mecanumDrive.pose);
        telemetry.update();


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAsync(armExtenderMotor, 0),
                        new MoveToPresetAsync(leftElevator, rightElevator, 0, 0)
                )
        );



        TrajectoryActionBuilder tab2 = mecanumDrive.actionBuilder(new Pose2d(12.5, -40, Math.toRadians(90)))
                .strafeTo(new Vector2d(36, -40))
                .setTangent(Math.toRadians(90))
                .lineToY(-10)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(45, -10))
                .setTangent(Math.toRadians(90))
                .lineToY(-55);

        Action pushSample = tab2.fresh()

                .build();

        Action trajectoryActionPushSample;
        trajectoryActionPushSample = tab2.build();



        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionPushSample //
                )
        );




        while (opModeIsActive() && !isStopRequested()) {

        }

    }
}