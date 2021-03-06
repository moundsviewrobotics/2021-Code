package frc.robot;

public final class Dynamics{
    //controller layout map -> this this makes it more simple to just access a controller button, and let the actual layout used be determined elsewhere
    public static int controllerButton_A;
    public static int controllerButton_B;
    public static int controllerButton_X;
    public static int controllerButton_Y;
    public static int controllerButton_menu;
    public static int controllerButton_home; 
    public static int controllerButton_lb;
    public static int controllerButton_rb;
    public static int controllerButton_ls;
    public static int controllerButton_rs;
    public static int controllerStick_ly;
    public static int controllerStick_lx;
    public static int controllerStick_ry;
    public static int controllerStick_rx;
    public static int controllerTrigger_l;
    public static int controllerTrigger_r;

    public static String controllerlayout = Constants.controlmode;

    //Software config/calibration -> dynamic in the future?
        //drivebase motor/controlling options
            //drivebase drivecontrol mode -> Options include the following: tank, race, trigger, arcade
            public static int dmode_index = 2;
            public static String drivemode = Constants.drivemodes[dmode_index];
            //sets automatic squaring inside the tankdrive and arcadedrive functions (turned off if it is aready done in controller input function)
            public static boolean default_squareinp = false;
            //invert boolean for each side
            public static boolean db_left_invt = false;
            public static boolean db_right_invt = false;
            //multipliers for each stick axis
            public static double c1_left_Y_mult = -0.5;
            public static double c1_right_Y_mult = -0.5;
            public static double c1_left_X_mult = -0.5;
            public static double c1_right_X_mult = -0.5;
            //deadzone on controller sticks -> only set for drivebase control atm
            public static double deadzone = 0.4;
            //takes the controller stick output value to the power of this number (along with multiplying it by the above values)
            public static int power = 2;

    public Dynamics(){
        setmode(Constants.controlmode);
    } 

    // public void setdrivemode(int direction){
        
    // }

    public void setmode(String mode){
        if(mode.equals("xbox")){
            controllerButton_A = Constants.x_controllerButton_A;
            controllerButton_B = Constants.x_controllerButton_B;
            controllerButton_X = Constants.x_controllerButton_X;
            controllerButton_Y = Constants.x_controllerButton_Y;
            controllerButton_menu = Constants.x_controllerButton_menu;
            controllerButton_home = Constants.x_controllerButton_home;
            controllerButton_lb = Constants.x_controllerButton_lb;
            controllerButton_rb = Constants.x_controllerButton_rb;
            controllerButton_ls = Constants.x_controllerButton_ls;
            controllerButton_rs = Constants.x_controllerButton_rs;
            controllerStick_ly = Constants.x_controllerStick_ly;
            controllerStick_lx = Constants.x_controllerStick_lx;
            controllerStick_ry = Constants.x_controllerStick_ry;
            controllerStick_rx = Constants.x_controllerStick_rx;
            controllerTrigger_l = Constants.x_controllerTrigger_l;
            controllerTrigger_r = Constants.x_controllerTrigger_r;
          }else if(mode.equals("logitech")){
            controllerButton_A = Constants.l_controllerButton_A;
            controllerButton_B = Constants.l_controllerButton_B;
            controllerButton_X = Constants.l_controllerButton_X;
            controllerButton_Y = Constants.l_controllerButton_Y;
            controllerButton_menu = Constants.l_controllerButton_menu;
            controllerButton_home = Constants.l_controllerButton_home;
            controllerStick_ly = Constants.l_controllerStick_ly;
            controllerStick_lx = Constants.l_controllerStick_lx;
            controllerStick_ry = Constants.l_controllerStick_ry;
            controllerStick_rx = Constants.l_controllerStick_rx;
            controllerTrigger_l = Constants.l_controllerTrigger_l;
            controllerTrigger_r = Constants.l_controllerTrigger_r;
          }
    }
}