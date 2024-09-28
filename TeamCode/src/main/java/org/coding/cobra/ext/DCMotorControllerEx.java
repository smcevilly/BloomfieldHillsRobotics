package org.coding.cobra.ext;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.coding.cobra.config.helpers.DCMotorConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Generic class to manage all motor functions for
 * Run to Position Config of DC Motor
 */

public class DCMotorControllerEx {

    DcMotorEx motor;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    DCMotorConfig motorConfig;

    public DCMotorControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, DCMotorConfig motorConfig) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetryObject;
        this.motorConfig = motorConfig;
        init();
    }

    public void init () {

        motor = hardwareMap.get(DcMotorEx.class, motorConfig.motorName);

        if (motorConfig.direction == DCMotorConfig.MotorDirection.FORWARD) {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        else {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        telemetry.addData("Motor ", motorConfig.motorName, " Initialized with Position, Velocity : ", motor.getCurrentPosition()+","+motor.getVelocity());
        // This will tell us the motors position on the drive hub. Anytime anything says telemtry.addData it is to send things to d the driver hub.
        motor.setTargetPosition((int) Math.round(Range.clip(motorConfig.startPosition, motorConfig.minPosition, motorConfig.maxPosition)));

    }

    public void handleEvents (float moveKey) {

            int motorposition = motor.getCurrentPosition();
            telemetry.addData("motor ", "position=" + motor.getCurrentPosition() + "  target=" + motorposition);

            if (moveKey == 1 && motorposition < motorConfig.maxPosition) {
                // Gamepad controls and arm movement.

                motorposition = motorposition + (int)motorConfig.steps;
                // This isi how much to move the arm so we will move it 15 steps if you click the button on the controller.


            } else if (moveKey == -1 && motorposition > motorConfig.maxPosition) {
                // Same thing as above it is just for moving the arm down instead of up.

                motorposition = motorposition - (int)motorConfig.steps;
                // Same thing as above it is just for moving the arm down instead of up.
            }
            motor.setPower(motorConfig.power);
            // We are setting how much power the number is set on code line 18
            motor.setTargetPosition(motorposition);
            // This is our arm position target
            telemetry.addData("Motor ", "position=" + motor.getCurrentPosition() + "  target=" + motorposition);
            // Displaying things on the driver hub.
    }

    public void handlePresets (boolean preset1Trigerred, boolean preset2Triggerred, boolean preset3Triggerred ) {

        if (preset1Trigerred)
            motor.setTargetPosition((int)motorConfig.preset1);

        if (preset2Triggerred)
            motor.setTargetPosition((int)motorConfig.preset2);

        if (preset3Triggerred)
            motor.setTargetPosition((int)motorConfig.preset3);

    }
}
