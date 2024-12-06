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
    //public String dummyMotorForDeadWheel = "parDeadWheel";


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

    public double  inPerTick = 0.002; //24000 for 48 inches
    // feedforward parameters (in tick units)
    public double kS =  0.9927348775935472;
    public double kV =  0.0003749024553606893;
    public   double lateralInPerTick = 0.0014334598160025587;

    // drive model parameters
    //public double lateralInPerTick = 0.0006628730045191881;
    public double trackWidthTicks =  6000;//7059.171075767915;//7800;//7250; // measred //7886.722580031681;  //0

    public static class TwoWheelOdoParams {
//        public double parYTicks = 0.625 * 1 / 0.001408116138; // y position of the parallel encoder (in tick units)
        public double parYTicks = 5.15 * 1/ 0.001408116138; // y position of the first parallel encoder (in tick units)
//        public double perpXTicks = -5.75 * 1 / 0.002038043478; // x position of the perpendicular encoder (in tick units)
        public double perpXTicks = -5.75 * 1 / 0.002; // x position of the perpendicular encoder (in tick units)
    }

    public static class ThreeWheelOdoParams {

        // -7 inches and + 7 inches for parallel
        // -6 1/4 inches for perp
        public double par0YTicks = -3800;//-2276.11;//-5.125 * 1/ 0.002; // y position of the first parallel encoder (in tick units)
        public double par1YTicks = 3400;//3389.867557429977;//2866.59;//5.125 * 1/ 0.002; // y position of the second parallel encoder (in tick units)
        public double perpXTicks = -2988.4603237011056;//-2529.56;//-7.125* 1 / 0.002; //-5.75  // x position of the perpendicular encoder (in tick units)
    }

    public double kA = 0.0001; //0.0001

    // path profile parameters (in inches)
    public double maxWheelVel = 60;              // 15  //45
    public double minProfileAccel = -40;        //  -10    //-30
    public double maxProfileAccel = 60;         // 15   //45

    // turn profile parameters (in radians)
    public double maxAngVel = Math.PI; // shared with path
    public double maxAngAccel = Math.PI;

    // path controller gains
/*    public double axialGain = 0.0;
    public double lateralGain = 0.7;//25
    public double headingGain = 1.1; // responsible for drift to right correction //5; // shared with turn//25
*/
    public double axialGain = 3;
    public double lateralGain = 5;//25
    public double headingGain = 3;//5; // shared with turn//25

    public double axialVelGain = 0;
    public double headingVelGain = 0; // shared with turn
    public double lateralVelGain = 0;
}
