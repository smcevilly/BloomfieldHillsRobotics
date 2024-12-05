package org.coding.cobra;

import android.content.SharedPreferences;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.coding.cobra.config.AutomationConfig;
import org.coding.cobra.ext.ServoMotorControllerEx;

import org.coding.cobra.ext.MoveToPresetAction;

@Config
public abstract class AbstractCobraAutoSpecimen extends CobraBase  {


    public void operateRunMode() {

        // hold the initial object
        flexiClawLeft.handlePresets(1);
        flexiClawRight.handlePresets(1);

        Action actionMoveCloserToBar = AUTO_CONFIG.getRobotCloserToBar(mecanumDrive).build();

        waitForStart();

        clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);

        telemetry.addData("Extending Arm ",  mecanumDrive.pose);
        telemetry.update();

        tracePathToBar(actionMoveCloserToBar, false);
        automationSpecimenHang(false);

        telemetryOutput ();

        mecanumDrive.updateRobotPoseTelemetryUpdate();

        moveForSpecimenPickup (AUTO_CONFIG.getRobotObjectStaffe(mecanumDrive).build());

        //need to turn around
        //getBlueTurnAroundTrajectory

        mecanumDrive.updateRobotPoseTelemetryUpdate();

        automationPickup(false);
        tracePathToBar( AUTO_CONFIG.getTracePathToBarTrajectory(mecanumDrive, true).build(), true);
        automationSpecimenHang(false);

        moveForSpecimenPickup (AUTO_CONFIG.getRobotSecondObjectMove(mecanumDrive).build());
        automationPickup(false);
        tracePathToBar( AUTO_CONFIG.getTracePathToBarTrajectory(mecanumDrive, false).build(), true);
        automationSpecimenHang(true);


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


        /**
         *
         * turn around
         *
         * pickup
         *
         * move to drop
         *
        Action turnAround = turnAroundForPickup.build();
        Actions.runBlocking(
                new SequentialAction(
                        turnAround
                )
        );
        automationPickup();
        automationSpecimenHang();
         */


        telemetry.addData("Resetting to postion", "");
        telemetry.update();
        telemetryOutput ();

        leftElevator.resetToZeroPosition();
        rightElevator.resetToZeroPosition();

        // Persisting position:

        //Pose2d = mecanumDrive.getPoseEstimate()
        mecanumDrive.updateRobotPose();
        persistanceManager.storePosition (mecanumDrive.pose);

        while (opModeIsActive() && !isStopRequested()) {

        }

    }
}