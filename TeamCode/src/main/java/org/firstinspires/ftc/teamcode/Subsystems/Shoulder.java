package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class Shoulder {
    DcMotor shoulder1;
    DcMotor shoulder2;
    public static double kP = .04;
    public static double sensitivity = .8;
    public static double min = -60;
    public static double max = 0;
    double targetPos = 0;
    public void initiate(HardwareMap hardwareMap){
        shoulder1 = hardwareMap.dcMotor.get("heightr");
        shoulder2 = hardwareMap.dcMotor.get("heightl");
        shoulder1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulder2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void move(double move){
        targetPos += move * sensitivity;
        if (targetPos > max){
            targetPos = max;
        }
        if (targetPos < min){
            targetPos = min;
        }
    }
    public void update(){
        shoulder1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shoulder2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double error = targetPos - shoulder1.getCurrentPosition();
        double power = error * kP;
        shoulder1.setPower(power);
        shoulder2.setPower(power);
    }
    public void status(Telemetry telemetry){
        telemetry.addData("shoulderPos",shoulder1.getCurrentPosition());
        telemetry.addData("shoulderPos2",shoulder2.getCurrentPosition());
        telemetry.addData("shoulder1Power",shoulder1.getPower());
        telemetry.addData("shoulder2Power",shoulder2.getPower());
        telemetry.addData("TargetPos",targetPos);
    }
}
