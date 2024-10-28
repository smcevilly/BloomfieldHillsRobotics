package org.coding.cobra.config;

import com.acmerobotics.dashboard.config.Config;

@Config

public class LimelightConfig {
    /**
     * Camera Names
     */
    public String cameraName;

    public LimelightConfig(String cameraName) {
        this.cameraName = cameraName;
    }
}
