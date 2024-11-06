package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Cobra Red Left", group = "Autonomous")
public class CobraAutoRedLeft extends AbstractCobraAutoSpecimen {

    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(-7.50, -62.00, Math.toRadians(90.00));

        loadPersistance();
        initialize(startPosition);

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPosition)
                .lineToY(-41);


        straffeObject1OnGround = mecanumDrive.actionBuilder(new Pose2d(-7.5, -41, Math.toRadians(90)))
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


        operateRunMode ();

    }


}
