package org.coding.cobra.config.helpers;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

public class PersistanceManager {

    public SharedPreferences sharedPreferences;

    public HardwareMap hardwareMap;

    public PersistanceManager (HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        loadPersistance();
    }

    public void loadPersistance () {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);
    }

    public Pose2d getLastStoredPosition () {
        Pose2d startPos;
        float x = sharedPreferences.getFloat("x",0.0f);
        float y = sharedPreferences.getFloat("y",0.0f);
        float heading = sharedPreferences.getFloat("heading",0.0f);
        startPos = new Pose2d(x, y, heading);
        return startPos;
    }

    public void storePosition (Pose2d position) {

        if (position == null) return;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Persisting position:

        editor.putFloat("x", (float)position.position.x);
        editor.putFloat("y", (float)position.position.y);
        editor.putFloat("heading", (float)position.heading.toDouble());

        editor.apply();
    }


}
