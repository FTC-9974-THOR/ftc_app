package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by christopher.johnson on 10/18/17.
 */

public class TankDrive implements RobotDrive {

    private DcMotor fLeft, fRight, bLeft, bRight;
    private MODE mode;

    public enum MODE {
        TWO_WHEEL,
        FOUR_WHEEL
    }

    public TankDrive (HardwareMap hw, MODE mode) {
        this.mode = mode;
        if (mode == MODE.TWO_WHEEL) {
            fLeft = hw.dcMotor.get("TD-fl");
            fRight = hw.dcMotor.get("TD-fr");
        } else if (mode == MODE.FOUR_WHEEL) {
            fLeft = hw.dcMotor.get("TD-fl");
            fRight = hw.dcMotor.get("TD-fr");
            bLeft = hw.dcMotor.get("TD-bl");
            bRight = hw.dcMotor.get("TD-br");
        }
    }

    @Override
    public void move(double x, double y, double rot) {

    }

    @Override
    public void move(double lTank, double rTank) {
        if (mode == MODE.TWO_WHEEL) {
            fLeft.setPower(lTank);
            fRight.setPower(rTank);
        } else if (mode == MODE.FOUR_WHEEL) {
            fLeft.setPower(lTank);
            fRight.setPower(rTank);
            bLeft.setPower(lTank);
            bRight.setPower(rTank);
        }
    }
}
