package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.AutomationConfig;

@Autonomous(name = "Cobra Blue Right", group = "Autonomous")
public class CobraAutoBlueRight extends AbstractCobraAutoSpecimen {

    @Override
    public void runOpMode() {

        AutomationConfig.robotStartMode = AutomationConfig.ROBOT_START_MODE.AUTO_BLUE_RIGHT;

        initialize();

        operateRunMode ();

    }


}
