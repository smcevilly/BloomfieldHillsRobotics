package org.coding.cobra;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.AutomationConfig;

@Autonomous(name = "Cobra Red Right", group = "Autonomous")
public class CobraAutoRedRight extends AbstractCobraAutoSpecimen {

    @Override
    public void runOpMode() {

        AutomationConfig.robotStartMode = AutomationConfig.ROBOT_START_MODE.AUTO_RED_RIGHT;

        initialize();

        operateRunMode ();

    }


}
