package org.firstinspires.ftc.teamcode.hw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by FTC on 10/30/2017.
 */
public class DuncanGrabber {

    private DcMotor pinion;

    public DuncanGrabber(HardwareMap hw) {
        pinion = hw.dcMotor.get("DG-pinion");
        pinion.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move(double movement) {
        pinion.setPower(movement);
    }

    public int position() {
        return pinion.getCurrentPosition();
    }
}
