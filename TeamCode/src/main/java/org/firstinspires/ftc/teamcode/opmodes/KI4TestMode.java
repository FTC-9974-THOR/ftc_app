package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.HoloPlusDrive;

/**
 * Created by christopher.johnson on 10/22/17.
 */

@TeleOp(name="KI4 Test Mode")
public class KI4TestMode extends OpMode {

    HoloPlusDrive rb;

    @Override
    public void init() {
        rb = new HoloPlusDrive(hardwareMap);
    }

    @Override
    public void loop() {
        rb.move(-gamepad1.right_stick_x, -gamepad1.right_stick_y, -gamepad1.left_stick_x);
    }
}
