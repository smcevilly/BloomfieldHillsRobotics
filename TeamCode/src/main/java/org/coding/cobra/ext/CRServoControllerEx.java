package org.coding.cobra.ext;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.coding.cobra.config.CRServoConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Generic class to manage all CR functions for
 */

public class CRServoControllerEx {

    CRServo crServo;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    CRServoConfig crConfig;

    public CRServoControllerEx(HardwareMap hardwareMap, Telemetry telemetryObject, CRServoConfig crConfig) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetryObject;
        this.crConfig = crConfig;
        init();
    }

    public void init () {
        crServo = hardwareMap.crservo.get(crConfig.crServoName);
        crServo.setPower(0);
    }

    public void handlePresets (boolean preset1Trigerred, boolean preset2Triggerred) {

        if (preset1Trigerred) {
            crServo.setPower(crConfig.power * (int) crConfig.preset1);
            telemetry.addData("CRServo ", crConfig.crServoName.toString() + " activated at power " + crConfig.power * (int) crConfig.preset1);
        }
        if (preset2Triggerred) {
            crServo.setPower(crConfig.power * (int) crConfig.preset2);
            telemetry.addData("CRServo ", crConfig.crServoName.toString() + " activated at power " + crConfig.power * (int) crConfig.preset2);
        }
        else {
            crServo.setPower(0);
        }


    }
}