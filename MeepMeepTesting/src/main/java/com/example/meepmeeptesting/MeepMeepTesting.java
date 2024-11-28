package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveShim;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        AutomationConfig automationConfig = new AutomationConfig();

        // Known Positions

        // BLUE END POSITION -   -61, 61, 270
        Pose2d BLUE_END_POSITION = new Pose2d(-61, 61, Math.toRadians(270));
        Pose2d BLUE_END_POSITION_AFTER_PICKUP  = new Pose2d(-61, 61, Math.toRadians(-270));
        // RED END POSITION -    61, -61, 90
        Pose2d RED_END_POSITION = new Pose2d(61, -61, Math.toRadians(90));
        Pose2d RED_END_POSITION_AFTER_PICKUP = new Pose2d(61, -61, Math.toRadians(-90));

        Pose2d startPosition = RED_END_POSITION_AFTER_PICKUP;
        Pose2d Blueleftstartpos = new Pose2d(6, 61, Math.toRadians(270));


         Pose2d BLUE_RIGHT_START_POSITION_2 = new Pose2d(-12.50, 41.00, Math.toRadians(270));

        // Blue End Position
         myBot.runAction(automationConfig.getBlueRightObjectMoveTrajectory(myBot.getDrive(),   BLUE_RIGHT_START_POSITION_2).build());
        //myBot.runAction(automationConfig.getBlueTurnAroundTrajectory(myBot.getDrive(), BLUE_END_POSITION_AFTER_PICKUP).build());
        //myBot.runAction(automationConfig.getBlueTracePathToBar(myBot.getDrive(), BLUE_END_POSITION_AFTER_PICKUP).build());

        // Red End Position
        //myBot.runAction(automationConfig.getRedTurnAroundTrajectory(myBot.getDrive(), RED_END_POSITION).build());
        //myBot.runAction(automationConfig.getRedTracePathToBar(myBot.getDrive(), RED_END_POSITION).build());



        //sample
     /*   myBot.runAction(
                myBot.getDrive().actionBuilder(Blueleftstartpos)
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
                        .lineToY(50).build());
          */

                       /* .lineToY(41)
                        .strafeTo(new Vector2d(48,41))
                        .setTangent(Math.toRadians(270))
                        .splineTo(new Vector2d(55, 50), Math.toRadians(38))
        //.splineToLinearHeading(new Vector2d(55, 55), Math.toRadians(230)
                   .build());

                        */



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}
