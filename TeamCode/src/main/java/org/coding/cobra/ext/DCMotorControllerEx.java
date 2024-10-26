package org.coding.cobra.ext;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.coding.cobra.CobraAuto;
import org.coding.cobra.config.helpers.DCMotorConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MecanumDrive;

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
            int motorposition = motor.getCurrentPosition();
            motorposition = (int) Range.clip(motorposition +  (motorConfig.steps * moveKey), motorConfig.minPosition, motorConfig.maxPosition);
            motor.setTargetPosition(motorposition);
            telemetry.addData("DC Motor : ", motorConfig.motorName.toString() + " position=" + motor.getCurrentPosition() + "  target=" + motorposition);
        }

    }

    public void handlePresets (int presetNumber) {

        handlePresets (presetNumber==0, presetNumber==1, presetNumber==2);
    }

    public void handlePresets (boolean preset1Trigerred, boolean preset2Triggerred, boolean preset3Triggerred ) {

        if (preset1Trigerred)
            motor.setTargetPosition((int)motorConfig.preset1);

        if (preset2Triggerred)
            motor.setTargetPosition((int)motorConfig.preset2);

        if (preset3Triggerred)
            motor.setTargetPosition((int)motorConfig.preset3);

        if (preset1Trigerred || preset2Triggerred || preset3Triggerred) {
            telemetry.addData("DCMotor : ", motorConfig.motorName.toString() + "Postion : " + motor.getCurrentPosition());
        }

    }

    public void resetEncoders () {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public DcMotorEx getRawMotor () {
        return motor;
    }

}
