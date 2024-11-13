package org.coding.cobra;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.ext.MoveToPresetAction;

@TeleOp(name = "Manual Cobra Blue")
public class CobraManualBlue extends AbstractCobraManual {

    public Pose2d startPos; // = new Pose2d(-61, 61.00, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException {

        loadPersistance();
        //new Pose2d(-12.50, -62.00, Math.toRadians(90.00));

        float x = sharedPreferences.getFloat("x",0.0f);
        float y = sharedPreferences.getFloat("y",0.0f);
        float heading = sharedPreferences.getFloat("heading",0.0f);
        startPos = new Pose2d(x, y, heading);
        telemetry.addData("",startPos);
        telemetry.update();

        initialize(startPos);

        executeOpMode();
    }


    public void automationSpecimenHang () {

        TrajectoryActionBuilder trajectoryMoveCloserToBar;
        //TrajectoryActionBuilder straffeObject1OnGround;
        //mecanumDrive.updateRobotPose();

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(clawRotator, 0)
                        ));

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPos)
                .turnTo(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-12.5, 41, Math.toRadians(270)), Math.toRadians(270)
                );

        Action actionMoveCloserToBar = trajectoryMoveCloserToBar.build();

        telemetry.addData("Extending Arm ",  mecanumDrive.pose);
        telemetry.update();

        Actions.runBlocking(
                new SequentialAction(
                        actionMoveCloserToBar,
                        new SleepAction(2),
                        new MoveToPresetAction(leftElevator, rightElevator, 6, 6),
                        new SleepAction(1),
                        new MoveToPresetAction(armExtenderMotor, 4),
                        new SleepAction(1),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 0, 0),
                        new SleepAction(1),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0)
                )
        );

    }


}
