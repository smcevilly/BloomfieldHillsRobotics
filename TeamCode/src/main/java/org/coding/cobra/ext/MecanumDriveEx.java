package org.coding.cobra.ext;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Twist2dDual;
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

    public void updateRobotPose () {
        Twist2dDual<Time> twist = localizer.update();
        pose = pose.plus(twist.value());
    }

    /*
    New version that uses target position to move
    This version is yet to be tested
     */
    public void moveRobot(double left_stick_y, double left_stick_x, double right_stick_x, double power){

       /* setDrivePowers( new PoseVelocity2d(
                new Vector2d(dr*power, // drive power
                strafe*power), // strafe power
                twist*power  // twist power
        ));
        */

        setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        left_stick_y,
                        left_stick_x
                ),
                right_stick_x
        ));


        //turns 90 degress
        //Actions.runBlocking(actionBuilder(startPose).strafeTo(new Vector2d(10, 10)).waitSeconds(2).build());
    }

}
