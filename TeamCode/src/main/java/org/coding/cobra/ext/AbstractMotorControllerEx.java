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
        handlePresets(presetNumber == 0, presetNumber == 1, presetNumber == 2, presetNumber == 3, presetNumber == 4);
    }

    public void handlePresets(boolean preset1Trigerred, boolean preset2Triggerred, boolean preset3Triggerred, boolean preset4Triggerred, boolean preset5Triggerred) {


        if (preset1Trigerred)
            setTargetPosition(motorConfig.preset0);

        if (preset2Triggerred)
            setTargetPosition(motorConfig.preset1);

        if (preset3Triggerred)
            setTargetPosition(motorConfig.preset2);

        if (preset4Triggerred)
            setTargetPosition(motorConfig.preset3);

        if (preset5Triggerred)
            setTargetPosition(motorConfig.preset4);

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

}
