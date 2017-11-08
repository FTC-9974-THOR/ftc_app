package org.firstinspires.ftc.teamcode.cv;

import android.app.Activity;
import android.graphics.Camera;
import android.widget.LinearLayout;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;

/**
 * Created by FTC on 11/4/2017.
 */
public class PreviewTestMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        boolean result = OpenCVLoader.initDebug();

        CameraBridgeViewBase camera = new CameraBridgeViewBase(hardwareMap.appContext, CameraBridgeViewBase.CAMERA_ID_FRONT) {
            @Override
            protected boolean connectCamera(int width, int height) {
                return true;
            }

            @Override
            protected void disconnectCamera() {

            }
        };

        LinearLayout layout = (LinearLayout) ((Activity) hardwareMap.appContext).findViewById(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        layout.addView(camera);
        camera.enableView();

        while (!isStopRequested()) {
            nop();
        }

        camera.disableView();
        layout.removeView(camera);
    }

    private void nop() {}
}
