package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by christopher.johnson on 9/18/17.
 */

public class HoloPlusDrive {

    private DcMotor left, right, front, back;

    public HoloPlusDrive(HardwareMap hw) {
        left = hw.dcMotor.get("HP-left");
        right = hw.dcMotor.get("HP-right");
        front = hw.dcMotor.get("HP-front");
        back = hw.dcMotor.get("HP-back");

        left.setDirection(DcMotorSimple.Direction.REVERSE);
        back.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(double x, double y, double rot) {
        double rx = (x + rot);
        double ry = (y + rot);
        left.setPower(ry);
        right.setPower(ry);
        front.setPower(rx);
        back.setPower(rx);
    }

    // getX and getY only work until the robot turns
    public int getX() {
        return (front.getCurrentPosition() + back.getCurrentPosition()) / 2;
    }

    public int getY() {
        return (left.getCurrentPosition() + right.getCurrentPosition()) / 2;
    }
}
