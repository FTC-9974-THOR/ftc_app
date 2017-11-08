package org.firstinspires.ftc.teamcode.cv.core;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;

/**
 * Created by FTC on 11/4/2017.
 */
public class Display implements CameraBridgeViewBase.CvCameraViewListener2 {

    private Context context;
    private LinearLayout cameraLayout;
    private JavaCameraView cameraView;
    private ElapsedTime fpsTimer;
    private int frames;
    private double fps;

    public Display(final Context context) {
        this.context = context;
        final int layoutId = context.getResources().getIdentifier("cameraMonitorViewId", "id", context.getPackageName());
        cameraView = new JavaCameraView(context, JavaCameraView.CAMERA_ID_BACK);
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cameraLayout = (LinearLayout) ((Activity) context).findViewById(layoutId);
                cameraLayout.addView(cameraView);
            }
        });
        cameraView.enableView();
        cameraView.setCvCameraViewListener(this);
        fpsTimer = new ElapsedTime();
        frames = 0;
        fps = 0;
    }

    public double fps() {
        return fps;
    }

    public void teardown() {
        cameraView.disableView();
        ((Activity) context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cameraLayout.removeView(cameraView);
            }
        });
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        if (fpsTimer.seconds() > 1) {
            fpsTimer.reset();
            frames = 0;
        }
        frames++;
        fps = frames / fpsTimer.seconds();
        return inputFrame.rgba();
    }
}
