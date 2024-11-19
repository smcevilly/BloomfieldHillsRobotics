package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.AutomationConfig;

@Autonomous(name = "Cobra Red Left", group = "Autonomous")
public class CobraAutoRedLeft extends AbstractCobraAutoSpecimen {

    @Override
    public void runOpMode() {

        AutomationConfig.robotStartMode = AutomationConfig.ROBOT_START_MODE.AUTO_RED_LEFT;

        initialize();

        operateRunMode ();

    }


}
