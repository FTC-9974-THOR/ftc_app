package org.firstinspires.ftc.teamcode.hw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by christopher.johnson on 9/18/17.
 *
 * Hardware class for the manipulator arm.
 * Uses inverse kinematics (IK) equations
 * developed by THOR.
 */

public class ManipulatorArm {

    private DcMotor shoulder;
    private DcMotor elbow;

    private double upperArm;
    private double lowerArm;

    public enum ControlMode {
        IK,
        MANUAL
    }

    public ManipulatorArm(HardwareMap hw, double upper, double lower) {
        shoulder = hw.dcMotor.get("MA-shoulder");
        elbow = hw.dcMotor.get("MA-elbow");

        upperArm = upper;
        lowerArm = lower;

        shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void enable() {
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Turns off motor braking. Decreases control, but the motor will last far longer.
     */
    public void coast() {
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    /**
     * Turns on motor braking. Increases control, but harder on the motor.
     */
    public void brake() {
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setShoulderMode(DcMotor.RunMode mode) {
        shoulder.setMode(mode);
    }

    /**
     * Set target pos of shoulder
     * @param pos target
     */
    public void setShoulderTarget(int pos) {
        shoulder.setTargetPosition(pos);
    }

    /**
     * Set shoulder movement speed during position seeking
     * @param speed movement speed
     */
    public void setShoulderSpeed(double speed) {
        shoulder.setPower(speed);
    }

    /**
     * Gets encoder target of shoulder
     * @return target
     */
    public int getShoulderTarget() {
        return shoulder.getTargetPosition();
    }

    public void setElbowMode(DcMotor.RunMode mode) {
        elbow.setMode(mode);
    }

    /**
     * Set target pos of elbow
     * @param pos target
     */
    public void setElbowTarget(int pos) {
        elbow.setTargetPosition(pos);
    }

    /**
     * Set elbow movement speed during position seeking
     * @param speed movement speed
     */
    public void setElbowSpeed(double speed) {
        elbow.setPower(speed);
    }

    /**
     * Gets encoder target of elbow
     * @return target
     */
    public int getElbowTarget() {
        return elbow.getTargetPosition();
    }
    /**
     * Get raw encoder ticks from the shoulder
     * @return ticks
     */
    public int getShoulderPos() {
        return shoulder.getCurrentPosition();
    }

    /**
     * Gets the raw position of the elbow
     * @return a value between 0 and 1 that represents the position of the servo
     */
    public double getElbowPos() {
        return elbow.getCurrentPosition();
    }

    /**
     * Calculates angle of shoulder from encoder ticks
     * @return angle, in radians
     */
    public double getShoulderAngle() {
        return ((getShoulderPos() / 3.0) / ((double) NeverestEncoderTicks.NV60)) * (Math.PI / 3.0);
    }

    /**
     * Calculates angle of elbow from servo position
     * @return angle, in radians
     */
    public double getElbowAngle() {
        return ((getElbowPos()) / 3.0) * (Math.PI / 3.0);
    }

    /**
     * Move the shoulder to an angle
     * @param angle desired angle, in radians
     */
    public void setShoulderAngle(double angle) {
        setShoulderTarget((int) ((angle / (Math.PI / 3.0)) * ((double) NeverestEncoderTicks.NV60) * 3.0));
    }

    /**
     * Move the elbow to an angle
     * @param angle desired angle, in radians
     */
    public void setElbowAngle(double angle) {
        setElbowTarget((int) (((angle / (Math.PI / 3.0)) * 3.0)) * TetrixMotorTicks.ticks);
    }

    /**
     * Move the arm in the A-plane (arm-plane) to a specified coordinate
     * @param x x coord
     * @param y y coord
     */
    public void ikMove(double x, double y) {
        double c = Math.min(dist(x, y), lowerArm + upperArm);
        double shoulder = Math.asin(lowerArm * Math.sin(Math.acos((sqr(upperArm) + sqr(lowerArm) - sqr(c)) / (2 * upperArm * lowerArm))) / c);
        double elbow = Math.acos((sqr(upperArm) + sqr(lowerArm) - sqr(c)) / (2 * upperArm * lowerArm)) + Math.atan2(y, x);
        setShoulderAngle(shoulder);
        setElbowAngle(elbow);
        setShoulderSpeed(1.0);
    }

    /**
     * Move
     * @param x
     * @param y
     * @param z
     */
    public void ikMove(double x, double y, double z) {

    }

    public void setMode(ControlMode cm) {
        if (cm == ControlMode.IK) {
            shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private double dist(double x, double y) {
        return Math.sqrt(sqr(x) + sqr(y));
    }

    private double sqr(double base) {
        return base * base;
    }
}
