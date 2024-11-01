package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;

@Autonomous(name = "Cobra Blue Left", group = "Autonomous")
public class CobraAutoBlueLeft extends  CobraAutoSpecimen{


    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(6.5, 62.00, Math.toRadians(270));

        initialize(startPosition);

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPosition)
                .lineToY(41);

        straffeObject1OnGround = mecanumDrive.actionBuilder(new Pose2d(6.5, 41, Math.toRadians(270)))
                .lineToY(60)
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
        operateRunMode ();

    }


}
