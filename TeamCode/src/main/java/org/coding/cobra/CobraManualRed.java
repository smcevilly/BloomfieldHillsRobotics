package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.coding.cobra.config.AutomationConfig;
import org.coding.cobra.config.SystemConfig;

@TeleOp(name = "Manual Cobra Red")
public class CobraManualRed extends AbstractCobraManual {



    @Override
    public void runOpMode() throws InterruptedException {

        AutomationConfig.robotStartMode = AutomationConfig.ROBOT_START_MODE.TELEOP_RED;

        initialize();

         executeOpMode();
    }

}
