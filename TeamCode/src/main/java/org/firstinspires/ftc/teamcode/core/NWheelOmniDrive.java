package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher.johnson on 9/18/17.
 */

public class NWheelOmniDrive {

    List<DcMotor> motors;
    int _w = 0;
    double theta = 0;

    public NWheelOmniDrive(HardwareMap hw, int wheels) {
        _w = wheels;
        theta = (2 * Math.PI) / _w;
        motors = new ArrayList<>(_w);
        for (int i = 0; i < _w; i++) {
            motors.set(_w, hw.dcMotor.get("NW-" + _w));
        }
    }

    public void move(double x, double y, double rot) {
        for (int i = 0; i < _w; i++) {
            motors.get(i).setPower((Math.cos(theta * i) * x + Math.sin(theta * i) * y + rot) / 2);
        }
    }
}
