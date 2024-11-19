package org.coding.cobra;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.coding.cobra.config.AutomationConfig;
import org.coding.cobra.config.helpers.PersistanceManager;

@Autonomous(name = "Cobra Blue Left", group = "Autonomous")
public class CobraAutoBlueLeft extends AbstractCobraAutoSpecimen {

    @Override
    public void runOpMode() {

        AutomationConfig.robotStartMode = AutomationConfig.ROBOT_START_MODE.AUTO_BLUE_LEFT;

        Pose2d startPosition = new Pose2d(6.5, 62.00, Math.toRadians(270));

        initialize();

        operateRunMode ();

    }


}
