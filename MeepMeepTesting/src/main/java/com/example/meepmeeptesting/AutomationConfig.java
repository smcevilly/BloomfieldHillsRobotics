package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DriveShim;

/**
 * Class where we will populate system configuration such as motor names
 * motor types, limits, etc
 */
public class AutomationConfig {

    // All Blue Configurations

    public Pose2d BLUE_LEFT_START_POSITION = new Pose2d(6.5, 62.00, Math.toRadians(270));
    public Pose2d BLUE_RIGHT_START_POSITION = new Pose2d(-12.50, 62.00, Math.toRadians(270));

    // BLUE END POSITION -   -61, 61, 270

    public TrajectoryActionBuilder getBlueTurnAroundTrajectory (DriveShim mecanumDrive, Pose2d startPosition) {
        return mecanumDrive.actionBuilder(startPosition)
                .turnTo(Math.toRadians(270));

    };

    public TrajectoryActionBuilder getBlueTracePathToBar (DriveShim mecanumDrive, Pose2d startPosition) {
        return mecanumDrive.actionBuilder(startPosition)
                .turnTo(Math.toRadians(0))
//                .strafeTo(new Vector2d(-10, 41));
                .splineTo(new Vector2d(-10, 41), Math.toRadians(270));
    };

    public TrajectoryActionBuilder getBlueMoveCloserToBarForAuto (DriveShim mecanumDrive, Pose2d startPosition) {
        return mecanumDrive.actionBuilder(startPosition)
                .lineToY(41);
    }



    public TrajectoryActionBuilder getBlueRightObjectMoveTrajectory (DriveShim mecanumDrive, Pose2d startPosition) {
        //return mecanumDrive.actionBuilder(new Pose2d(-12.5, 41, Math.toRadians(270)))
/*          return mecanumDrive.actionBuilder(startPosition)
                 .lineToY(60)
                .strafeTo(new Vector2d(-38, 60))
                .setTangent(Math.toRadians(270))
                .lineToY(7)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-50, 7.5))
                .setTangent(Math.toRadians(270))
                .lineToY(55)
                .setTangent(Math.toRadians(270))
                .lineToY(45)
                  .turnTo(Math.toRadians(-270));
*/
        return mecanumDrive.actionBuilder(startPosition)
                .turnTo( Math.toRadians(180))
                .lineToX(-35)
                .turnTo( Math.toRadians(90))
                .lineToY(12)
                .strafeTo(new Vector2d(-45,12))
                .setTangent(Math.toRadians(270))
                .lineToY(55)
                .lineToY(12)
                .strafeTo(new Vector2d(-55,12))
                .setTangent(Math.toRadians(270))
                .lineToY(55)

                //                .lineToY(45)
                ;


    }

    public TrajectoryActionBuilder getBlueLeftObjectMoveTrajectory (DriveShim mecanumDrive, Pose2d startPosition) {

        //return mecanumDrive.actionBuilder(new Pose2d(6.5, 41, Math.toRadians(270)))
        return mecanumDrive.actionBuilder(startPosition)
                .lineToY(60)
                .strafeTo(new Vector2d(-38, 60))
                .lineToY(7)
                .strafeTo(new Vector2d(-50, 7.5))
                .lineToY(55)
                .lineToY(7)
                .strafeTo(new Vector2d(-61, 7))
                .lineToY(61)
                .lineToY(50)
                .turnTo(Math.toRadians(270));
    }

    // All Red Configurations

    // RED END POSITION -   61, -61, 90

    public Pose2d RED_LEFT_START_POSITION = new Pose2d(-7.50, -62.00, Math.toRadians(90.00));
    public Pose2d RED_RIGHT_START_POSITION = new Pose2d(12.50, -62.00, Math.toRadians(90.00));

    public TrajectoryActionBuilder getRedTurnAroundTrajectory (DriveShim mecanumDrive, Pose2d startPosition) {
        return mecanumDrive.actionBuilder(startPosition)
                .lineToY(50)
                .turnTo(Math.toRadians(90));
    };

    public TrajectoryActionBuilder getRedTracePathToBar (DriveShim mecanumDrive, Pose2d startPosition) {
        return mecanumDrive.actionBuilder(startPosition)
                .turnTo(Math.toRadians(0))
                .lineToX(10)
                .turnTo(Math.toRadians(-90))
                .lineToY(-41)
                ;
    };

    public TrajectoryActionBuilder getRedMoveCloserToBarForAuto (DriveShim mecanumDrive, Pose2d startPosition) {
        return mecanumDrive.actionBuilder(startPosition)
                .lineToY(-41);
    }


    public TrajectoryActionBuilder getRedLeftObjectMoveTrajectory (DriveShim mecanumDrive, Pose2d startPosition) {

        //return mecanumDrive.actionBuilder(new Pose2d(-7.5, -41, Math.toRadians(90)))
        return mecanumDrive.actionBuilder(startPosition)
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


    public TrajectoryActionBuilder getRedRightObjectMoveTrajectory(DriveShim mecanumDrive, Pose2d startPosition) {
        //return mecanumDrive.actionBuilder(new Pose2d(12.5, -41, Math.toRadians(90)))
        return mecanumDrive.actionBuilder(startPosition)
                .strafeTo(new Vector2d(38, -41))
                .lineToY(-7)
                .strafeTo(new Vector2d(50, -7))
                .lineToY(-55)
                .lineToY(-7)
                //.strafeTo(new Vector2d(61, -7))
                //.lineToY(-61);
                .splineTo(new Vector2d(61, -7), Math.toRadians(270));
    }


}


