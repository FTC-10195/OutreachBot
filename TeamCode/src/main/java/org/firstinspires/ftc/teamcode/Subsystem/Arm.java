package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm{
    DcMotor slideMotor;
    public void initiate(HardwareMap hardwareMap){
        slideMotor = hardwareMap.dcMotor.get("Arm Motor");
        slideMotor.setTargetPosition(0);
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void slides(double power, Telemetry telemetry){
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
