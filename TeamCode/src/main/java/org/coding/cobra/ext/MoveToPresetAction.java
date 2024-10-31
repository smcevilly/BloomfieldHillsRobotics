package org.coding.cobra.ext;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

/**
 * Supports moving one or two motors as part of action
 */
public class MoveToPresetAction implements Action {
        private boolean initialized = false;

        AbstractMotorControllerEx motorController1;
        AbstractMotorControllerEx motorController2;

        int motor1Preset;
        int motor2Preset;


        public MoveToPresetAction(AbstractMotorControllerEx motorController1, AbstractMotorControllerEx motorController2, int motor1Preset, int motor2Preset) {
            this.motorController1 = motorController1;
            this.motorController2 = motorController2;

            this.motor1Preset = motor1Preset;
            this.motor2Preset = motor2Preset;
        }

        public MoveToPresetAction(DCMotorControllerEx motorController1, int motor1Preset) {
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

                return motorController1.isMotorBusy() || motorController2.isMotorBusy();
            }
            else {
                return motorController1.isMotorBusy();
            }

        }

       /* public boolean presetSynchronous (DCMotorControllerEx motorController, int motorPreset) {
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
        */

}
