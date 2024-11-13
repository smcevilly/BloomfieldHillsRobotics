package org.coding.cobra.ext;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.coding.cobra.config.helpers.DCMotorConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class AbstractMotorControllerEx {

    HardwareMap hardwareMap;
    Telemetry telemetry;
    DCMotorConfig motorConfig;


    public AbstractMotorControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, DCMotorConfig motorConfig) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetryObject;
        this.motorConfig = motorConfig;
    }


    public void handlePresets(int presetNumber) {
        handlePresets(presetNumber == 0, presetNumber == 1, presetNumber == 2, presetNumber == 3, presetNumber == 4, presetNumber == 5,  presetNumber == 6);
    }

    public void handlePresets(boolean preset0Trigerred, boolean preset1Trigerred, boolean preset2Triggerred, boolean preset3Triggerred, boolean preset4Triggerred, boolean preset5Triggerred, boolean preset6Triggerred) {
        if (preset0Trigerred)
            setTargetPosition(motorConfig.preset0);
        else if (preset1Trigerred)
            setTargetPosition(motorConfig.preset1);
        else if (preset2Triggerred)
            setTargetPosition(motorConfig.preset2);
        else if (preset3Triggerred)
            setTargetPosition(motorConfig.preset3);
        else if (preset4Triggerred)
            setTargetPosition(motorConfig.preset4);
        else if (preset5Triggerred)
            setTargetPosition(motorConfig.preset5);
        else if (preset6Triggerred)
            setTargetPosition(motorConfig.preset6);
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



    public abstract void setTargetPosition (double position);

    public abstract boolean isMotorBusy ();

    public abstract void resetToZeroPosition ();

}
