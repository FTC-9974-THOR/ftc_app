package org.firstinspires.ftc.teamcode.opmodes.competition;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.HoloPlusDrive;
import org.firstinspires.ftc.teamcode.hw.BalanceStoneDepressor;
import org.firstinspires.ftc.teamcode.hw.DuncanGrabber;
import org.firstinspires.ftc.teamcode.hw.ManipulatorArm;

/**
 * Created by FTC on 11/1/2017.
 */
@TeleOp(name="KI4 Competition (slow)")
public class KI4SlowCompetitionMode extends OpMode {

    DuncanGrabber grabber;
    ManipulatorArm arm;
    HoloPlusDrive rb;
    BalanceStoneDepressor depressor;

    @Override
    public void init() {
        rb = new HoloPlusDrive(hardwareMap);
        arm = new ManipulatorArm(hardwareMap, 10.125, 10.75);
        grabber = new DuncanGrabber(hardwareMap);
        depressor = new BalanceStoneDepressor(hardwareMap);
        depressor.release();
    }

    @Override
    public void start() {
        arm.enable();
        arm.setShoulderMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setElbowMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void loop() {
        if (gamepad1.left_bumper) {
            rb.move(-0.8 * gamepad1.right_stick_x, -0.8 * gamepad1.right_stick_x, 0);
        } else {
            rb.move(-0.8 * gamepad1.right_stick_x, -0.8 * gamepad1.right_stick_y, -0.5 * gamepad1.left_stick_x);
        }
        arm.setShoulderSpeed(-0.3 * gamepad2.right_stick_x);
        arm.setElbowSpeed(-0.3 * gamepad2.left_stick_x);
        if (gamepad2.left_bumper) {
            grabber.move(-0.3);
        } else if (gamepad2.right_bumper) {
            grabber.move(0.3);
        } else {
            grabber.move(0.0);
        }
        if (gamepad2.a) {
            depressor.press();
        } else if (gamepad1.b) {
            depressor.release();
        }
        telemetry.addData("Shoulder", arm.getShoulderPos());
        telemetry.addData("Elbow", arm.getElbowPos());
    }
}
