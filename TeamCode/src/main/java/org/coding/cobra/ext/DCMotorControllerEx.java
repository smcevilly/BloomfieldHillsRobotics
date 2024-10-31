package org.coding.cobra.ext;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
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

public class DCMotorControllerEx extends AbstractMotorControllerEx {

    DcMotorEx motor;
    Encoder encoder;
    int debounceCount;

    public DCMotorControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, DCMotorConfig motorConfig) {
        super (hardwareMap, telemetryObject, motorConfig);
        this.motorConfig = motorConfig;
        init();
    }

    public void init () {

        motor = hardwareMap.get(DcMotorEx.class, motorConfig.motorName);
        encoder = new OverflowEncoder(new RawEncoder(motor));

        if (motorConfig.direction == DCMotorConfig.MotorDirection.FORWARD) {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
            encoder.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        else {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
            encoder.setDirection(DcMotorSimple.Direction.REVERSE);
        }

       // resetEncoders();
        // This will tell us the motors position on the drive hub. Anytime anything says telemtry.addData it is to send things to d the driver hub.

        resetEncoders();
        motor.setPower(motorConfig.power);
        motor.setVelocity(motorConfig.velocity);
        motorConfig.startPosition = motor.getCurrentPosition();
        motor.setTargetPosition((int) Math.round(Range.clip(motorConfig.startPosition, motorConfig.minPosition, motorConfig.maxPosition)));
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        telemetry.addData("DC Motor : ", motorConfig.motorName.toString() + " Initialized with Position, Velocity : " + motor.getCurrentPosition()+","+motor.getVelocity());

    }

    public void handleEvents (float moveKey) {

        if (moveKey!=0) {
            double adjustedSteps = motorConfig.steps;
            if (debounceCount<10) {
                 //adjustedSteps /= 2;
                 adjustedSteps = adjustedSteps * debounceCount / 10;
            }
            int motorposition = motor.getCurrentPosition();
            motorposition = (int) Range.clip(motorposition +  (adjustedSteps * moveKey), motorConfig.minPosition, motorConfig.maxPosition);
            motor.setTargetPosition(motorposition);
            telemetry.addData("DC Motor : ", motorConfig.motorName.toString() + " debounce = " + debounceCount + " position=" + motor.getCurrentPosition() + "  target=" + motorposition);
            debounceCount++;
        }
        else {
            debounceCount = 0;
        }

    }


    public void setTargetPosition (double position) {
        motor.setTargetPosition((int)position);
    }

    public void resetEncoders () {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public DcMotorEx getRawMotor () {
        return motor;
    }

    public void shutdown () {
        motor.setTargetPosition (0);
    }

    public void outputTelemetry () {
        telemetry.addData("DCM: ", motorConfig.motorName.toString() + " Pos: " + motor.getCurrentPosition());
    }

    public boolean isMotorBusy() {
        return motor.isBusy();
    }
}
