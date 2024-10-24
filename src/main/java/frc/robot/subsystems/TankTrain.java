// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import frc.robot.Constants;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.proto.Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

public class TankTrain extends SubsystemBase {
  private DifferentialDrive m_myDrive;
  // device id may need to be changed 
  private Pigeon2 gryo = new Pigeon2(14);

  private final CANSparkMax m_frontLeftMotor = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax m_frontRightMotor = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax m_backLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax m_backRightMotor = new CANSparkMax(3, MotorType.kBrushless);
  private final XboxController m_Controller = new XboxController(0); 
  private double speed = 0;

  private Joystick joystick1 = new Joystick(0);

  public TankTrain() {
  m_frontLeftMotor.follow(m_backLeftMotor);
  m_frontRightMotor.follow(m_backRightMotor);
  m_backLeftMotor.setInverted(true);
  m_backRightMotor.setInverted(true);

  m_myDrive = new DifferentialDrive(m_backLeftMotor, m_backRightMotor);
  }

  @Override
  public void periodic() {
    //m_myDrive.arcadeDrive(speed, 0);
    double speed = -joystick1.getRawAxis(1) * 0.6;
    double turn = joystick1.getRawAxis(4) * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    m_frontLeftMotor.set(0.5);
    m_frontRightMotor.set(-0.5);
    m_backLeftMotor.set(0.5);
    m_backRightMotor.set(-0.5);
  }

  
  public RelativeEncoder[] getEncoders() {
  RelativeEncoder[] encoders = {m_frontLeftMotor.getEncoder(), m_frontRightMotor.getEncoder()};
  return encoders;
  }
   
  public double xGryoValue() {
  return gryo.getAccumGyroX().getValueAsDouble();

  }
}
