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
            }

            return false;
            /**

            DcMotorEx motor = motorController.getRawMotor();
            double pos = motor.getCurrentPosition();
            packet.put("motor position ", pos);
            if (pos < 3000.0) {
                // keep motor powered
                return true;
            } else {
                // turn off motor
                //lift.setPower(0);
                return false;
            }
             **/
        }

}
