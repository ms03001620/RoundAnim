package org.mazn.roundanim;

/**
 * Created by Administrator on 2015/8/5.
 */
public class Utils {

    public static double getPercent(double current ,double total){
        return current / total;
    }

    public static int getAngle(double current, double total){
        double percent = getPercent(current, total);
        return (int)(360 * percent);
    }

}
