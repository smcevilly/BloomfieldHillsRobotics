package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.AutomationConfig;

@TeleOp(name = "Manual Cobra Blue")
public class CobraManualBlue extends AbstractCobraManual {

    @Override
    public void runOpMode() throws InterruptedException {

        AutomationConfig.robotStartMode = AutomationConfig.ROBOT_START_MODE.TELEOP_BLUE;

        initialize();

        executeOpMode();
    }



}
