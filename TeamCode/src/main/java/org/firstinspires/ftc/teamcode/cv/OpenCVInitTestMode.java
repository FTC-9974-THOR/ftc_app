package org.firstinspires.ftc.teamcode.cv;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.android.OpenCVLoader;

/**
 * Created by FTC on 11/4/2017.
 */
public class OpenCVInitTestMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        boolean result = OpenCVLoader.initDebug();

        while (!isStarted() && !isStopRequested()) {}
        if (isStopRequested()) return;

        while (!isStopRequested()) {
            telemetry.addData("Result", result);
            telemetry.update();
        }
    }
}
