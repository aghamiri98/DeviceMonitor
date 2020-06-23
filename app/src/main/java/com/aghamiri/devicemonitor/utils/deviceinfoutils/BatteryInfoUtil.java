package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;


import com.aghamiri.devicemonitor.R;

import java.text.DecimalFormat;



public class BatteryInfoUtil {
    // battery level
    public static String getBatteryLevel (Intent batteryIntent) {
        if (batteryIntent != null) {
            return batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) + "%";
        }
        return 0 + " %";
    }
    // battery capacity
    public static String getBatteryCapacity (Activity activity) {
        double batteryCapacity = 0;
        try {
            Object object = Class.forName("com.android.internal.os.PowerProfile").getConstructor(Context.class).newInstance(activity);
            batteryCapacity = (Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getAveragePower", String.class).invoke(object, "battery.capacity");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new DecimalFormat("#.##").format(batteryCapacity) + " mAh";
    }
    // battery health
    public static String getBatteryHealth (Intent batteryIntent, Activity activity) {
        if (batteryIntent != null) {
            int health = batteryIntent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            return getBatteryHealthCharging(activity, health);
        }
        return activity.getString(R.string.unknow);
    }
    private static String getBatteryHealthCharging (Activity activity, int health) {
        switch (health) {
            case 1:
                return activity.getString(R.string.battery_health_one);
            case 2:
                return activity.getString(R.string.battery_health_two);
            case 3:
                return activity.getString(R.string.battery_health_three);
            case 4:
                return activity.getString(R.string.battery_health_four);
            case 5:
                return activity.getString(R.string.battery_health_five);
            case 6:
                return activity.getString(R.string.battery_health_six);
            case 7:
                return activity.getString(R.string.battery_health_seven);
        }
        return activity.getString(R.string.unknow);
    }
    // battery power source
    public static String getBatteryPowerSource (Intent batteryIntent, Activity activity) {
        if (batteryIntent != null) {
            int powerSource = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
            return getBatteryPowerSourceCharging(activity, powerSource);
        }
        return activity.getString(R.string.none);
    }
    private static String getBatteryPowerSourceCharging (Activity activity, int powerSource) {
        switch (powerSource) {
            case 1:
                return activity.getString(R.string.battery_power_source_one);
            case 2:
                return activity.getString(R.string.battery_power_source_two);
            case 4:
                return activity.getString(R.string.battery_power_source_four);
        }
        return activity.getString(R.string.unknow);
    }
    // battery status
    public static String getBatteryStatus (Intent batteryIntent, Activity activity) {
        if (batteryIntent != null) {
            int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
            return getBatteryCharging(activity, status);
        }
        return activity.getString(R.string.unknow);
    }
    private static String getBatteryCharging (Activity activity, int status) {
        switch (status) {
            case 1:
                return activity.getString(R.string.battery_status_one);
            case 2:
                return activity.getString(R.string.battery_status_two);
            case 3:
                return activity.getString(R.string.battery_status_three);
            case 4:
                return activity.getString(R.string.battery_status_four);
            case 5:
                return activity.getString(R.string.battery_status_five);
        }
        return activity.getString(R.string.unknow);
    }
    // battery technology
    public static String getBatteryTechnology (Intent batteryIntent) {
        if (batteryIntent != null) {
            return batteryIntent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
        }
        return "";
    }
    // battery temp
    public static String getBatteryTemp (Intent batteryIntent) {
        if (batteryIntent != null) {
            int tempC = batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1) / 10;
            int tempF = tempC * 9 / 5 + 32;
            return (tempC + "°C" + "/" + tempF + "°F");
        }
        return "";
    }
    // battery voltage
    public static String getBatteryVoltage (Intent batteryIntent) {
        if (batteryIntent != null) {
            return (batteryIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1) + " mV");
        }
        return "";
    }
}
