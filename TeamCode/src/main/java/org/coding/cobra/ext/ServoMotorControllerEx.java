package org.coding.cobra.ext;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.coding.cobra.config.helpers.DCMotorConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Generic class to manage all motor functions for
 * Run to Position Config of DC Motor
 */

public class ServoMotorControllerEx extends AbstractMotorControllerEx{

    Servo motor;

    public ServoMotorControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, DCMotorConfig motorConfig) {
        super (hardwareMap, telemetryObject, motorConfig);
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

        telemetry.addData("Servo : ", motorConfig.motorName.toString() + " Initialized with Position, Velocity : " + motor.getPosition());
        // This will tell us the motors position on the drive hub. Anytime anything says telemtry.addData it is to send things to d the driver hub.
        motor.setPosition(Range.clip(motorConfig.startPosition, motorConfig.minPosition, motorConfig.maxPosition));
    }

    public void handleEvents (boolean upKey, boolean downKey) {

        double position = motor.getPosition();

        if   (upKey && position<motorConfig.maxPosition){
            telemetry.addData("Servo Up : ", motorConfig.motorName.toString()+ " Start Postion : "+ motor.getPosition());
            position = position + motorConfig.steps;
            motor.setPosition(position);
            telemetry.addData("Servo Up : ", motorConfig.motorName.toString()+ " Target Postion : "+ motor.getPosition());

        } else if (downKey && position>motorConfig.minPosition) {
            telemetry.addData("Servo Down : ", motorConfig.motorName.toString()+ " Start Postion : "+ motor.getPosition());
            position = position - motorConfig.steps;
            motor.setPosition(position);
            telemetry.addData("Servo Down : ", motorConfig.motorName.toString()+ " Postion : "+ motor.getPosition());
        }
    }


    @Override
    public void setTargetPosition(double position) {
        motor.setPosition(position);
    }

    public void outputTelemetry () {
        telemetry.addData("SRV: ", motorConfig.motorName.toString() + " Pos : " + motor.getPosition());
    }


    public boolean isMotorBusy() {
        return false;
    }
}
