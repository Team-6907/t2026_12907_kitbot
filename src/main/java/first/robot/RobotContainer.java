package first.robot;

import org.wpilib.command3.button.CommandXboxController;
import org.wpilib.driverstation.XboxController;

import first.robot.subsystems.SuperStructure;

public class RobotContainer {
    private final SuperStructure m_superstructureSubsystem = new SuperStructure();
    private final CommandXboxController controller = new CommandXboxController(0);

    public RobotContainer() {
    setDefaultCommands();
    configureBindings();
  }

  private void configureBindings() {
    // TODO: 在这里添加手柄按钮绑定
    controller.x().whileTrue(m_superstructureSubsystem.shootCommand());
    controller.a().whileTrue(m_superstructureSubsystem.intakeCommand());
  }

  private void setDefaultCommands() {
    // TODO: 在这里设置默认命令
   m_superstructureSubsystem.setDefaultCommand(m_superstructureSubsystem.stopCommand());
  }

}
