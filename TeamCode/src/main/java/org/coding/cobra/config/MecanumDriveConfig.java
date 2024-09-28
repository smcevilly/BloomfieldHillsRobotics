package org.coding.cobra.config;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class MecanumDriveConfig {

    /**
     * Motor Names
     */

    public String leftFrontMotor = "leftFront";
    public String rightFrontMotor = "rightFront";
    public String leftRearMotor = "leftBack";
    public String rightRearMotor = "rightBack";


    // IMU orientation
    // TODO: fill in these values based on
    //   see https://ftc-docs.firstinspires.org/en/latest/programming_resources/imu/imu.html?highlight=imu#physical-hub-mounting
    public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.UP;
    public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

    // drive model parameters
    public double inPerTick = 0.02;
    public double lateralInPerTick = inPerTick;
    public double trackWidthTicks = 1113.0175578308852;  //0

    // feedforward parameters (in tick units)
    public double kS = 1.641865208142689;
    public double kV =  0.003921974356018547;
    public double kA = 0.0001;

    // path profile parameters (in inches)
    public double maxWheelVel = 15;             // 50 //15
    public double minProfileAccel = -10;        // -30 // -10
    public double maxProfileAccel = 15;         // 50 //15

    // turn profile parameters (in radians)
    public double maxAngVel = Math.PI; // shared with path
    public double maxAngAccel = Math.PI;

    // path controller gains
    public double axialGain = 0.0;
    public double lateralGain = 0.0;
    public double headingGain = 0.0; // shared with turn

    public double axialVelGain = 0.0;
    public double lateralVelGain = 0.0;
    public double headingVelGain = 0.0; // shared with turn
}
