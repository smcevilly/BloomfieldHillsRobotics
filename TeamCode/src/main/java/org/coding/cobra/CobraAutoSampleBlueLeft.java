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

import org.coding.cobra.ext.MoveToPresetAction;

@Config
@Autonomous(name = "Cobra Auto Sample Blue Left", group = "Autonomous")
public class CobraAutoSampleBlueLeft extends CobraBase  {

    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(6, 62.00, Math.toRadians(270));

        initialize();

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder tab1 = mecanumDrive.actionBuilder(startPosition)
                .lineToY(41);



        TrajectoryActionBuilder tab2 = mecanumDrive.actionBuilder(new Pose2d(6, 41, Math.toRadians(270)))
                .strafeTo(new Vector2d(48,41));

        TrajectoryActionBuilder tab3 = mecanumDrive.actionBuilder(new Pose2d(40, 41, Math.toRadians(270)))
                .splineTo(new Vector2d(55, 50), Math.toRadians(38));

               // .setTangent(Math.toRadians(90))
            //  .lineToYLinearHeading(40,Math.toRadians(90))
              //.waitSeconds(3);
/*
*/

                Action trajectoryActionPlaceSpecimen = tab1.fresh()

                        .build();

        Action trajectoryActionMoveObjects = tab2.fresh()

                .build();

        Action trajectoryActionPlaceObjects = tab3.fresh()

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
          //  clawRotator.handlePresets(2);
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
                        trajectoryActionChosen
                )
        );

        telemetry.addData("Lifting Action ",  mecanumDrive.pose);
        telemetry.update();


        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 1, 1),
                        new SleepAction(0.7),
                        new MoveToPresetAction(armExtenderMotor, firsthang?1:2),
                        new SleepAction(0.7),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 0, 0),
                        new SleepAction(0.2),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new SleepAction(0.5),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0),
                        new MoveToPresetAction(clawRotator, 1)
                )
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        telemetry.addData("Hooking the object ",  mecanumDrive.pose);
        telemetry.update();

         Action trajectoryActionChosen2;
         trajectoryActionChosen2 = tab2.build();


        Actions.runBlocking(
                new ParallelAction(
                        trajectoryActionChosen2
                )
        );


        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 3, 3),
                        new SleepAction(0.7),
                        new MoveToPresetAction(armExtenderMotor, 1),
                        new SleepAction(0.7),
                        new MoveToPresetAction(clawRotator, 1),
                        new SleepAction(1),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 1, 1),
                        new SleepAction(0.9),
                        new MoveToPresetAction(clawRotator, 1),
                        new SleepAction(0.9),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new SleepAction(0.5),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0)
                )
        );

        Action trajectoryActionChosen3;
        trajectoryActionChosen3 = tab3.build();


        Actions.runBlocking(
                new ParallelAction(
                        trajectoryActionChosen3
                )
        );

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 4, 4),
                        new SleepAction(0.7),
                        new MoveToPresetAction(clawRotator, 2),
                        new SleepAction(0.7),
                        new MoveToPresetAction(armExtenderMotor, 1),
                        new SleepAction(0.7),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 0, 0),
                        new SleepAction(0.9),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new SleepAction(0.5),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0)
                )
        );



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }








        while (opModeIsActive()) {

        }

    }
}