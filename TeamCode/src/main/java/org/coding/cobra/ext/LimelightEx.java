package org.coding.cobra.ext;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.coding.cobra.config.LimelightConfig;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

import java.util.List;

public class LimelightEx {

    Limelight3A camera;
    HardwareMap hardwareMap;
    Telemetry telemetry;
    LimelightConfig cameraConfig;

    public LimelightEx(HardwareMap hardwareMap, Telemetry telemetryObject, LimelightConfig cameraConfig) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetryObject;
        this.cameraConfig = cameraConfig;
        init();
    }

    public void init() {
        camera = hardwareMap.get(Limelight3A.class, cameraConfig.cameraName);
        telemetry.setMsTransmissionInterval(11);
        camera.pipelineSwitch(0);
        camera.start();
        telemetry.addData("Limelight", "Initialized");
        telemetry.update();
    }


    public Pose3D getRobotPosition () {

        LLResult result = camera.getLatestResult();
        if (result != null) {
            if (result.isValid()) {
                return result.getBotpose();
            }
        }
        return null;
    }

    public void getResults() {
        LLResult result = camera.getLatestResult();
        if (result != null) {
            // Get Bot Position
            Pose3D botpose = result.getBotpose();
            if (result.isValid()) {
                telemetry.addData("tx", result.getTx());
                telemetry.addData("txnc", result.getTxNC());
                telemetry.addData("ty", result.getTy());
                telemetry.addData("tync", result.getTyNC());
                telemetry.addData("Botpose", botpose.toString());
                // Access barcode results
                List<LLResultTypes.BarcodeResult> barcodeResults = result.getBarcodeResults();
                for (LLResultTypes.BarcodeResult br : barcodeResults) {
                    telemetry.addData("Barcode", "Data: %s", br.getData());
                }
                // Access detector results
                List<LLResultTypes.DetectorResult> detectorResults = result.getDetectorResults();
                for (LLResultTypes.DetectorResult dr : detectorResults) {
                    telemetry.addData("Detector", "Class: %s, Area: %.2f", dr.getClassName(), dr.getTargetArea());
                }
            }
        } else {
            telemetry.addData("Limelight", "No data available");
        }
        telemetry.update();
    }
    public void handleEvents(boolean dpadLeft, boolean dpadRight) {
        int pipelinenumber = 0;
        if (dpadLeft) {
            pipelinenumber = 1;
            camera.pipelineSwitch(pipelinenumber);
            telemetry.addData("pipelinenumber", pipelinenumber);
            getResults();
        } else if (dpadRight) {
            pipelinenumber = 0;
            camera.pipelineSwitch(pipelinenumber);
            telemetry.addData("pipelinenumber", pipelinenumber);
            getResults();
        }
    }
}