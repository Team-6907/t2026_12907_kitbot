package first.robot.subsystems;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.hardware.bus.CANPort;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import first.robot.Constants.SuperstructureConstants;

public class SuperStructure implements Mechanism{
    private final TalonFX m_feeder = new TalonFX(SuperstructureConstants.kFeederCanId ,new CANBus(CANPort.CAN_S0));
  private final TalonFX m_shooter = new TalonFX(SuperstructureConstants.kShooterCanId,new CANBus(CANPort.CAN_S0));

  private final VelocityVoltage m_feederVelocityRequest = new VelocityVoltage(0.0);
  private final VelocityVoltage m_shooterVelocityRequest = new VelocityVoltage(0.0);
  private final VoltageOut m_feederLaunchRequest = new VoltageOut(SuperstructureConstants.kLaunchFeederVoltage);
  private final VoltageOut m_shooterLaunchRequest = new VoltageOut(SuperstructureConstants.kLaunchShooterVoltage);
  private final NeutralOut m_stopRequest = new NeutralOut();

  public SuperStructure() {
   TalonFXConfiguration shooterConfig = new TalonFXConfiguration();
    TalonFXConfiguration feederConfig = new TalonFXConfiguration();

    shooterConfig.Slot0.kS = SuperstructureConstants.kShooterKs;
    shooterConfig.Slot0.kV = SuperstructureConstants.kShooterKv;
    shooterConfig.Slot0.kP = SuperstructureConstants.kShooterKp;
    shooterConfig.Slot0.kI = SuperstructureConstants.kShooterKi;
    shooterConfig.Slot0.kD = SuperstructureConstants.kShooterKd;

    feederConfig.Slot0.kS = SuperstructureConstants.kFeederKs;
    feederConfig.Slot0.kV = SuperstructureConstants.kFeederKv;
    feederConfig.Slot0.kP = SuperstructureConstants.kFeederKp;
    feederConfig.Slot0.kI = SuperstructureConstants.kFeederKi;
    feederConfig.Slot0.kD = SuperstructureConstants.kFeederKd;

    shooterConfig.MotorOutput.Inverted = SuperstructureConstants.kShooterInverted;
    feederConfig.MotorOutput.Inverted = SuperstructureConstants.kFeederInverted;

    shooterConfig.MotorOutput.NeutralMode = NeutralModeValue.Coast;
    feederConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

    m_shooter.getConfigurator().apply(shooterConfig);
    m_feeder.getConfigurator().apply(feederConfig);
  }

  public void shoot() {
    // TODO: 实现 shoot 方法
    m_shooter.setControl(m_shooterLaunchRequest);
    m_feeder.setControl(m_feederLaunchRequest);
  }

  public void stop() {
    // TODO: 实现 stop 方法
    m_shooter.setControl(m_stopRequest);
    m_feeder.setControl(m_stopRequest);
  }

  public void intake() {
    // TODO: 实现 intake 方法
    m_shooter.setControl(m_shooterVelocityRequest.withVelocity(SuperstructureConstants.kIntakeShooterVelocityRps));
    m_feeder.setControl(m_feederVelocityRequest.withVelocity(SuperstructureConstants.kIntakeFeederVelocityRps));
  }

  public Command shootCommand() {
    return this.runRepeatedly(()->shoot()).named("SuperStructure:Shoot");
  }

    public Command stopCommand() {
        return this.runRepeatedly(()->stop()).named("SuperStructure:Stop");
    }

    public Command intakeCommand() {
        return this.runRepeatedly(()->intake()).named("SuperStructure:Intake");
    }

}
