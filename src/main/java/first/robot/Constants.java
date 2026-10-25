// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package first.robot;

import org.wpilib.hardware.bus.CAN;
import org.wpilib.hardware.bus.CANPort;


import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.signals.InvertedValue;

/** 硬件配置及控制参数。标定开环自动时，在这里统一调整输出、时长和圆弧比例。 */
public final class Constants {
    public static final CANPort CAN_Port = CANPort.CAN_S0;
  private Constants() {}

  public static final class OperatorConstants {
    // 手柄在 Driver Station 中的端口号。
    public static final int kControllerPort = 0;

    private OperatorConstants() {}
  }

  public static final class DriveConstants {
    public static final int kLeftMasterCanId = 1;
    public static final int kLeftFollowerCanId = 2;
    public static final int kRightMasterCanId = 3;
    public static final int kRightFollowerCanId = 4;

    // 左右车轮中心间距：24.75 in = 0.62865 m。
    public static final double kTrackWidthMeters = 24.75 * 0.0254;

    private DriveConstants() {}
  }

  public static final class SuperstructureConstants {
    
    public static final int kFeederCanId = 5;
    public static final int kShooterCanId = 6;
    public static final  InvertedValue kFeederInverted = InvertedValue.CounterClockwise_Positive;
    public static final InvertedValue kShooterInverted = InvertedValue.Clockwise_Positive;

    public static final double kLaunchShooterVoltage = 0.0; // TODO: Tune
    public static final double kLaunchFeederVoltage = 0.0; // TODO: Tune
    // 保留原代码中的预留速度参数，当前射球仍使用上面的电压控制。
    public static final double kLaunchShooterVelocityRps = 0.0; // TODO: Tune
    public static final double kLaunchFeederVelocityRps = 0.0; // TODO: Tune
    public static final double kFeederVelocityRps = 0.0;
    public static final double kShooterTowerVelocityRps = 0.0;
    public static final double kIntakeShooterVelocityRps = 0.0; // TODO: Tune
    public static final double kIntakeFeederVelocityRps = 0.0; // TODO: Tune

    // 速度单位为 rotations per second；确认传感器方向后需实车调节前馈和 PID。
    public static final double kFeederKs = 0.0;
    public static final double kFeederKv = 0.0;
    public static final double kFeederKp = 0.0;
    public static final double kFeederKi = 0.0;
    public static final double kFeederKd = 0.0;
    public static final double kShooterKs = 0.0;
    public static final double kShooterKv = 0.0;
    public static final double kShooterKp = 0.0;
    public static final double kShooterKi = 0.0;
    public static final double kShooterKd = 0.0;

    private SuperstructureConstants() {}
  }

  public static final class AutoConstants {
    // 全程开环：输出是 [-1, 1] 的比例，不是 m/s，也不读取距离或航向反馈。
    public static final double kDriveOutput = 0.0;
    public static final double kTurnOutput = 0.0;
    public static final double kArcOuterOutput = 0.0;

    // 半径以底盘中心为参考。顺时针前进时左轮在外侧。
    public static final double kArcRadiusMeters = 3.0;
    // 理想轮速比约 0.8103；用作开环输出比的初值，仍需实车标定。
    public static final double kArcInnerToOuterRatio =
        (kArcRadiusMeters - DriveConstants.kTrackWidthMeters / 2.0)
            / (kArcRadiusMeters + DriveConstants.kTrackWidthMeters / 2.0);
    public static final double kArcInnerOutput = kArcOuterOutput * kArcInnerToOuterRatio;

    private AutoConstants() {}
  }
}