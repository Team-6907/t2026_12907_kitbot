# t2026_12907_kitbot

> FRC Team 6907 The G.O.A.T. · 2026 暑期 Workshop 的 KitBot 教学机器人代码（6907 新队员培训用；“12907” 是队内对新生的戏称）。

Robot code for the kitbot used in the team's 2026 summer workshop, where
Team 6907's rookies (nicknamed "12907" inside the team) learn FRC programming. It is a teaching project: small,
heavily commented (in Chinese), and intentionally simple.

## Robot

- **Drivetrain** - differential (tank) drive. Each side has a CTRE `WPI_TalonSRX`
  master and a `WPI_VictorSPX` follower (Phoenix 5), driven through WPILib
  `DifferentialDrive`.
- **Superstructure** - a feeder and a shooter, one `TalonFX` each (Phoenix 6).
  Intake uses closed-loop velocity targets; shooting uses open-loop voltage.
- **Controls** - one Xbox controller on port 0: left stick Y / right stick X
  arcade drive, `X` intake, `A` shoot (`X` takes priority).
- **Autonomous** - `Autos.teachingAuto`, a fully open-loop, time-based sequence
  (drive, turn, arc, then shoot). Durations are uncalibrated starting values.

CAN IDs and all tunables are in `Constants.java`; several are still marked `TODO`.

## Code structure

WPILib command-based Java (`Robot` extends `TimedRobot` and only runs the
`CommandScheduler`; everything else hangs off `RobotContainer`).

```
src/main/java/frc/robot/
  Main.java, Robot.java, RobotContainer.java, Constants.java
  commands/    Autos.java, TeleopDriveCommand.java
  subsystems/  DriveSubsystem.java, SuperstructureSubsystem.java
```

- GradleRIO `2026.2.1`, Java 17
- Vendordeps: Phoenix 5, Phoenix 6, WPILib New Commands

## Build and deploy

Install the WPILib 2026 tools, then from the repository root:

```bash
./gradlew build     # compile and run checks
./gradlew deploy    # deploy to the roboRIO (robot network required)
```

The team number comes from `.wpilib/wpilib_preferences.json` (currently 6907);
change it there if the roboRIO is imaged for a different team. Desktop
simulation support is disabled in `build.gradle` (`includeDesktopSupport = false`).

## Branches

- `main` - current workshop code.
- `systemcore-testing-commandv3` - an experimental, unrelated-history snapshot
  of the kitbot ported to WPILib 2027 alpha (GradleRIO `2027.0.0-alpha-7`),
  Systemcore and Commands v3. Not merged into `main`.

## License

Team code is released under the MIT License, see [LICENSE](LICENSE). The
project template and WPILib itself are covered by
[WPILib-License.md](WPILib-License.md).
