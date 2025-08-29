package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Catapult {
    public enum States{
        RESTING,
        FIRING
    }
    DcMotor catapult;
    States currentState = States.RESTING;
    public static double kP = .04;
    public static int restPos = 0;
    public static int shootPos = 30;
    public static double shootTime = 300;
    double timeSnapshot = System.currentTimeMillis();
    public States getState(){
        return currentState;
    }

    public void initiate(HardwareMap hardwareMap){
        catapult = hardwareMap.dcMotor.get("cat");
        catapult.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void shoot(){
        if (currentState != States.FIRING){
            currentState = States.FIRING;
            timeSnapshot = System.currentTimeMillis();
        }
    }
    public void update(){
        if (System.currentTimeMillis() - timeSnapshot > shootTime){
            currentState = States.RESTING;
        }
        switch (currentState){
            case RESTING:
                catapult.setTargetPosition(restPos);
                break;
            case FIRING:
                catapult.setTargetPosition(shootPos);
                break;
        }
        catapult.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        double error = catapult.getTargetPosition() - catapult.getCurrentPosition();
        double power = error * kP;
        catapult.setPower(power);
    }
    public void status(Telemetry telemetry){
        telemetry.addData("catpaultPosition",catapult.getCurrentPosition());
        telemetry.addData("catpaultPower",catapult.getPower());
        telemetry.addData("TargetPos",catapult.getTargetPosition());
    }
}
