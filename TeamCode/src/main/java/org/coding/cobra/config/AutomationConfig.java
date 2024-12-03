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

    public Pose2d BLUE_LEFT_START_POSITION = new Pose2d(6.5, 60.00, Math.toRadians(270));
    public Pose2d BLUE_RIGHT_START_POSITION = new Pose2d(-12.50, 60.00, Math.toRadians(270));

    // BLUE END POSITION -   -61, 61, 270

    private TrajectoryActionBuilder getBlueTurnAroundTrajectory (MecanumDriveEx mecanumDrive) {
        return null;
        /*mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .setTangent(Math.toRadians(270))
                .lineToY(50)
                .turnTo(Math.toRadians(270));*/
    };

    private TrajectoryActionBuilder getBlueTracePathToBar (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .turnTo(Math.toRadians(0))
                .splineTo(new Vector2d(-10, 41), Math.toRadians(270));
    };

    private TrajectoryActionBuilder getBlueMoveCloserToBarForAuto (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(41);
    }



    private TrajectoryActionBuilder getBlueRightObjectMoveTrajectory (MecanumDriveEx mecanumDrive) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();
        /*return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .turnTo( Math.toRadians(180))
                .splineTo(new Vector2d(-45, 30), Math.toRadians(-270))
                .lineToY(53)
                .lineToY(50)
                ;
            */

        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .splineTo(new Vector2d(-36.00, 41.00), Math.toRadians(270.00))
                .splineTo(new Vector2d(-42.41, 10.92), Math.toRadians(100.00))
                .splineTo(new Vector2d(-49.69, 50.41), Math.toRadians(90.00))
                .lineToY(10)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(-59,10))
                .setTangent(Math.toRadians(90))
                .splineTo(new Vector2d(-55, 50), Math.toRadians(90.00));
                // .splineTo(new Vector2d(-55, 15), Math.toRadians(90.00))
                //.splineTo(new Vector2d(-55, 55), Math.toRadians(90.00))//10.64

        /*
                .turnTo( Math.toRadians(180))
                .lineToX(-40)
                .turnTo( Math.toRadians(90))
                .lineToY(12)
                .strafeTo(new Vector2d(-50,12))
                .setTangent(Math.toRadians(270))
                .lineToY(55)
                .lineToY(12)
                .strafeTo(new Vector2d(-60,12))
                .setTangent(Math.toRadians(270))
                .lineToY(50);*/
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

    // All Red Configurations

    // RED END POSITION -   61, -61, 90

    public Pose2d RED_LEFT_START_POSITION = new Pose2d(-7.50, -60.00, Math.toRadians(90.00));
    public Pose2d RED_RIGHT_START_POSITION = new Pose2d(12.50, -60.00, Math.toRadians(90.00));

    private TrajectoryActionBuilder getRedTurnAroundTrajectory (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(50)
                .turnTo(Math.toRadians(90));
    };

    private TrajectoryActionBuilder getRedTracePathToBar (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .turnTo(Math.toRadians(0))
                .lineToX(10)
                .turnTo(Math.toRadians(-90))
                .lineToY(-41)
                ;
    };

    private TrajectoryActionBuilder getRedMoveCloserToBarForAuto (MecanumDriveEx mecanumDrive) {
        return mecanumDrive.actionBuilder(mecanumDrive.getRobotPose())
                .lineToY(-41);
    }


    private TrajectoryActionBuilder getRedLeftObjectMoveTrajectory (MecanumDriveEx mecanumDrive) {

        //return mecanumDrive.actionBuilder(new Pose2d(-7.5, -41, Math.toRadians(90)))
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
                .strafeTo(new Vector2d(38, -41))
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

    public TrajectoryActionBuilder getTracePathToBarTrajectory (MecanumDriveEx mecanumDrive) {
        mecanumDrive.updateRobotPoseTelemetryUpdate();
        switch (robotStartMode) {
            case AUTO_BLUE_LEFT: return getBlueTracePathToBar(mecanumDrive);
            case AUTO_BLUE_RIGHT: return getBlueTracePathToBar(mecanumDrive);
            case TELEOP_BLUE: return getBlueTracePathToBar(mecanumDrive);
            case AUTO_RED_LEFT: return getRedTracePathToBar(mecanumDrive);
            case AUTO_RED_RIGHT: return getRedTracePathToBar(mecanumDrive);
            case TELEOP_RED: return getRedTracePathToBar(mecanumDrive);
        }
        return null;
    }

}


