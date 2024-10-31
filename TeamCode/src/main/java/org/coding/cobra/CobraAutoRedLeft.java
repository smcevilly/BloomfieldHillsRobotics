package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;

@Autonomous(name = "Cobra Red Left", group = "Autonomous")
public class CobraAutoRedLeft extends  CobraAutoSpecimen{

    @Override
    public void runOpMode() {

        Pose2d startPosition = new Pose2d(-12.50, -62.00, Math.toRadians(90.00));

        initialize(startPosition);

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(startPosition)
                .setTangent(Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(-5.25, -41, Math.toRadians(90)), Math.toRadians(90));



        straffeObject1OnGround = mecanumDrive.actionBuilder(new Pose2d(-12.5, -43, Math.toRadians(90)))
                .strafeTo(new Vector2d(-40, -41));

        operateRunMode ();

    }


}
