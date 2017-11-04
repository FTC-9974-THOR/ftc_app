package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by FTC on 10/30/2017.
 */
public class REVTouchSensor {

    DigitalChannel channel;

    public REVTouchSensor(String name, HardwareMap hw) {
        channel = hw.digitalChannel.get(name);
        channel.setMode(DigitalChannel.Mode.INPUT);
    }

    public REVTouchSensor(DigitalChannel channel) {
        this.channel = channel;
        this.channel.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean isPressed() {
        return !channel.getState();
    }
}
