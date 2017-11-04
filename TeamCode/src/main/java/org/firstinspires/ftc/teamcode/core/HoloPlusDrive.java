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

        front.setDirection(DcMotorSimple.Direction.REVERSE);
        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        front.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        back.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move(double x, double y, double rot) {
        left.setPower(y - rot);
        right.setPower(y + rot);
        front.setPower(-x - rot);
        back.setPower(x - rot);
    }

    // getX and getY only work until the robot turns
    public int getX() {
        return (front.getCurrentPosition() + back.getCurrentPosition()) / 2;
    }

    public int getY() {
        return (left.getCurrentPosition() + right.getCurrentPosition()) / 2;
    }
}
