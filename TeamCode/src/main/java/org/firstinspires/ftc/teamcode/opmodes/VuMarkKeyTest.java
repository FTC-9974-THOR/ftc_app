package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.VuforiaKey;

/**
 * Created by christopher.johnson on 9/18/17.
 *
 * Testing OpMode for Vuforia (specifically the VuMarks)
 */

public class VuMarkKeyTest extends OpMode {

    VuforiaLocalizer vuforia;
    VuforiaTrackables keymarks;
    VuforiaTrackable template;

    @Override
    public void init() {
        int previewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(previewId);
        parameters.vuforiaLicenseKey = VuforiaKey.key;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        keymarks = vuforia.loadTrackablesFromAsset("RelicVuMark");
        template = keymarks.get(0);
    }

    @Override
    public void start() {
        keymarks.activate();
    }

    @Override
    public void loop() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(template);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            telemetry.addData("VuMark", "%s visible", vuMark);
        }
    }
}
