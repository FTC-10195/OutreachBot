package org.firstinspires.ftc.teamcode.Subsystem;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
        DcMotor slideMotor;
        public void initiate(HardwareMap hardwareMap){
            slideMotor = hardwareMap.dcMotor.get("Arm Motor");
            slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
            slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        public void slides(double power){
            int position = slideMotor.getCurrentPosition();
            int maxPosition = 3710;
            if (power > 0) {
                slideMotor.setTargetPosition(maxPosition);
            } else if (power < 0) {
                slideMotor.setTargetPosition(0);
            } else {
                slideMotor.setTargetPosition(position);
            }
            slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            slideMotor.setPower(power);
        }
    }

