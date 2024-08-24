package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Subsystem.DriveTrain;
@TeleOp
public class TeleOpBasic extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DriveTrain driveTrain = new DriveTrain();
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {

        }
    }
}
