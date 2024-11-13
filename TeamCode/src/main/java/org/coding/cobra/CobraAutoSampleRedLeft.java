package org.coding.cobra;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.ext.MoveToPresetAction;

@Config
@Autonomous(name = "Cobra Auto Sample Red Left", group = "Autonomous")
public class CobraAutoSampleRedLeft extends CobraBase  {

    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(-12.50, -62.00, Math.toRadians(90.00));

        loadPersistance();

        initialize(startPosition);

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder tab1 = mecanumDrive.actionBuilder(startPosition)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(220)), Math.toRadians(280));



        TrajectoryActionBuilder tab2 = mecanumDrive.actionBuilder(new Pose2d(-52, -52, Math.toRadians(220)))
                .setTangent(Math.toRadians(90))
              .lineToYLinearHeading(40,Math.toRadians(90))
              .waitSeconds(3);
/*
*/

                Action trajectoryActionPlaceSpecimen = tab1.fresh()

                        .build();

        Action trajectoryActionMoveObjects = tab2.fresh()

                .build();

        // actions that need to happen on init; for instance, a claw tightening.
        //Actions.runBlocking(claw.closeClaw());

        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Initial Position : Holding the Object at position ", mecanumDrive.pose);
            telemetry.update();

            // hold the initial object
            flexiClawLeft.handlePresets(1);
            flexiClawRight.handlePresets(1);
            clawRotator.handlePresets(2);
        }

        telemetry.addData("Determining the Move ",  mecanumDrive.pose);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;
        trajectoryActionChosen = tab1.build();

       // Action trajectoryActionChosen2;
       // trajectoryActionChosen2 = tab2.build();



        telemetry.addData("Moving the robot ",  mecanumDrive.pose);
        telemetry.update();

        Actions.runBlocking(
                new ParallelAction(
                        trajectoryActionChosen//,
                        //new MoveToPresetAsync(armExtenderMotor, 2)
                        //lift.liftUp(),
                        //claw.openClaw(),
                        //lift.liftDown()
                )
        );

        telemetry.addData("Lifting Action ",  mecanumDrive.pose);
        telemetry.update();


        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 1, 1),
                        new MoveToPresetAction(armExtenderMotor, 1)
                        //lift.liftUp(),
                        //claw.openClaw(),
                        //lift.liftDown()
                )
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        telemetry.addData("Dropping the object ",  mecanumDrive.pose);
        telemetry.update();

        flexiClawLeft.handlePresets(true, false, false, false, false, false, false);
        flexiClawRight.handlePresets(true, false, false, false, false, false, false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0)
                        //lift.liftUp(),
                        //claw.openClaw(),
                        //lift.liftDown()
                )
        );
      /*  Actions.runBlocking(
                new ParallelAction(
                        trajectoryActionMoveObjects//,


                )
        );

       */



        while (opModeIsActive()) {

        }

    }
}