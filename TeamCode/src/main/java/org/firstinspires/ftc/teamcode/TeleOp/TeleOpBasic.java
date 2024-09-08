package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystem.Arm;
import org.firstinspires.ftc.teamcode.Subsystem.DriveTrain;

@TeleOp
public class TeleOpBasic extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        if (isStopRequested()) return;
        DriveTrain driveTrain = new DriveTrain();
        Arm arm = new Arm();
        driveTrain.initiate(hardwareMap);
        arm.initiate(hardwareMap);
        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick is reversed!
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;
            double slidepower = gamepad1.right_trigger - (gamepad1.left_trigger);
            boolean resetslide = gamepad1.options;
            driveTrain.move(x,y,rx);
            arm.slides(slidepower,telemetry);
        }
    }
}
