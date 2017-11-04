package org.firstinspires.ftc.teamcode.opmodes.competition;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.core.HoloPlusDrive;

/**
 * Created by FTC on 11/3/2017.
 */
public class KI4SafezoneAuto extends LinearOpMode {

    HoloPlusDrive rb;

    @Override
    public void runOpMode() throws InterruptedException {
        rb = new HoloPlusDrive(hardwareMap);
        ElapsedTime time = new ElapsedTime();

        while (!isStarted() && !isStopRequested()) {
            telemetry.addLine("Ready");
            telemetry.update();
        }

        if (isStopRequested()) {
            return;
        }

        time.reset();
        rb.move(0, 1, 0);
        while (!isStopRequested() && time.seconds() < 3) {
            telemetry.addLine("Moving");
            telemetry.addData("Time", time.seconds());
            telemetry.update();
        }
        rb.move(0, 0, 0);
    }
}
