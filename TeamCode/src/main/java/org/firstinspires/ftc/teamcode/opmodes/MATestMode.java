package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.hw.ManipulatorArm;

/**
 * Created by christopher.johnson on 9/18/17.
 */

@TeleOp(name="MA IK Test")
public class MATestMode extends OpMode {

    ManipulatorArm arm;

    @Override
    public void init() {
        arm = new ManipulatorArm(hardwareMap, 14.375, 10.03125);
        arm.setShoulderTarget(0);
        arm.setShoulderSpeed(1);
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_left) {
            arm.setShoulderTarget(arm.getShoulderTarget() + 1);
        } else if (gamepad1.dpad_right) {
            arm.setShoulderTarget(arm.getShoulderTarget() - 1);
        }

        if (gamepad1.dpad_up) {
            arm.moveElbow(1);
        } else if (gamepad1.dpad_down) {
            arm.moveElbow(-1);
        }

        if (gamepad1.left_bumper) {
            arm.brake();
        } else if (gamepad1.right_bumper) {
            arm.coast();
        }
    }
}
