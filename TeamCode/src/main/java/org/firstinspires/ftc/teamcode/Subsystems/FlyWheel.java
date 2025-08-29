package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class FlyWheel {
    public enum WheelStates{
        ON,
        OFF
    }
    public enum ServoStates{
        FORWARD,
        BACKWARD
    }
    WheelStates flyWheelState = WheelStates.OFF;
    ServoStates servoState = ServoStates.BACKWARD;
    public static double forwardPos = 0.9;
    public static double backwardPos = .5;
    public static double pushTime = 200;
    double pushSnapshot = System.currentTimeMillis();
    public void pushServo(){
        servoState = ServoStates.FORWARD;
        pushSnapshot = System.currentTimeMillis();
    }
    public void flipWheel(){
        switch (flyWheelState){
            case ON:
                flyWheelState = WheelStates.OFF;
                break;
            case OFF:
                flyWheelState = WheelStates.ON;
                break;
        }
    }
    DcMotor flyWheel;
    Servo servo;
    public void initiate(HardwareMap hardwareMap){
        flyWheel = hardwareMap.dcMotor.get("flywheelr");
        servo = hardwareMap.servo.get("rightserv");
    }
    public void update(){
        if (System.currentTimeMillis() - pushSnapshot > pushTime){
            servoState = ServoStates.BACKWARD;
        }

        switch (servoState){
            case FORWARD:
                servo.setPosition(forwardPos);
                break;
            case BACKWARD:
                servo.setPosition(backwardPos);
                break;
        }
        switch (flyWheelState){
            case ON:
                flyWheel.setPower(1);
                break;
            case OFF:
                flyWheel.setPower(0);
        }
    }
    public void status(Telemetry telemetry){
        telemetry.addData("servoState",servoState);
    }
}
