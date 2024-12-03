package org.coding.cobra.config;


import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

@Config
public class MecanumDriveConfig {

    /**
     * Motor Names
     */

    public String leftFrontMotor = "leftFront";
    public String rightFrontMotor = "rightFront";
    public String leftRearMotor = "leftBack";
    public String rightRearMotor = "rightBack";
    public String dummyMotorForDeadWheel = "parDeadWheel";


    // IMU orientation
    // TODO: fill in these values based on
    //   see https://ftc-docs.firstinspires.org/en/latest/programming_resources/imu/imu.html?highlight=imu#physical-hub-mounting
    public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.FORWARD;
    public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.UP;


    public RevHubOrientationOnRobot.LogoFacingDirection secondaryImuLogoFacingDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.BACKWARD;
    public RevHubOrientationOnRobot.UsbFacingDirection secondaryUsbFacingDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.UP;


    public  final double maxMotorPower = 2;


    // drive model parameters
    public double  inPerTick = 0.002; //24000 for 48 inches
    public   double lateralInPerTick = 0.0013929811062764618;
    //public double lateralInPerTick = 0.0006628730045191881;
    public double trackWidthTicks =  8000;//7800;//7250; // measred //7886.722580031681;  //0

    public static class TwoWheelOdoParams {
//        public double parYTicks = 0.625 * 1 / 0.001408116138; // y position of the parallel encoder (in tick units)
        public double parYTicks = 5.15 * 1/ 0.001408116138; // y position of the first parallel encoder (in tick units)
//        public double perpXTicks = -5.75 * 1 / 0.002038043478; // x position of the perpendicular encoder (in tick units)
        public double perpXTicks = -5.75 * 1 / 0.002; // x position of the perpendicular encoder (in tick units)
    }

    public static class ThreeWheelOdoParams {
        public double par0YTicks = -2562;//-2276.11;//-5.125 * 1/ 0.002; // y position of the first parallel encoder (in tick units)
        public double par1YTicks = 2800;//2866.59;//5.125 * 1/ 0.002; // y position of the second parallel encoder (in tick units)
        public double perpXTicks = -2875;//-2529.56;//-7.125* 1 / 0.002; //-5.75  // x position of the perpendicular encoder (in tick units)
    }

    // feedforward parameters (in tick units)
    public double kS =  1.158509924;
    public double kV =  0.00037828605;
    public double kA = 0.0001; //0.0001

    // path profile parameters (in inches)
    public double maxWheelVel = 60;              // 15  //45
    public double minProfileAccel = -40;        //  -10    //-30
    public double maxProfileAccel = 60;         // 15   //45

    // turn profile parameters (in radians)
    public double maxAngVel = Math.PI; // shared with path
    public double maxAngAccel = Math.PI;

    // path controller gains
    public double axialGain = 3;
    public double lateralGain = 5;//25
    public double headingGain = 3;//5; // shared with turn//25

    public double axialVelGain = 0.0;
    public double lateralVelGain = 0.0;
    public double headingVelGain = 0.0; // shared with turn
}
