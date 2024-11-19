package org.coding.cobra;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.coding.cobra.config.AutomationConfig;
import org.coding.cobra.config.SystemConfig;
import org.coding.cobra.config.helpers.PersistanceManager;
import org.coding.cobra.ext.DCMotorControllerEx;
import org.coding.cobra.ext.LimelightEx;
import org.coding.cobra.ext.MecanumDriveEx;
import org.coding.cobra.ext.MoveToPresetAction;
import org.coding.cobra.ext.ServoMotorControllerEx;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.Drawing;

public  abstract class CobraBase extends LinearOpMode {

    AutomationConfig AUTO_CONFIG = new AutomationConfig();

    Pose3D botpose;
    SystemConfig sysConfig = new SystemConfig();

    MecanumDriveEx mecanumDrive;
    DCMotorControllerEx armExtenderMotor;

    ServoMotorControllerEx clawRotator, flexiClawLeft, flexiClawRight;
    LimelightEx camera;

    DCMotorControllerEx leftElevator;
    DCMotorControllerEx rightElevator;

    PersistanceManager persistanceManager;

    Pose2d startPosition;

    public void initialize () {
        persistanceManager = new PersistanceManager(hardwareMap);

        startPosition = AUTO_CONFIG.getRobotStartPosition();

        if (startPosition == null) {
            this.startPosition = persistanceManager.getLastStoredPosition();
        }

        this.startPosition = startPosition;

        mecanumDrive = new MecanumDriveEx(hardwareMap, telemetry, startPosition);
        leftElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Left_Elevator);
        rightElevator = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.Right_Elevator);
        armExtenderMotor = new DCMotorControllerEx(hardwareMap, telemetry, sysConfig.ARM_EXTENDER);
        // To delay the start of the claw rotator movement after init is pressed
        //
        // clawRotator = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.CLAW_ROTATOR);
        flexiClawLeft = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_L);
        flexiClawRight = new ServoMotorControllerEx(hardwareMap, telemetry, sysConfig.FLEXI_CLAW_MOTOR_R);
        camera = new LimelightEx(hardwareMap, telemetry,  sysConfig.CAMERA);
        camera.init();
    }

    public void telemetryOutput () {

        if (debounce <10)
            debounce++;
        else {
            debounce=0;

            armExtenderMotor.outputTelemetry();
            leftElevator.outputTelemetry();
            rightElevator.outputTelemetry();
            clawRotator.outputTelemetry();
            flexiClawLeft.outputTelemetry();
            flexiClawRight.outputTelemetry();

            mecanumDrive.updateRobotPose();

            if (botpose != null) {
                telemetry.addData("Botpose ", botpose.toString());
            }

            TelemetryPacket packet = new TelemetryPacket();
            packet.fieldOverlay().setStroke("#3F51B5");
            Drawing.drawRobot(packet.fieldOverlay(), mecanumDrive.pose);
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
            telemetry.update();

        }

    }

    int debounce = 0;


    public void automationPickup () {

        // Pickup the object from ground

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 5, 5), // pickup level
                        new SleepAction(0.5),
                        new MoveToPresetAction(clawRotator, 1), // rotate claw down
                        new SleepAction(0.5),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight, 0,0), //opens claw
                        new SleepAction(0.5),
                        new MoveToPresetAction(armExtenderMotor,5), //Extends arm forward
                        new SleepAction(1),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight, 1,1), // pickup
                        new SleepAction(0.5),
                        new MoveToPresetAction(leftElevator, rightElevator, 6, 6), // level up
//                        new SleepAction(0.5),
                        new MoveToPresetAction(armExtenderMotor,0),
//                        new SleepAction(0.5),
                        new MoveToPresetAction(clawRotator, 0), // rotate claw down
//                        new SleepAction(0.5),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0) // level up
                        //Extends arm forward
                        //  new MoveToPresetAction(clawRotator, 0) // rotate claw to face straing
                )
        );
    }

    public void tracePathToBar() {
        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(clawRotator, 0)
                ));

        Action actionMoveCloserToBar = AUTO_CONFIG.getTracePathToBarTrajectory(mecanumDrive).build();

        Actions.runBlocking(
                new SequentialAction(
                        actionMoveCloserToBar));
    }


    public void automationSpecimenHang () {

        Actions.runBlocking(
                new SequentialAction(
                        new MoveToPresetAction(leftElevator, rightElevator, 2, 2),
                        new SleepAction(0.7),
                        new MoveToPresetAction(armExtenderMotor, 2),
                        new SleepAction(0.7),
                        new MoveToPresetAction(leftElevator, rightElevator, 3, 3),
                        new SleepAction(0.3),
                        new MoveToPresetAction(armExtenderMotor, 3),
                        new SleepAction(0.3),
                        new MoveToPresetAction(flexiClawLeft, flexiClawRight , 0, 0)
                )
        );

        Actions.runBlocking(
                new SequentialAction(
                        new SleepAction(0.2),
                        new MoveToPresetAction(armExtenderMotor, 0),
                        new MoveToPresetAction(leftElevator, rightElevator, 0, 0),
                        new MoveToPresetAction(clawRotator, 0)

        ));

    }
}
