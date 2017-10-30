package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by christopher.johnson on 9/18/17.
 */

public class HoloXDrive {

    private DcMotor w1, w2, w3, w4;

    public HoloXDrive(HardwareMap hw) {
        w1 = hw.dcMotor.get("HX-1");
        w2 = hw.dcMotor.get("HX-2");
        w3 = hw.dcMotor.get("HX-3");
        w4 = hw.dcMotor.get("HX-4");
    }

    public void move(double x, double y, double rot) {
        w1.setPower((Math.cos(-Math.PI / 2) * x + Math.sin(-Math.PI / 2) * y + rot) / 2);
        w2.setPower((Math.cos(-(3 * Math.PI) / 2) * x + Math.sin(-(3 * Math.PI) / 2) * y + rot) / 2);
        w3.setPower((Math.cos((3 * Math.PI) / 2) * x + Math.sin((3 * Math.PI) / 2) * y + rot) / 2);
        w4.setPower((Math.cos(Math.PI / 2) * x + Math.sin(Math.PI / 2) * y + rot) / 2);
    }
}
