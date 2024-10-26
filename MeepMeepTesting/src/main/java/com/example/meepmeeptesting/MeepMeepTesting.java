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

        Pose2d ROBOT_START_POSITION = new Pose2d(11.8, 61.7, Math.toRadians(0));


        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(0.17, -62.33, Math.toRadians(90.00)))
                .splineTo(new Vector2d(0.00, -43.83), Math.toRadians(90.52))
                .splineTo(new Vector2d(-22.17, -47.67), Math.toRadians(189.81))
                .splineTo(new Vector2d(-27.33, -62.00), Math.toRadians(250.18))
                .splineTo(new Vector2d(-36.17, -69.17), Math.toRadians(219.05))
                        .build());




     /*   myBot.runAction(myBot.getDrive().actionBuilder(ROBOT_START_POSITION)
               // .lineToYSplineHeading(33, Math.toRadians(0))
                .waitSeconds(2)
                .setTangent(Math.toRadians(90))
                .lineToY(48)
                .setTangent(Math.toRadians(0))
                .lineToX(32)
                .strafeTo(new Vector2d(44.5, 30))
                .turn(Math.toRadians(180))
                .lineToX(47.5)
                .waitSeconds(3)
                .build());

      */

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}