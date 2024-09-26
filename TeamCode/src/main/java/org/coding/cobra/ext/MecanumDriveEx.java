package org.coding.cobra.ext;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;


/*
    Extended version of Mecanum Drive with some extra functions
 */
public class MecanumDriveEx extends MecanumDrive {

    Telemetry telemetry;
    TrajectoryActionBuilder trajectoryActionBuilder;
    Pose2d startPose;

    public MecanumDriveEx(HardwareMap hardwareMap, Telemetry telemetryObject, Pose2d pose) {
        super(hardwareMap, pose);
        this.telemetry = telemetryObject;
        startPose = pose;
    }

    /*
    New version that uses target position to move
    This version is yet to be tested
     */
    public void moveRobot(double dr, double strafe, double twist, double power){

        setDrivePowers( new PoseVelocity2d(
                new Vector2d(dr*power, // drive power
                strafe*power), // strafe power
                twist*power  // twist power
        ));

        //turns 90 degress
        //Actions.runBlocking(actionBuilder(startPose).strafeTo(new Vector2d(10, 10)).waitSeconds(2).build());
    }

}
