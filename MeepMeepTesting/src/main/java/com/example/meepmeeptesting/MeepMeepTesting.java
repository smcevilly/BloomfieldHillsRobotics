package com.example.meepmeeptesting;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
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

        // Blue End Position
        //myBot.runAction(automationConfig.getBlueRightObjectMoveTrajectory(myBot.getDrive(), automationConfig.BLUE_RIGHT_START_POSITION).build());
        //myBot.runAction(automationConfig.getBlueTurnAroundTrajectory(myBot.getDrive(), BLUE_END_POSITION_AFTER_PICKUP).build());
        myBot.runAction(automationConfig.getBlueTracePathToBar(myBot.getDrive(), BLUE_END_POSITION_AFTER_PICKUP).build());

        // Red End Position
        //myBot.runAction(automationConfig.getRedTurnAroundTrajectory(myBot.getDrive(), RED_END_POSITION).build());
        //myBot.runAction(automationConfig.getRedTracePathToBar(myBot.getDrive(), RED_END_POSITION).build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();

    }
}
