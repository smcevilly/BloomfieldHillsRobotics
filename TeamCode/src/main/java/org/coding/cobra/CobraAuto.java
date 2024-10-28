package org.coding.cobra;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MoveToPresetAsync;
import org.firstinspires.ftc.teamcode.Drawing;

@Config
@Autonomous(name = "Cobra Auto", group = "Autonomous")
public class CobraAuto extends CobraBase  {

    @Override
    public void runOpMode() {

        initialize();

        // vision here that outputs position
        int visionOutputPosition = 1;

        TrajectoryActionBuilder tab1 = mecanumDrive.actionBuilder(SystemConfig.ROBOT_START_POSITION)
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(-50)
                .strafeTo(new Vector2d(-50, -50))
                .turn(Math.toRadians(135))
                .waitSeconds(4);

/*                .setTangent(Math.toRadians(0))
                .lineToX(30)
                .strafeTo(new Vector2d(55, 60))
                .turn(Math.toRadians(36))
                //.lineToX(47.5)
                .waitSeconds(3);
*/



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
            flexiClawLeft.handlePresets(1);
            flexiClawRight.handlePresets(1);
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


        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAsync(leftElevator, rightElevator, 1, 1),
                        new MoveToPresetAsync(armExtenderMotor, 1)
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

        flexiClawLeft.handlePresets(true, false, false);
        flexiClawRight.handlePresets(true, false, false);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAsync(armExtenderMotor, 0),
                        new MoveToPresetAsync(leftElevator, rightElevator, 0, 0)
                        //lift.liftUp(),
                        //claw.openClaw(),
                        //lift.liftDown()
                )
        );


        while (opModeIsActive()) {

        }

    }
}