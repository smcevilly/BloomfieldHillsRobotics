package org.coding.cobra.ext;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class MoveToPresetAsync implements Action {
        private boolean initialized = false;

        DCMotorControllerEx motorController;
        int preset;
        public MoveToPresetAsync (DCMotorControllerEx motorController, int preset) {
            this.motorController = motorController;
            this.preset = preset;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (!initialized) {
                //lift.setPower(0.8);
                //initialized = true;
            }

            motorController.handlePresets(preset);
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
