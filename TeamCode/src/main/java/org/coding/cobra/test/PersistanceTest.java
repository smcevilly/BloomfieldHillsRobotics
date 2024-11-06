package org.coding.cobra.test;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "Persistance Test")
public class PersistanceTest extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        //This is telling the robot to wait until start is clicked on the driver hub.

        SharedPreferences sharedPreferences;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Persisting position:


        editor.putFloat("x", 0);
        editor.putFloat("y", 0);
        editor.putFloat("heading", 0);

        editor.apply();


        // Continuous loop
        while (opModeIsActive()) {

            if (isStopRequested()) return;
        }

    }

}

