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

        initialize(startPosition);

        executeOpMode();
    }


    public void automationSpecimenHang () {

        leftElevator.resetToZeroPosition();
        rightElevator.resetToZeroPosition();

        /*
        TrajectoryActionBuilder trajectoryMoveCloserToBar;
        TrajectoryActionBuilder straffeObject1OnGround;

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPosition)
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(-12.50, 62.00))
                .lineToY(41);

        Action actionMoveCloserToBar = trajectoryMoveCloserToBar.build();
        waitForStart();

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

*/
    }

}
