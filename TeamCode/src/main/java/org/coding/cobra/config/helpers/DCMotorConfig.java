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
    public double preset3;
    public double preset4;
    public double preset5;
    public double preset6;

    public DCMotorConfig(String motorName, double startPosition, double minPosition, double maxPosition, double steps, MotorDirection  direction, double power, double velocity,
                         double preset0, double preset1, double preset2, double preset3, double preset4, double preset5, double preset6) {
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
        this.preset3 = preset3;
        this.preset4 = preset4;
        this.preset5 = preset5;
        this.preset6 = preset6;
    }

    public enum MotorDirection {
        FORWARD, REVERSE
    }

}
