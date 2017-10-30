package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.VuforiaKey;
import org.firstinspires.ftc.teamcode.core.KiwiDrive;

/**
 * Created by christopher.johnson on 10/11/17.
 */

@Autonomous(name="Glyph Auto")
public class GlyphAutoMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        KiwiDrive rb = new KiwiDrive(hardwareMap, this);

        while (!isStarted()) {
            telemetry.addLine("Calibration status");
            byte calStatus = rb.imu.getCalibrationStatus().calibrationStatus;
            telemetry.addData("SYS Cal", (calStatus >> 6) & 0x3);
            telemetry.addData("GYR Cal", (calStatus >> 4) & 0x3);
            telemetry.addData("ACC Cal", (calStatus >> 2) & 0x3);
            telemetry.addData("MAG Cal", calStatus & 0x3);
        }

        VuforiaLocalizer vuforia;
        VuforiaTrackables keymarks;
        VuforiaTrackable template;

        int previewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(previewId);
        parameters.vuforiaLicenseKey = VuforiaKey.key;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        keymarks = vuforia.loadTrackablesFromAsset("RelicVuMark");
        template = keymarks.get(0);

        keymarks.activate();

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(template);
        while (vuMark != RelicRecoveryVuMark.UNKNOWN && !isStopRequested()) {
            vuMark = RelicRecoveryVuMark.from(template);
        }


        rb.imu.startAccelerationIntegration(rb.imu.getPosition(), rb.imu.getVelocity(), 5);
        rb.move(-1, 0, 0);
        while (rb.imu.getPosition().toUnit(DistanceUnit.INCH).x < 27 + ((vuMark.ordinal() - 1) * 8) && !isStopRequested()) {
            telemetry.addData("Pos X", rb.imu.getPosition().toUnit(DistanceUnit.INCH).x);
            telemetry.update();
        }
        rb.move(0, 0, 0);
        sleep(300);
        rb.move(0, 1, 0);
        while (rb.imu.getPosition().toUnit(DistanceUnit.INCH).y < 3 && !isStopRequested()) {
            telemetry.addData("Pos X", rb.imu.getPosition().toUnit(DistanceUnit.INCH).x);
            telemetry.update();
        }
        rb.move(0, 0, 0);
    }
}
