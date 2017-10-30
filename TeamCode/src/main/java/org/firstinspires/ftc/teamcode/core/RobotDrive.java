package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by christopher.johnson on 9/18/17.
 */

public interface RobotDrive {

    public void move(double x, double y, double rot);
    public void move(double lTank, double rTank);
}
