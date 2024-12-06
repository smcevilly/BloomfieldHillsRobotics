package org.coding.cobra.config;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

import org.coding.cobra.ext.MecanumDriveEx;

/**
 * Class where we will populate system configuration such as motor names
 * motor types, limits, etc
 */
@Config
public class AutomationConfig {

    public enum ROBOT_START_MODE {
        AUTO_BLUE_LEFT,
        AUTO_BLUE_RIGHT,
        AUTO_RED_LEFT,
        AUTO_RED_RIGHT,
        TELEOP_BLUE,
        TELEOP_RED
    }

    public static AutomationConfig.ROBOT_START_MODE robotStartMode;

    // All Blue Configurations

    public Pose2d BLUE_LEFT_START_POSITION = new Pose2d(7.5, 62, Math.toRadians(270));
    public Pose2d BLUE_RIGHT_START_POSITION = new Pose2d(-7.5, 62, Math.toRadians(270));

    // BLUE END POSITION -   -61, 61, 270

    private TrajectoryActionBuilder getBlueTurnAroundTrajectory (MecanumDriveEx mecanumDrive) {
        return null;
        /*mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .setTangent(Math.toRadians(270))
                .lineToY(50)
                .turnTo(Math.toRadians(270));*/
    };

    private TrajectoryActionBuilder getBlueTracePathToBar (MecanumDriveEx mecanumDrive, boolean retractBack) {
        tracePathUsageCountForOffset+=3;
        if (retractBack) {
            return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
            .lineToY(45)
            .turnTo(Math.toRadians(0))
            .splineTo(new Vector2d(-12.5+tracePathUsageCountForOffset, 43), Math.toRadians(270));
        }
        else
        {
            return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                    .turnTo(Math.toRadians(0))
                    .splineTo(new Vector2d(-12.5+tracePathUsageCountForOffset, 43), Math.toRadians(270));
        }
    }

    private TrajectoryActionBuilder getBlueMoveCloserToBarForAuto (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(40);
    }

