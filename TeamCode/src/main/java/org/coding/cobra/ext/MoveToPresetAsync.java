package org.coding.cobra.ext;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MoveToPresetAsync implements Action {
        private boolean initialized = false;

        DCMotorControllerEx motorController1;
        DCMotorControllerEx motorController2;


        int motor1Preset;
        int motor2Preset;


        public MoveToPresetAsync (DCMotorControllerEx motorController1, DCMotorControllerEx motorController2, int motor1Preset, int motor2Preset) {
            this.motorController1 = motorController1;
            this.motorController2 = motorController2;

            this.motor1Preset = motor1Preset;
            this.motor2Preset = motor2Preset;
        }

    public MoveToPresetAsync (DCMotorControllerEx motorController1, int motor1Preset) {
        this.motorController1 = motorController1;
        motorController2 = null;

        this.motor1Preset = motor1Preset;
        this.motor2Preset = -1;
    }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                //lift.setPower(0.8);
                //initialized = true;
            }

            motorController1.handlePresets(motor1Preset);
            if (motorController2!=null) {
                motorController2.handlePresets(motor2Preset);

                return motorController1.getRawMotor().isBusy() || motorController1.getRawMotor().isBusy();
            }
            else {
                return motorController1.getRawMotor().isBusy();
            }


            //return presetSynchronous(motorController1, motor1Preset) || presetSynchronous(motorController2, motor2Preset);
        }

        public boolean presetSynchronous (DCMotorControllerEx motorController, int motorPreset) {
             DcMotorEx motor = motorController.getRawMotor();
             double pos = motor.getCurrentPosition();
             double targetPosition = 0;
             double[] presets = motorController.getPresets();

             if (pos < presets[motorPreset] ) {
                 motor.setPower(0.75);
                 return false;
             }
             else if (pos > presets[motorPreset] ) {
                 motor.setPower(-0.75);
                 return false;
             }
             else {
                 // turn off motor
                 motor.setPower(0);
                 return true;
             }
        }

}
