package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;



@TeleOp(name = "BartManual")
public class BartManual extends LinearOpMode {

    private final double gripperClosePosition = 0.7;
    private final double gripperOpenPosition = 0;

    private double wristUpPosition = 0;

    private double wristDownPosition = 0;


    private double wristStep = 0.025; /*equals 9 degrees*/

    private Servo wrist;

    private DcMotor rightBack;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private Servo rightGrabber;
    private DcMotor arm;
    private Servo leftGrabber;
    private DcMotor leftFront;
    private Servo launch;
    int armCurrentPosition = 0;
    int drone = 1;
    int armMoveStep = 50; //ticks

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        boolean servoButton;
        int oldServoButton = 0;
        // TODO: Enter the type for variable named servoPos
        int servoPos = 0;
        double  drive           = 0;        // Desired forward power/speed (-1 to +1)
        double  strafe          = 0;        // Desired strafe power/speed (-1 to +1)
        double  turn            = 0;        // Desired turning power/speed (-1 to +1)

        waitForStart();

        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        rightGrabber = hardwareMap.get(Servo.class, "rightGrabber");
        leftGrabber = hardwareMap.get(Servo.class, "leftGrabber");
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        wrist = hardwareMap.get(Servo.class, "wrist");
        launch = hardwareMap.get(Servo.class, "launch");

        // rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        //leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        // rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        // rightBack.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        while (opModeIsActive()) {
//Grabber:
            moveClaw(gamepad2);
//wrist/drone:
            moveWrist(gamepad2);
//navigation:

            // drive using manual POV Joystick mode.  Slow things down to make the robot more controlable.

            drive = -gamepad1.left_stick_y / 1.5;  // Reduce drive rate to 50%.
            strafe = -gamepad1.left_stick_x / 1.5;  // Reduce strafe rate to 50%.
            turn = -gamepad1.right_stick_x / 1.5;  // Reduce turn rate to 50%.

            // Apply desired axes motions to the drivetrain.
            moveRobot(drive, strafe, turn);
            sleep(10);

//arm:

            moveArm(gamepad2);

            telemetry.update();
            launch(gamepad1);

        }
    }

    private void launch(Gamepad launchDrone) {
        if(launchDrone.a){
            launch.setPosition(drone);
            launchDrone.a = false;
        }
    }

    public void moveClaw(Gamepad clawControl) {
        if (clawControl.left_bumper) {
            leftGrabber.setDirection(Servo.Direction.REVERSE);
            rightGrabber.setDirection(Servo.Direction.FORWARD);
            rightGrabber.setPosition(gripperOpenPosition);
            leftGrabber.setPosition(gripperOpenPosition);
            telemetry.addData("Left bumper", clawControl.left_bumper);
        } else if (clawControl.right_bumper) {
            leftGrabber.setDirection(Servo.Direction.FORWARD);
            rightGrabber.setDirection(Servo.Direction.REVERSE);
            rightGrabber.setPosition(gripperClosePosition);
            leftGrabber.setPosition(gripperClosePosition);
            telemetry.addData("Right Bumper", clawControl.right_bumper);
        }
    }
    public void moveWrist(Gamepad wristControl) {
        if (wristControl.right_stick_y > 0) {
            wristUpPosition = wrist.getPosition() + wristStep;
            if (wristUpPosition > 0.45)
                wristUpPosition = 0.45;
            wrist.setPosition(wristUpPosition);
            wristControl.right_stick_y = 0;

        }
        if (wristControl.right_stick_y < 0) {
            wristDownPosition = wrist.getPosition() - wristStep;
            if (wristUpPosition <0)
                wristUpPosition = 0;
            wrist.setPosition(wristDownPosition);
            wristControl.right_stick_y = 0;
        }
        telemetry.addData("wrist position",wrist.getPosition());
    }
    public void moveArm(Gamepad armControl) {
        double armPower = 0;

        if (armControl.left_stick_y != 0 ) {
            armMoveStep = 150;
            armPower = Math.abs(armControl.left_stick_y);
        } else{
            armMoveStep = 0;
            armPower = 1;
        }
        arm.setTargetPosition( (arm.getCurrentPosition() + (int)(armControl.left_stick_y * armMoveStep)));
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(armPower);
        telemetry.addData("arm position",arm.getCurrentPosition());
    }
    public void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower    =  x -y -yaw;
        double rightFrontPower   =  x +y +yaw;
        double leftBackPower     =  x +y -yaw;
        double rightBackPower    =  x -y +yaw;

        telemetry.addData("Manual","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", x, y, yaw);

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Send powers to the wheels.
        leftFront.setPower(leftFrontPower);
        rightFront.setPower(rightFrontPower);
        leftBack.setPower(leftBackPower);
        rightBack.setPower(rightBackPower);
    }
}