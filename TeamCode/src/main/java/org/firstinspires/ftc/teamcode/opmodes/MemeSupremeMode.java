package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.TankDrive;
import org.firstinspires.ftc.teamcode.hw.MemeSupremeArm;

/**
 * Created by FTC on 11/2/2017.
 */
@TeleOp(name="Meme Supreme Mode")
public class MemeSupremeMode extends OpMode {

    TankDrive rb;
    MemeSupremeArm arm;

    @Override
    public void init() {
        rb = new TankDrive(hardwareMap, TankDrive.MODE.TWO_WHEEL);
        arm = new MemeSupremeArm(hardwareMap);
    }

    @Override
    public void loop() {
        rb.move(-gamepad1.left_stick_y, -gamepad1.right_stick_y);
        arm.moveClaw(-gamepad2.left_stick_x);
        arm.movePivot(-gamepad2.right_stick_y);
    }
}
