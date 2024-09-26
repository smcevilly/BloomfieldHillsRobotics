package org.coding.cobra.config;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class ServoMotorConfig {

    public String motorName;
    public int startPosition;
    public int minPosition;
    public int maxPosition;
    public     DcMotorSimple.Direction direction;
    public int power;

    public ServoMotorConfig(String motorName, int startPosition, int minPosition, int maxPosition, DcMotorSimple.Direction  direction, int power) {
        this.motorName = motorName;
        this.startPosition = startPosition;
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
        this.direction = direction;
        this.power = power;
    }
}
