// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3.ColorSensorMeasurementRate;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3.ColorSensorResolution;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.util.Color;

public class ColorSense extends SubsystemBase {
  //colorsensors
  private ColorSensorV3 colorsrc = new ColorSensorV3(Constants.colorsensor_port);
  //private ColorSensorV3 colorsrc2 = new ColorSensorV3(Constants.colorsensor2_port);     //WILL NEED FIXING IF THERE ARE ACTUALLY TWO COLORSENSORS

  private ColorMatch colormatcher = new ColorMatch();
  
  private double[] ycal = Constants.yellow;
  private double[] gcal = Constants.green;
  private double[] ccal = Constants.cyan;
  private double[] rcal = Constants.red;

  /** Creates a new ColorSense. */
  public ColorSense() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void addMatchColor(double red, double green, double blue){
    colormatcher.addColorMatch(ColorMatch.makeColor(red, green, blue));
  }

  /**
   * Make sure to add colors to match to with the addMatchColor method before running this!
   * @return Returns an array of doubles with an index of 4 - the first 3 indexes are the rgb values (of the color that it found a match to, 
   * so compare these to the values you added to addMatchColor to find out what colr it is), the last is the confidence of the match. 
   */
  public double[] runcolormatch(){
    double ret[] = {0, 0, 0, 0};
    ColorMatchResult match = colormatcher.matchClosestColor(colorsrc.getColor());
    Color data = match.color;
    ret[0] = data.red;
    ret[1] = data.green;
    ret[2] = data.blue;
    ret[3] = match.confidence;
    return ret;
  }

  private double magnitude(double red, double green, double blue){
    return Math.sqrt(red*red + blue*blue + green*green);
  }

  public double red(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(mode == 0){ret = red;}
    else if(mode == 1){ret = red/cmag;}
    return ret;
  }

  public double green(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(mode == 0){ret = green;}
    else if(mode == 1){ret = green/cmag;}
    return ret;  
  }

  public double blue(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double ret = 0;
    if(mode == 0){ret = blue;}
    else if(mode ==1){ret = blue/cmag;}
    return ret;
  }

  /**
   * Get the raw colors from the colorsensor
   * @param - can be 0 or 1, mode 0 returns the raw output from the colorsensor, while mode 1 returns the output after dividing the colors by the magnitude
   * @return an array of doubles, the first index being the red value, the second the green value, and the third being the blue value;
   */
  public double[] rawcolors(int mode){
    double red = colorsrc.getRed();
    double green = colorsrc.getGreen();
    double blue = colorsrc.getBlue();
    double cmag = magnitude(red, green, blue);
    double[] ret = {0.0, 0.0, 0.0};
    if(mode == 0){
    ret[0] = red;
    ret[1] = green;
    ret[2] = blue;
    }else if(mode == 1){
    ret[0] = red/cmag;
    ret[1] = green/cmag;
    ret[2] = blue/cmag;
    }
    return ret;
  }

  //WILL NEED FIXING IF THERE ARE ACTUALLY TWO COLORSENSORS
  // public double[] rawcolors2(){
  //   double[] ret = {0.0, 0.0, 0.0};
  //   ret[0] = colorsrc2.getRed();
  //   ret[1] = colorsrc2.getGreen();
  //   ret[2] = colorsrc2.getBlue();
  //   return ret;
  // }

  //needs some work, and thought on what should be returned, where, and how this will integrate elsewhere
  //Unsure about if using the "double arrays" for the color calibration will work at this point, further testing needed
  public String colorCompare(){
    double red1 = red(1);
    double green1 = green(1);
    double blue1 = blue(1);
    if (red1<ycal[0] & red1>ycal[1] & green1<ycal[2] & green1>ycal[3] & blue1>ycal[4] & blue1<ycal[5]){
    return "Yellow";
    }else if (red1<gcal[0] & red1>gcal[1] & green1<gcal[2] & green1>gcal[3] & blue1<gcal[4] & blue1>gcal[5]){
    return "Green";
    }else if (red1<ccal[0] & red1>ccal[1] & green1<ccal[2] & green1>ccal[3] & blue1<ccal[4] & blue1>ccal[5]){
    return "Cyan";
    }else if (red1<rcal[0] & red1>rcal[1] & green1<rcal[2] & green1>rcal[3] & blue1<rcal[4] & blue1>rcal[5]){
    return "Red";
    }else{
    return "None";
    }
  }
}

