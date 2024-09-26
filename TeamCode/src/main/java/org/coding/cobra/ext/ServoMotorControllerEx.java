package org.coding.cobra.ext;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.coding.cobra.config.DCMotorConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Generic class to manage all motor functions for
 * Run to Position Config of DC Motor
 */

public class ServoMotorControllerEx {

    Servo motor;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    DCMotorConfig motorConfig;

    public ServoMotorControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, DCMotorConfig motorConfig) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetryObject;
        this.motorConfig = motorConfig;
        init();
    }

    public void init () {

        motor = hardwareMap.get(Servo.class, motorConfig.motorName);

        if (motorConfig.direction == DCMotorConfig.MotorDirection.FORWARD) {
            motor.setDirection(Servo.Direction.FORWARD);
        }
        else {
            motor.setDirection(Servo.Direction.REVERSE);
        }

        telemetry.addData("Servo ", motorConfig.motorName, " Initialized with Position, Velocity : ", motor.getPosition());
        // This will tell us the motors position on the drive hub. Anytime anything says telemtry.addData it is to send things to d the driver hub.
        motor.setPosition(Range.clip(motorConfig.startPosition, motorConfig.minPosition, motorConfig.maxPosition));

    }

    public void handleEvents (boolean upKey, boolean downKey) {

        double position = motor.getPosition();

        if   (upKey && position<motorConfig.maxPosition){
            position = position + motorConfig.steps;
            motor.setPosition(position);
        } else if (downKey && position>motorConfig.minPosition) {
            position = position - motorConfig.steps;
            motor.setPosition(position);
        }

    }


    public void handlePresets (boolean preset1Trigerred, boolean preset2Triggerred, boolean preset3Triggerred ) {

        if (preset1Trigerred)
            motor.setPosition(motorConfig.preset1);

        if (preset2Triggerred)
            motor.setPosition(motorConfig.preset2);

        if (preset3Triggerred)
            motor.setPosition(motorConfig.preset3);

    }
}
