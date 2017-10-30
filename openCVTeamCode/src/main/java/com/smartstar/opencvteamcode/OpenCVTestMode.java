package com.smartstar.opencvteamcode;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;

/**
 * Created by christopher.johnson on 10/9/17.
 */

@TeleOp(name="OpenCV tests")
//@SuppressWarnings("WeakerAccess")
public class OpenCVTestMode extends OpMode implements CameraBridgeViewBase.CvCameraViewListener2 {

    GlyphPipeline pipeline;
    Mat frame;
    OpenCVLoader ocvl;

    @Override
    public void init() {
        ocvl = new OpenCVLoader();
        ocvl.safeInit(hardwareMap.appContext);
        pipeline = new GlyphPipeline();
        LinearLayout preview = (LinearLayout) ((FtcRobotControllerActivity) hardwareMap.appContext).findViewById(hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName()));
        JavaCameraView javaCameraView = new JavaCameraView(hardwareMap.appContext, CameraBridgeViewBase.CAMERA_ID_BACK);
        javaCameraView.enableView();
        preview.addView(javaCameraView);
        javaCameraView.setVisibility(View.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    public void loop() {
        pipeline.setswitch0(gamepad1.a);
        for (MatOfPoint matOfPoint : pipeline.findContoursOutput()) {
            double x = 0;
            double y = 0;
            for (Point point : matOfPoint.toList()) {
                x += point.x;
                y += point.y;
            }
            x /= matOfPoint.toArray().length;
            y /= matOfPoint.toArray().length;
            telemetry.addLine("Contour " + pipeline.findContoursOutput().indexOf(matOfPoint));
            telemetry.addData("X", x);
            telemetry.addData("Y", y);
        }
        telemetry.addData("Color", (gamepad1.a) ? "Brown" : "Grey");
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        frame = new Mat(height, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        frame.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        frame = inputFrame.rgba();
        pipeline.process(frame);
        return pipeline.maskOutput();
    }
}
