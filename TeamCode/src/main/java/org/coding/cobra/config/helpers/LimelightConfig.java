package org.coding.cobra.config.helpers;

import com.acmerobotics.dashboard.config.Config;

@Config

public class LimelightConfig {
    /**
     * Camera Names
     */
    public String cameraName;


    // how many degrees back is your limelight rotated from perfectly vertical?
    public double limelightMountAngleDegrees = 0;

    // distance from the center of the Limelight lens to the floor
    public double limelightLensHeightInches = 3;


    public LimelightConfig(String cameraName, double limelightMountAngleDegrees,  double limelightLensHeightInches) {
        this.cameraName = cameraName;
        this.limelightLensHeightInches = limelightLensHeightInches;
        this.limelightMountAngleDegrees = limelightMountAngleDegrees;
    }
}
