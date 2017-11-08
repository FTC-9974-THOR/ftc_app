package org.firstinspires.ftc.teamcode.cv;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.smartstar.opencvteamcode.core.Display;

/**
 * Created by FTC on 11/4/2017.
 */
@TeleOp(name="Display Test")
public class DisplayTestMode extends LinearOpMode {

    Display display;

    @Override
    public void runOpMode() throws InterruptedException {
        display = new Display(hardwareMap.appContext);
        while (!isStopRequested()) {
            telemetry.addData("FPS", display.fps());
            telemetry.update();
        }
        display.teardown();
    }
}