    private TrajectoryActionBuilder getBlueRightObjectMoveTrajectory (MecanumDriveEx mecanumDrive) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();

        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .strafeTo(new Vector2d(-36, 40))
                .setTangent(Math.toRadians(270))
//                .splineToConstantHeading(new Vector2d(-36.91, 45), Math.toRadians(270.00))
                .splineTo(new Vector2d(-38.72, 12.08), Math.toRadians(124.07))
                .splineTo(new Vector2d(-39.2, 34.59), Math.toRadians(84.12))
                .splineTo(new Vector2d(-40.03, 56.61), Math.toRadians(90.00))
                .lineToY(5.25)
                .setTangent(Math.toRadians(90))
                .splineTo(new Vector2d(-56, 12.25), Math.toRadians(125.61))
                .splineTo(new Vector2d(-56, 29.30), Math.toRadians(90.00))
                .splineTo(new Vector2d(-56, 58), Math.toRadians(90.00));

    }

    private TrajectoryActionBuilder getBlueLeftObjectMoveTrajectory (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(60)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-38, 60))
                .setTangent(Math.toRadians(270))
                .lineToY(7)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-50, 7.5))
                .setTangent(Math.toRadians(270))
                .lineToY(55)
                .lineToY(7)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-61, 7))
                .setTangent(Math.toRadians(270))
                .lineToY(61);
    }

    private TrajectoryActionBuilder getBlueSecondObjectPickupTrajectory (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .turnTo(Math.toRadians(150))
                .splineTo(new Vector2d(-56, 57), Math.toRadians(90));
    }


    // All Red Configurations

    // RED END POSITION -   61, -61, 90

    public Pose2d RED_LEFT_START_POSITION = new Pose2d(-7.50, -62, Math.toRadians(90.00));
    public Pose2d RED_RIGHT_START_POSITION = new Pose2d(7.5, -62, Math.toRadians(90.00));

    private TrajectoryActionBuilder getRedTurnAroundTrajectory (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(50)
                .turnTo(Math.toRadians(90));
    };

    private TrajectoryActionBuilder getRedTracePathToBar (MecanumDriveEx mecanumDrive, boolean retractBack) {
        tracePathUsageCountForOffset+=4;
        if (retractBack) {
            return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                    .lineToY(-45)
                    .turnTo(Math.toRadians(180))
                    .splineTo(new Vector2d(12.5-tracePathUsageCountForOffset, -41), Math.toRadians(90));
        }
        else
        {
            return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                    .turnTo(Math.toRadians(180))
                    .splineTo(new Vector2d(12.5-tracePathUsageCountForOffset, -41), Math.toRadians(90));
        }
    };

    private TrajectoryActionBuilder getRedMoveCloserToBarForAuto (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(-38);
    }


    private TrajectoryActionBuilder getRedLeftObjectMoveTrajectory (MecanumDriveEx mecanumDrive) {

        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(-60)
                .strafeTo(new Vector2d(38, -60))
                .setTangent(Math.toRadians(90))
                .lineToY(-7)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(50, -7))
                .setTangent(Math.toRadians(90))
                .lineToY(-55)
                .lineToY(-7)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(61, -7))
                .setTangent(Math.toRadians(90))
                .lineToY(-61);

    }


    private TrajectoryActionBuilder getRedRightObjectMoveTrajectory(MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .strafeTo(new Vector2d(36, -40))
                .setTangent(Math.toRadians(90))
                .splineTo(new Vector2d(39.5, -12.08), Math.toRadians(270))
//                .splineTo(new Vector2d(40.2, -34.59), Math.toRadians(270))
                .splineTo(new Vector2d(41.03, -56.61), Math.toRadians(270))
                .lineToY(-5.25)
                .setTangent(Math.toRadians(270))
                .splineTo(new Vector2d(56, -12.25), Math.toRadians(270))
                .splineTo(new Vector2d(56, -29.30), Math.toRadians(270))
                .splineTo(new Vector2d(56, -58), Math.toRadians(270));
    }

    private TrajectoryActionBuilder getRedSecondObjectPickupTrajectory (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .turnTo(Math.toRadians(0))
                .splineTo(new Vector2d(56, -57), Math.toRadians(270));
    }


    /**
     *
     * Dynamically returns start position
     * @return
     */
    public Pose2d getRobotStartPosition () {
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return BLUE_LEFT_START_POSITION;
            case AUTO_BLUE_RIGHT: return BLUE_RIGHT_START_POSITION;
            case AUTO_RED_LEFT: return RED_LEFT_START_POSITION;
            case AUTO_RED_RIGHT: return RED_RIGHT_START_POSITION;
        }
        return null;
    }

    public TrajectoryActionBuilder getRobotObjectStaffe (MecanumDriveEx mecanumDrive) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return getBlueLeftObjectMoveTrajectory(mecanumDrive);
            case AUTO_BLUE_RIGHT: return getBlueRightObjectMoveTrajectory(mecanumDrive);
            case AUTO_RED_LEFT: return getRedLeftObjectMoveTrajectory(mecanumDrive);
            case AUTO_RED_RIGHT: return getRedRightObjectMoveTrajectory(mecanumDrive);
        }
        return null;
    }

    public TrajectoryActionBuilder getRobotSecondObjectMove (MecanumDriveEx mecanumDrive) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return getBlueSecondObjectPickupTrajectory(mecanumDrive);
            case AUTO_BLUE_RIGHT: return getBlueSecondObjectPickupTrajectory(mecanumDrive);
            case AUTO_RED_LEFT: return getRedSecondObjectPickupTrajectory(mecanumDrive);
            case AUTO_RED_RIGHT: return getRedSecondObjectPickupTrajectory(mecanumDrive);
        }
        return null;
    }


    public TrajectoryActionBuilder getRobotCloserToBar (MecanumDriveEx mecanumDrive) {
        //mecanumDrive.updateRobotPoseTelemetryUpdate();
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return getBlueMoveCloserToBarForAuto(mecanumDrive);
            case AUTO_BLUE_RIGHT: return getBlueMoveCloserToBarForAuto(mecanumDrive);
            case TELEOP_BLUE: return getBlueMoveCloserToBarForAuto(mecanumDrive);
            case AUTO_RED_LEFT: return getRedMoveCloserToBarForAuto(mecanumDrive);
            case AUTO_RED_RIGHT: return getRedMoveCloserToBarForAuto(mecanumDrive);
            case TELEOP_RED: return getRedMoveCloserToBarForAuto(mecanumDrive);
        }
        return null;
    }

    public TrajectoryActionBuilder getTurnAroundTrajectory (MecanumDriveEx mecanumDrive) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return getBlueTurnAroundTrajectory(mecanumDrive);
            case AUTO_BLUE_RIGHT: return getBlueTurnAroundTrajectory(mecanumDrive);
            case TELEOP_BLUE: return getBlueTurnAroundTrajectory(mecanumDrive);
            case AUTO_RED_LEFT: return getRedTurnAroundTrajectory(mecanumDrive);
            case AUTO_RED_RIGHT: return getRedTurnAroundTrajectory(mecanumDrive);
            case TELEOP_RED: return getRedTurnAroundTrajectory(mecanumDrive);
        }
        return null;
    }

    int tracePathUsageCountForOffset = 0;
    public TrajectoryActionBuilder getTracePathToBarTrajectory (MecanumDriveEx mecanumDrive, boolean retractBack) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return getBlueTracePathToBar(mecanumDrive, retractBack);
            case AUTO_BLUE_RIGHT: return getBlueTracePathToBar(mecanumDrive, retractBack);
            case TELEOP_BLUE: return getBlueTracePathToBar(mecanumDrive, retractBack);
            case AUTO_RED_LEFT: return getRedTracePathToBar(mecanumDrive, retractBack);
            case AUTO_RED_RIGHT: return getRedTracePathToBar(mecanumDrive, retractBack);
            case TELEOP_RED: return getRedTracePathToBar(mecanumDrive, retractBack);
        }
        return null;
    }

}


