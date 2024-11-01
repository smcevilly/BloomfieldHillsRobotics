package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;

@TeleOp(name = "Manual Cobra Red")
public class CobraManualRed extends AbstractCobraManual {

    @Override
    public void runOpMode() throws InterruptedException {

        initialize(new Pose2d(61, -61.00, Math.toRadians(90)));

        executeOpMode();
    }


    @Override
    public void automationSpecimenHang() {

    }
}
