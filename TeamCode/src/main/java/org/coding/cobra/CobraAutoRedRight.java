package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;

@Autonomous(name = "Cobra Red Right", group = "Autonomous")
public class CobraAutoRedRight extends  CobraAutoSpecimen{

    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(12.50, -62.00, Math.toRadians(90.00));

        initialize(startPosition);

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPosition)
                .lineToY(-41);


        straffeObject1OnGround = mecanumDrive.actionBuilder(new Pose2d(12.5, -41, Math.toRadians(90)))
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



        operateRunMode ();

    }


}
