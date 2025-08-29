package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.Subsystems.FlyWheel;
import org.firstinspires.ftc.teamcode.Subsystems.Shoulder;

@TeleOp
public class TeleOpExample extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain driveTrain = new DriveTrain();
        driveTrain.initiate(hardwareMap);
        FlyWheel flyWheel = new FlyWheel();
        flyWheel.initiate(hardwareMap);
        Shoulder shoulder = new Shoulder();
        shoulder.initiate(hardwareMap);
        waitForStart();
        if (isStopRequested()) return;
        Gamepad previousGamepad1 = new Gamepad();
        while (opModeIsActive()) {
            boolean LB = gamepad1.left_bumper && !previousGamepad1.left_bumper;
            boolean RB = gamepad1.right_bumper && !previousGamepad1.right_bumper;
            previousGamepad1.copy(gamepad1);

            if (LB){
                flyWheel.flipWheel();
            }
            if (RB){
                flyWheel.pushServo();
            }
            shoulder.move(gamepad1.right_trigger- gamepad1.left_trigger);

            shoulder.update();
            shoulder.status(telemetry);
            driveTrain.run(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
            flyWheel.update();
            flyWheel.status(telemetry);
            telemetry.update();
        }
    }
}
