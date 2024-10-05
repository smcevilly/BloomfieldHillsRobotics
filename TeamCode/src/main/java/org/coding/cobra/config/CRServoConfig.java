package org.coding.cobra.config;

public class CRServoConfig {

    public String crServoName;
    public double power;
    public double preset1;
    public double preset2;

    public CRServoConfig(String crServoName,
                         double power,
                         double preset1, double preset2) {
        this.crServoName = crServoName;
        this.power = power;
        this.preset1 = preset1;
        this.preset2 = preset2;
    }
}