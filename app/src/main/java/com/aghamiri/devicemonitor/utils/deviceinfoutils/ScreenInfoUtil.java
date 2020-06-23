package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;


import com.aghamiri.devicemonitor.R;

import java.text.DecimalFormat;



public class ScreenInfoUtil {
    // screen brightness
    public static String getScreenBrightness (Activity activity) {
        int brightness = Settings.System.getInt(activity.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS,-1);
        int brightnessPercent = brightness * 100 / 255;
        return (brightnessPercent + "%");
    }
    // screen density
    public static String getScreenDensity (DisplayMetrics displayMetrics) {
        return displayMetrics.densityDpi + " dpi";
    }
    // screen orientation
    public static String getScreenOrientation (Activity activity) {
        switch (activity.getResources().getConfiguration().orientation) {
            case 0:
                return activity.getString(R.string.screen_orientation_zero);
            case 1:
                return activity.getString(R.string.screen_orientation_one);
            case 2:
                return activity.getString(R.string.screen_orientation_two);
        }
        return activity.getString(R.string.unknow);
    }
    // screen refresh rate
    public static String getScreenRefreshRate (Activity activity) {
        Display display = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        float refreshRating = display.getRefreshRate();
        return (refreshRating + "Hz");
    }
    // screen resolution
    public static String getScreenResolution (DisplayMetrics displayMetrics) {
        return (displayMetrics.widthPixels + " x " + displayMetrics.heightPixels) + " pixel";
    }
    // screen size
    public static String getScreenSize (DisplayMetrics displayMetrics) {
        double width = displayMetrics.widthPixels;
        double height = displayMetrics.heightPixels;
        double densx = displayMetrics.xdpi;
        double densy = displayMetrics.ydpi;
        double screenInches = Math.sqrt(Math.pow(width / densx, 2) + Math.pow(height / densy,2));
        return new DecimalFormat("#.##").format(screenInches) + " inches";
    }
    // screen type
    public static String getScreenType (Activity activity) {
        String screenSizeType = "";
        if ((activity.getResources().getConfiguration().screenLayout & 15) == 4) {
            return (activity.getString(R.string.screen_size_type_xlarge));
        } else if ((activity.getResources().getConfiguration().screenLayout & 15) == 3) {
            return (activity.getString(R.string.screen_size_type_large));
        } else if ((activity.getResources().getConfiguration().screenLayout & 15) == 2) {
            return (activity.getString(R.string.screen_size_type_normal));
        } else if ((activity.getResources().getConfiguration().screenLayout & 15) == 1) {
            return (activity.getString(R.string.screen_size_type_small));
        } else {
            return screenSizeType;
        }
    }
    // screen type density
    public static String getScreenTypeDensity (DisplayMetrics displayMetrics) {
        float density = displayMetrics.density;
        if (((double) density) >= 4.0d) {
            return "xxxhdpi";
        }
        if (((double) density) >= 3.0d) {
            return "xxhdpi";
        }
        if (((double) density) >= 2.0d) {
            return "xhdpi";
        }
        if (((double) density) >= 1.5d) {
            return "hdpi";
        }
        if (((double) density) >= 1.0d) {
            return "mdpi";
        }
        return "ldpi";
    }
}
