package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Cobra Blue Right", group = "Autonomous")
public class CobraAutoBlueRight extends AbstractCobraAutoSpecimen {

    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(-12.50, 62.00, Math.toRadians(270));

        loadPersistance();
        initialize(startPosition);

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPosition)
                .lineToY(41);

        straffeObject1OnGround = mecanumDrive.actionBuilder(new Pose2d(-12.5, 41, Math.toRadians(270)))
                .strafeTo(new Vector2d(-38, 41))
                .setTangent(Math.toRadians(270))
                .lineToY(12)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-50, 12))
                .setTangent(Math.toRadians(270))
                .lineToY(55)
                .lineToY(12)
                .setTangent(Math.toRadians(270))
                .strafeTo(new Vector2d(-61, 12))
                .setTangent(Math.toRadians(270))
                .lineToY(61);

        operateRunMode ();

    }


}