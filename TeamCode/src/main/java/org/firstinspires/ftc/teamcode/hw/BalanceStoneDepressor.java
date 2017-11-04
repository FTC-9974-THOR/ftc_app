package org.firstinspires.ftc.teamcode.hw;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by FTC on 11/3/2017.
 */
public class BalanceStoneDepressor {

    private Servo depressor;

    public BalanceStoneDepressor(HardwareMap hw) {
        depressor = hw.servo.get("BSD-depressor");
    }

    public void setDepressorPos(double pos) {
        depressor.setPosition(pos);
    }

    public void press() {
        setDepressorPos(1);
    }

    public void release() {
        setDepressorPos(0);
    }
}