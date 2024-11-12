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

    public Pose2d startPosition = new Pose2d(-61, 61.00, Math.toRadians(270));

    @Override
    public void runOpMode() throws InterruptedException {

        loadPersistance();
        //new Pose2d(-12.50, -62.00, Math.toRadians(90.00));

        float x = sharedPreferences.getFloat("x",0.0f);
        float y = sharedPreferences.getFloat("y",0.0f);
        float heading = sharedPreferences.getFloat("heading",0.0f);
        Pose2d startPos = new Pose2d(x, y, heading);
        telemetry.addData("",startPos);
        telemetry.update();

        initialize(startPos);

        executeOpMode();
    }


    public void automationPickup () {

        // Pickup the object from ground

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 4, 4), // level down
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight, 0,0), //opens claw
                        new MoveToPresetAction(clawRotator, 1), // rotate claw to pickup
                        new MoveToPresetAction(armExtenderMotor,2), //Extends arm forward
                        new SleepAction(1.5),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight, 1,1), // pickup
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0), // level down
                        new MoveToPresetAction(armExtenderMotor,3) //Extends arm forward
                      //  new MoveToPresetAction(clawRotator, 0) // rotate claw to face straing
                )
        );

    }

    public void automationSpecimenHang () {

      TrajectoryActionBuilder trajectoryMoveCloserToBar;
        TrajectoryActionBuilder straffeObject1OnGround;

        mecanumDrive.updateRobotPose();

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .turnTo(Math.toRadians(270))
                .splineToLinearHeading(new Pose2d(-12.5, 41, Math.toRadians(270)), Math.toRadians(270));

        Action actionMoveCloserToBar = trajectoryMoveCloserToBar.build();

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

    }

}
