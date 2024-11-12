package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.SystemConfig;

@TeleOp(name = "Manual Cobra Red")
public class CobraManualRed extends AbstractCobraManual {

    @Override
    public void runOpMode() throws InterruptedException {

        loadPersistance();

        float x = sharedPreferences.getFloat("x",0.0f);
        float y = sharedPreferences.getFloat("y",0.0f);
        float heading = sharedPreferences.getFloat("heading",0.0f);
        Pose2d startPos = new Pose2d(x, y, heading);
        telemetry.addData("",startPos);
        telemetry.update();

        initialize(startPos);

        executeOpMode();
    }


    @Override
    public void automationSpecimenHang() {

    }

    @Override
    public void automationPickup() {

    }
}
