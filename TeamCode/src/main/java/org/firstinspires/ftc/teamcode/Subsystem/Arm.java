package org.firstinspires.ftc.teamcode.Subsystem;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Arm {
    DcMotor slideMotor ;
    Servo clawServo;
    public void initiate(HardwareMap hardwareMap){
        slideMotor = hardwareMap.dcMotor.get("Arm Motor");
        clawServo = hardwareMap.servo.get("Claw");
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
    public void adminSlides(boolean reset, boolean overide, Telemetry telemetry){
        int position = slideMotor.getCurrentPosition();
        if (reset){
            slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        if (overide){
            slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        slideMotor.setPower(slideMotor.getPower());
        telemetry.addData("position", position);
    }
    public void slides(double power){
        int position = slideMotor.getCurrentPosition();
        int maxPosition = 3700;
        if (power > 0){
            slideMotor.setTargetPosition(maxPosition);
        } else if (power < 0){
            power = power/2;
            slideMotor.setTargetPosition(0);
        } else {
            slideMotor.setTargetPosition(position);
        }
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideMotor.setPower(power);
    }
    boolean leftButtonPressed = false;
    boolean open = true;
    public void claw(boolean leftButton){
        double position  = clawServo.getPosition();
        if (!leftButtonPressed && leftButton) {
            leftButtonPressed = true;
            if (position == 1) {
                open = true;
            } else if (position == 0) {
                open = false;
            }
        } else if (!leftButton){
            leftButtonPressed = false;
        }
        if (open){
            clawServo.setPosition(0);
        }else {
            clawServo.setPosition(1);
        }
    }
}
