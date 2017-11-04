package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hw.ManipulatorArm;

/**
 * Created by christopher.johnson on 10/4/17.
 */

@TeleOp(name="MA Direct Control")
public class MADirectControlMode extends OpMode {

    ManipulatorArm ma;

    @Override
    public void init() {
        ma = new ManipulatorArm(hardwareMap, 14.375, 10.03125);
        ma.setElbowSpeed(1);
        ma.setShoulderSpeed(1);
        telemetry.addData("Pos", ma.getShoulderTarget());
        telemetry.update();
    }

    @Override
    public void init_loop() {
        telemetry.addData("Movement", -0.005 * gamepad1.left_stick_x);
        telemetry.update();
    }

    @Override
    public void start() {
        ma.enable();
    }

    @Override
    public void loop() {
        ma.setShoulderTarget(Math.min((int) (ma.getShoulderTarget() - (30 * gamepad1.right_stick_x)), 0));
        telemetry.addData("S Target", ma.getShoulderTarget());

        ma.setElbowTarget((int) (-0.1 * gamepad1.left_stick_x));
        telemetry.addData("Movement", -0.1 * gamepad1.left_stick_x);
        telemetry.addData("E Target", ma.getElbowPos());
    }
}
