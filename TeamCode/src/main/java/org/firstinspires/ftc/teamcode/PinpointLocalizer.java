package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.Vector2dDual;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.coding.cobra.config.helpers.GoBildaPinpointDriver;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

public class PinpointLocalizer implements Localizer{
    private  GoBildaPinpointDriver odo;

    // State variables for computing deltas
    private double lastX, lastY, lastHeading;

    public PinpointLocalizer(HardwareMap hardwareMap, double inPerTick) {
        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.

        odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");

        /*
        Set the odometry pod positions relative to the point that the odometry computer tracks around.
        The X pod offset refers to how far sideways from the tracking point the
        X (forward) odometry pod is. Left of the center is a positive number,
        right of center is a negative number. the Y pod offset refers to how far forwards from
        the tracking point the Y (strafe) odometry pod is. forward of center is a positive number,
        backwards is a negative number.
         */
        odo.setOffsets(-84.0, -168.0); //these are tuned for 3110-0002-0001 Product Insight #1

        /*
        Set the kind of pods used by your robot. If you're using goBILDA odometry pods, select either
        the goBILDA_SWINGARM_POD, or the goBILDA_4_BAR_POD.
        If you're using another kind of odometry pod, uncomment setEncoderResolution and input the
        number of ticks per mm of your odometry pod.
         */
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        //odo.setEncoderResolution(13.26291192);


        /*
        Set the direction that each of the two odometry pods count. The X (forward) pod should
        increase when you move the robot forward. And the Y (strafe) pod should increase when
        you move the robot to the left.
         */
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);


        /*
        Before running the robot, recalibrate the IMU. This needs to happen when the robot is stationary
        The IMU will automatically calibrate when first powered on, but recalibrating before running
        the robot is a good idea to ensure that the calibration is "good".
        resetPosAndIMU will reset the position to 0,0,0 and also recalibrate the IMU.
        This is recommended before you run your autonomous, as a bad initial calibration can cause
        an incorrect starting value for x, y, and heading.
         */
        //odo.recalibrateIMU();
        odo.resetPosAndIMU();

        Pose2D position;
        position = odo.getPosition();

        // Initialize the previous state
        this.lastX = position.getX(DistanceUnit.INCH);
        this.lastY = position.getY(DistanceUnit.INCH);
        this.lastHeading = position.getHeading(AngleUnit.RADIANS);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("X offset", odo.getXOffset());
        telemetry.addData("Y offset", odo.getYOffset());
        telemetry.addData("Device Version Number:", odo.getDeviceVersion());
        telemetry.addData("Device Scalar", odo.getYawScalar());
        telemetry.update();
    }


    @Override
    public Twist2dDual<Time> update() {
        // Fetch linear velocities from the driver
        double vx = odo.getVelX();
        double vy = odo.getVelX();

        // Fetch angular velocity from the driver
        double angularVelocity = odo.getHeadingVelocity();

        // Fetch positions from the driver (if needed for future enhancements)
        Pose2D position;
        position = odo.getPosition();
        double xPos = position.getX(DistanceUnit.INCH);
        double yPos = position.getY(DistanceUnit.INCH);
        double heading = position.getHeading(AngleUnit.RADIANS);

        // Compute deltas for position
        double deltaX = xPos - lastX;
        double deltaY = yPos - lastY;
        double deltaHeading = heading - lastHeading;

        // Construct DualNum objects for position and velocity
        DualNum<Time> xDual = new DualNum<>(new double[] { deltaX, vx });
        DualNum<Time> yDual = new DualNum<>(new double[] { deltaY, vy });
        DualNum<Time> headingDual = new DualNum<>(new double[] { deltaHeading, angularVelocity });

        // Assemble and return the Twist2dDual object
        return new Twist2dDual<>(
                new Vector2dDual<>(xDual, yDual), // Linear motion: X and Y
                headingDual                        // Angular motion: heading
        );
    }
}
