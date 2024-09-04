package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DriveTrain {
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor slideMotor;
    public void  initiate(HardwareMap hardwareMap){
        frontLeftMotor = hardwareMap.dcMotor.get("motor3");
        frontRightMotor = hardwareMap.dcMotor.get("motor2");
        backLeftMotor = hardwareMap.dcMotor.get("motor1");
        backRightMotor = hardwareMap.dcMotor.get("motor0");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        slideMotor = hardwareMap.dcMotor.get("Arm Motor");
        slideMotor.setTargetPosition(0);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void move(double x, double y, double rx){
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        frontLeftMotor.setPower((y + x + rx) / denominator);
        backLeftMotor.setPower((y - x + rx) / denominator);
        frontRightMotor.setPower((y - x - rx) / denominator);
        backRightMotor.setPower((y + x - rx) / denominator);
    }
    public void arm(double power,Telemetry telemetry){
        int max = 4474;
        double CPR = 537.7;

        // Get the current position of the motor
        int position = slideMotor.getCurrentPosition();
        double revolutions = position/CPR;

        double angle = revolutions * 360;
        double angleNormalized = angle % 360;

        // Show the position of the motor on telemetry
        telemetry.addData("Encoder Position", position);
        telemetry.addData("Encoder Revolutions", revolutions);
        telemetry.addData("Encoder Angle (Degrees)", angle);
        telemetry.addData("Encoder Angle - Normalized (Degrees)", angleNormalized);
        telemetry.addData("power", slideMotor.getPower());
        telemetry.update();

            if (power > 0 && position < max){
                slideMotor.setTargetPosition(max);
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(Math.abs(power));
            } else if (power < 0 && position > 0){
                slideMotor.setTargetPosition(0);
                slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                slideMotor.setPower(Math.abs(power));
            } else if (power == 0){
                slideMotor.setPower(power);
                slideMotor.setTargetPosition(position);
                slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            }
    }
}
