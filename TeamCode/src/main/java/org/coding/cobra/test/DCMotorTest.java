package org.coding.cobra.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Motor Test")
public class DCMotorTest extends LinearOpMode {


    private DcMotorEx testMotor;
    // Naming the motor

    int MOTOR_START_POSITION = 100;
    // This is the motor start position we can adjust this by adjusting the number above.

    double ARMPOWER=1.0;
    //Motor Power

    int armposition = 0;
    // Starting arm position (Arm position as soon as you start the program)

    int ARM_MIN = 100, ARM_MAX = 3300;
    // This is our arm's minimum and maximum positions.

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        //This is telling the robot to wait until start is clicked on the driver hub.

        testMotor = hardwareMap.get(DcMotorEx.class, "TestMotor");
        // Above we are naming our motor.
        testMotor.setDirection(DcMotorEx.Direction.FORWARD);
        // We are setting the direction of the motor if it is forwards or backwards.
        telemetry.addData("TestMotor", testMotor.getCurrentPosition()+" "+testMotor.getVelocity());
        // This will tell us the motors position on the drive hub. Anytime anything says telemtry.addData it is to send things to d the driver hub.
        testMotor.setTargetPosition(MOTOR_START_POSITION);
        // This is our motor start position which is stated in line 15.

        testMotor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        // Continuous loop
        while (opModeIsActive()) {

            moveMotor ();
            if (isStopRequested()) return;
        }

    }

    public void moveMotor () {

        armposition = testMotor.getCurrentPosition();
        telemetry.addData("motor ", "position=" + testMotor.getCurrentPosition() + "  target=" + armposition);

        if (gamepad2.left_stick_y == 1 && armposition < ARM_MAX) {
            // Gamepad controls and arm movement.

            armposition = armposition + 15;
            // This isi how much to move the arm so we will move it 15 steps if you click the button on the controller.


        } else if (gamepad2.left_stick_y == -1 && armposition > ARM_MIN) {
            // Same thing as above it is just for moving the arm down instead of up.

            armposition = armposition - 15;
            // Same thing as above it is just for moving the arm down instead of up.
        }
        testMotor.setPower(ARMPOWER);
        // We are setting how much power the number is set on code line 18
        testMotor.setTargetPosition(armposition);
        // This is our arm position target
        telemetry.addData("TestMotor", "position=" + testMotor.getCurrentPosition() + "  target=" + armposition);
        // Displaying things on the driver hub.

    }

}

