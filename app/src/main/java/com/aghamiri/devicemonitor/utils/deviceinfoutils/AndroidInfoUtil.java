package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class AndroidInfoUtil {
    // android version name
    public static String getAndroidVersionName () {
        String sAndroidOSName = null;
        StringBuilder builder = new StringBuilder();
        builder.append("android : ").append(Build.VERSION.RELEASE);
        Field[] fields = Build.VERSION_CODES.class.getFields();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            int fieldValue = -1;
            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException | IllegalAccessException | NullPointerException e) {
                e.printStackTrace();
            }
            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(" : ").append(fieldName).append(" : ");
                builder.append("sdk=").append(fieldValue);
                sAndroidOSName = fieldName;
            }
        }
        return sAndroidOSName;
    }
    // android version code
    public static String getAndroidVersionCode () {
        return Build.VERSION.RELEASE;
    }
    // android bootleader
    public static String getAndroidBootLeader () {
        return Build.BOOTLOADER;
    }
    // android build fingerprint
    public static String getAndroidFingerprint () {
        return Build.FINGERPRINT;
    }
    // android build id
    public static String getAndroidBuildID () {
        return Build.ID;
    }
    // android build time
    public static String getAndroidBuildTinme () {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
        return simpleDateFormat.format(new Date(Build.TIME));
    }
    // android code name
    public static String getAndroidCodeName () {
        return Build.VERSION.CODENAME;
    }
    // android incremental
    public static String getAndroidIncremental () {
        return Build.VERSION.INCREMENTAL;
    }
    // android opengl es
    public static String getAndroidOpenGLES (Activity activity) {
        int i;
        PackageManager packageManager;
        ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
        if (activityManager != null) {
            ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
            if (deviceConfigurationInfo.reqGlEsVersion != 0 && deviceConfigurationInfo.reqGlEsVersion > 65536) {
                i = deviceConfigurationInfo.reqGlEsVersion;
                packageManager = activity.getPackageManager();
                if (packageManager != null) {
                    FeatureInfo[] systemAvailableFeatures = packageManager.getSystemAvailableFeatures();
                    if (systemAvailableFeatures != null && systemAvailableFeatures.length > 0) {
                        for (FeatureInfo featureInfo : systemAvailableFeatures) {
                            if (featureInfo != null && featureInfo.name == null && featureInfo.reqGlEsVersion != 0 && featureInfo.reqGlEsVersion > 0) {
                                i = featureInfo.reqGlEsVersion;
                            }
                        }
                    }
                }
                return (i >> 16) + "." + (i & 65535);
            }
        }
        return null;
    }
    // android opengl version
    public static String getAndroidOpenGLVersion () {
        String str = "ro.opengles.version";
        String str2 = "unknown";
        try {
            str2 = ((String) list("getprop " + str).get(0)).trim();
        } catch (SecurityException ignored) {
        }
        return str2 == "[]" ? "unknown" : str2;
    }
    // android platform version
    public static String getAndroidPlatformVersion () {
        return "3.0.15-I9100";
    }
    // android ril version
    public static String getAndroidRILVersion () {
        String str = "gsm.version.ril-impl";
        String str2 = "unknown";
        try {
            str2 = ((String) list("getprop " + str).get(0)).trim();
        } catch (SecurityException ignored) {
        }
        return str2 == "[]" ? "unknown" : str2;
    }
    private static List list (String str) {
        List arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return arrayList;
                }
                arrayList.add(readLine);
            }
        } catch (IOException | SecurityException ignored) {
        }
        return arrayList;
    }
    // android sdk version
    public static String getAndroidSDKVersion () {
        return String.valueOf(Build.VERSION.SDK_INT);
    }
    // android system encoding
    public static String getAndroidSystemEncoding () {
        return System.getProperty("file.encoding");
    }
    // android system language
    public static String getAndroidSystemLanguage () {
        return Locale.getDefault().getDisplayLanguage();
    }
    // android system os version
    public static String getAndroidSystemOSVersion () {
        return System.getProperty("os.name");
    }
    // android system region
    public static String getAndroidSystemRegion () {
        return System.getProperty("user.region");
    }
}

