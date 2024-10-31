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
        Pose2d startPosition = new Pose2d(12.50, 62.00, Math.toRadians(90.00));

        Pose2d ROBOT_START_POSITION = new Pose2d(-12.50, -62.00, Math.toRadians(90.00));
Pose2d ROBOT_START_POSITION_FOR_RED_SPECIMEN= new Pose2d(12.50, -62.00, Math.toRadians(90.00));

        /* myBot.runAction(myBot.getDrive().actionBuilder(ROBOT_START_POSITION)
                        .waitSeconds(2)
                      //  .setTangent(Math.toRadians(90))
                        .lineToY(-52)
                        .strafeTo(new Vector2d(-55, -52))
                        .turn(Math.toRadians(135))
                        .waitSeconds(3)
                       // .setTangent(Math.toRadians(90))
                        .turn(Math.toRadians(-135))
                        .lineToY(-48)
                        .waitSeconds(6)
                        .lineToY(-52)
                        .turn(Math.toRadians(135))
                        .build());





        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-52, -52, Math.toRadians(220)))
                        .setTangent(Math.toRadians(90))
                        .lineToYLinearHeading(-40,Math.toRadians(90))
                        .waitSeconds(3)
                                .build());


*/


       myBot.runAction(myBot.getDrive().actionBuilder(ROBOT_START_POSITION)
               .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-52, -52, Math.toRadians(220)), Math.toRadians(280))
                .build());






                /*





        myBot.runAction(myBot.getDrive().actionBuilder(startPosition)
                .setTangent(Math.toRadians(280))
                .splineToLinearHeading(new Pose2d(52, 52, Math.toRadians(220)), Math.toRadians(280))

                        .build());

         */
         /*
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12.5, 41, Math.toRadians(270)))
                .strafeTo(new Vector2d(-38, 41))
                .setTangent(Math.toRadians(270))
                .lineToY(7.5)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-50, 7.5))
                .setTangent(Math.toRadians(270))
                .lineToY(55)
                .lineToY(7)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-61, 7))
                .setTangent(Math.toRadians(270))
                .lineToY(61)
                .build());


         */















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

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}