package org.firstinspires.ftc.teamcode.opmodes;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
import org.firstinspires.ftc.teamcode.core.REVTouchSensor;
import org.firstinspires.ftc.teamcode.hw.DuncanGrabber;

/**
 * Created by FTC on 10/30/2017.
 */
@TeleOp(name="DG Test Mode")
public class DuncanGrabberTestMode extends OpMode {

    DuncanGrabber grabber;

    @Override
    public void init() {
        grabber = new DuncanGrabber(hardwareMap);
    }

    @Override
    public void loop() {
        grabber.move(-gamepad1.right_stick_y);
    }
}
