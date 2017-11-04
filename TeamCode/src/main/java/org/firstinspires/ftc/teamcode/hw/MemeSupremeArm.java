package org.firstinspires.ftc.teamcode.hw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by FTC on 11/2/2017.
 */
public class MemeSupremeArm {

    DcMotor pivot;
    Servo claw;

    public MemeSupremeArm(HardwareMap hw) {
        pivot = hw.dcMotor.get("MSA-pivot");
        claw = hw.servo.get("MSA-claw");
        claw.setPosition(0);
    }

    public void moveClaw(double movement) {
        claw.setPosition(Range.clip(claw.getPosition() + movement, 0, 180));
    }

    public void movePivot(double movement) {
        pivot.setPower(movement);
    }
}
