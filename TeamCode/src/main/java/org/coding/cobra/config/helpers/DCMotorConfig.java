package org.coding.cobra.config.helpers;

public class DCMotorConfig {

    public String motorName;
    public double startPosition;
    public double minPosition;
    public double maxPosition;
    public double steps;
    public MotorDirection direction; //    DcMotorSimple.Direction direction;
    public double power;
    public double velocity;
    public double preset0;
    public double preset1;
    public double preset2;

    public DCMotorConfig(String motorName, double startPosition, double minPosition, double maxPosition, double steps, MotorDirection  direction, double power, double velocity,
                         double preset0, double preset1, double preset2) {
        this.motorName = motorName;
        this.startPosition = startPosition;
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
        this.steps = steps;
        this.direction = direction;
        this.power = power;
        this.velocity = velocity;
        this.preset0 = preset0;
        this.preset1 = preset1;
        this.preset2 = preset2;
    }

    public enum MotorDirection {
        FORWARD, REVERSE
    }

}
