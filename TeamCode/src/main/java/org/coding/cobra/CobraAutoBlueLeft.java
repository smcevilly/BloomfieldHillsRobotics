package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.SystemConfig;

@Autonomous(name = "Cobra Blue Left", group = "Autonomous")
public class CobraAutoBlueLeft extends  CobraAutoSpecimen{

    @Override
    public void runOpMode() {

        initialize();

        trajectoryMoveCloserToBar = mecanumDrive.actionBuilder(SystemConfig.ROBOT_START_POSITION_FOR_RED_SPECIMEN)
                .lineToY(-43);


        straffeObject1OnGround = mecanumDrive.actionBuilder(new Pose2d(12.5, -43, Math.toRadians(90)))
                .strafeTo(new Vector2d(38, -40))
                .setTangent(Math.toRadians(90))
                .lineToY(-7)
                .setTangent(Math.toRadians(90))
                .strafeTo(new Vector2d(50, -7))
                .setTangent(Math.toRadians(90))
                .lineToY(-55);

        operateRunMode ();

    }


}
