// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.analog.adis16470.frc.ADIS16470_IMU;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

public class IMU_Gyro extends SubsystemBase {
  private ADIS16470_IMU imu = new ADIS16470_IMU();
  private BuiltInAccelerometer rioAcc = new BuiltInAccelerometer();
  //private AnalogGyro imu_gyro = new AnalogGyro(Constants.gyro_port);      //<<< not sure if necessary
  private double angle, rate;
  

  /** Creates a new IMU_Gyro. */
  public IMU_Gyro() {
    imu.setYawAxis(Constants.imu_yaw);
    imu.configCalTime(Constants.imu_caltime);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    rate = currentRate();
    angle = currentAngle();
  }

  /**
   * @return rotation in DEGREES per SECOND
   */
  public double currentRate(){ 
    return imu.getRate();
  }

  /**
   * @return the CURRENT ANGLE (total since last calibration) of the YAW axis (this can be changed in Constants)
   */
  public double currentAngle(){
    //note: a positive angle is in the clockwise direction
    return imu.getAngle();
  }

  public double imuX(){
    return imu.getAccelInstantX();
  }

  public double imuY(){
    return imu.getAccelInstantY();
  }

  public double imuZ(){
    return imu.getAccelInstantZ();
  }

  public double imuX_si(){
    return imu.getAccelInstantX() * 9.81;
  }

  public double imuY_si(){
    return imu.getAccelInstantY() * 9.81;
  }

  public double imuZ_si(){
    return imu.getAccelInstantZ() * 9.81;
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioX(){
    return rioAcc.getX();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioY(){
    return rioAcc.getY();
  }

  /**
   * @return returns in G-FORCES, so make sure to convert to something useful
   */
  public double rioZ(){
    return rioAcc.getZ();
  }

  public double rioX_si(){
    return rioAcc.getX() * 9.81;
  }

  public double rioY_si(){
    return rioAcc.getY() * 9.81;
  }

  public double rioZ_si(){
    return rioAcc.getZ() * 9.81;
  }

  public double avgX(){
    return (rioAcc.getX() + imu.getAccelInstantX()) /2;
  }

  public double avgY(){
    return (rioAcc.getY() + imu.getAccelInstantY()) /2;
  }

  public double avgZ(){
    return (rioAcc.getZ() + imu.getAccelInstantZ()) /2;
  }

  /**
   * Make sure the robot doesn't move while recalibrating
   */
  public void recalibrate(){
    imu.calibrate();
  }

  /**
   * Call this periodically if it is intended to update with each scheduler run
   */
  public void DashboardAngle(){
    SmartDashboard.putNumber("Current Angle:", currentAngle());
  }

  /**
   * Call this periodically if it is intended to update with each scheduler run
   */
  public void DashboardRate(){
    SmartDashboard.putNumber("Current Rate:", currentRate());
  }




  // public double[] accelDist_v1(double period_time){
  //   double[] pos = {0.0, 0.0, 0.0};
  //   double accelX[] = {0,0,0,0,0,0,0,0,0,0};
  //   double accelY[] = {0,0,0,0,0,0,0,0,0,0};
  //   double accelZ[] = {0,0,0,0,0,0,0,0,0,0};
  //   int i = 0;
  //   double distanceX = 0;
  //   double distanceY = 0;
  //   double distanceZ = 0;
  //   double sum;
  //   double dt = period_time;
  //   dt = 0.02;

  //   //run a loop of ten
  //   while(i++ <=9){
  //     //fill up array with values from imu (there are ten spots and the loop runs 10 times)
  //     accelX[i] = imu.getAccelInstantX() * 9.81;
  //     accelY[i] = imu.getAccelInstantY() * 9.81;
  //     accelZ[i] = imu.getAccelInstantZ() * 9.81;
  //   }

  //   //X-axis -> average all values and store in distanceX 
  //   sum = 0;
  //   for(int j=0; j<10; j++){
  //     sum += accelX[j];
  //   }
  //   sum /= 10;
  //   distanceX = sum * dt;

  //   //Y-axis -> average all values and store in distanceY
  //   sum = 0;
  //   for(int j=0; j<10; j++){
  //     sum += accelY[j];
  //   }
  //   sum /= 10;
  //   distanceY = sum * dt;

  //   //Z-axis -> average all values and store in disanceZ
  //   sum = 0;
  //   for(int j=0; j<10; j++){
  //     sum += accelZ[j];
  //   }
  //   sum /= 10;
  //   distanceZ = sum * dt;

  //   //set places in the array that will be returned equal to the values gotten above
  //   pos[0] = distanceX;
  //   pos[1] = distanceY;
  //   pos[2] = distanceZ;  

  //   //return positions
  //   return pos;
  // }

}
