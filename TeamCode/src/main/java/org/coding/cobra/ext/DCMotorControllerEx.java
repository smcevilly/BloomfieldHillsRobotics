package org.coding.cobra.ext;

import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
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

public class DCMotorControllerEx {

    DcMotorEx motor;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    DCMotorConfig motorConfig;
    Encoder encoder;

    int debounceCount;

    public DCMotorControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, DCMotorConfig motorConfig) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetryObject;
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

    public void handlePresets (int presetNumber) {

        handlePresets (presetNumber==0, presetNumber==1, presetNumber==2, presetNumber==3, presetNumber==4);
    }

    public void handlePresets (boolean preset1Trigerred, boolean preset2Triggerred, boolean preset3Triggerred , boolean preset4Triggerred, boolean preset5Triggerred) {

        if (preset1Trigerred || preset2Triggerred || preset3Triggerred || preset4Triggerred || preset5Triggerred) {
            telemetry.addData("DCMotor : ", motorConfig.motorName.toString() + "Postion : " + motor.getCurrentPosition());


            if (preset1Trigerred)
                motor.setTargetPosition((int)motorConfig.preset0);

            if (preset2Triggerred)
                motor.setTargetPosition((int)motorConfig.preset1);

            if (preset3Triggerred)
                motor.setTargetPosition((int)motorConfig.preset2);

            if (preset4Triggerred)
                motor.setTargetPosition((int)motorConfig.preset3);

            if (preset5Triggerred)
                motor.setTargetPosition((int)motorConfig.preset4);

        }
    }

    public double [] getPresets () {
        double [] presets = new double [5];
        presets[0] = motorConfig.preset0;
        presets[1] = motorConfig.preset1;
        presets[2] = motorConfig.preset2;
        presets[3] = motorConfig.preset3;
        presets[4] = motorConfig.preset4;
        return presets;
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

}
