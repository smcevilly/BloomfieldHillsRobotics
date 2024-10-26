package org.coding.cobra;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MoveToPresetAsync;

@Config
@Autonomous(name = "CobraAuto", group = "Autonomous")
public class CobraAuto extends CobraBase  {

    @Override
    public void runOpMode() {

        initialize();

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder tab1 = mecanumDrive.actionBuilder(SystemConfig.ROBOT_START_POSITION)
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(50)
                .setTangent(Math.toRadians(0))
                .lineToX(15)
                .strafeTo(new Vector2d(55, 60))
                .turn(Math.toRadians(36))
                //.lineToX(47.5)
                .waitSeconds(3);


        Action trajectoryActionCloseOut = tab1.fresh()
              ///  .strafeTo(new Vector2d(48, 12))
                .build();

        // actions that need to happen on init; for instance, a claw tightening.
        //Actions.runBlocking(claw.closeClaw());

        while (!isStopRequested() && !opModeIsActive()) {
            int position = visionOutputPosition;
            telemetry.addData("Initial Position : Holding the Object at position ", mecanumDrive.pose);
            telemetry.update();

            // hold the initial object
            flexiClawLeft.handlePresets(false, true, false);
            flexiClawRight.handlePresets(false, true, false);
        }

        int startPosition = visionOutputPosition;
        telemetry.addData("Determining the Move ",  mecanumDrive.pose);
        telemetry.update();
        waitForStart();

        if (isStopRequested()) return;

        Action trajectoryActionChosen;
        trajectoryActionChosen = tab1.build();


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

        //leftElevator.handlePresets(1);
        //rightElevator.handlePresets(1);
        armExtenderMotor.handlePresets(1);

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAsync(leftElevator, rightElevator, 1, 1)
                        //  new MoveToPresetAsync(armExtenderMotor, 1)
                        //lift.liftUp(),
                        //claw.openClaw(),
                        //lift.liftDown()
                )
        );

        telemetry.addData("Dropping the object ",  mecanumDrive.pose);
        telemetry.update();

        armExtenderMotor.handleEvents(1);
        flexiClawLeft.handlePresets(true, false, false);
        flexiClawRight.handlePresets(true, false, false);

        while (opModeIsActive()) {

        }

    }
}