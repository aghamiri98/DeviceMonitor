package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;


import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.RootUtil;


import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DeviceInfoUtil {
    // device android id
    public static String getDeviceAndroidID (Activity activity) {
        return "" + Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    // device board
    public static String getDeviceBoard () { // The name of the underlying board, like "goldfish" - unknown?
        return Build.BOARD;
    }
    // device brand
    public static String getDeviceBrand () { // thương hiệu
        return Build.BRAND;
    }
    // device build time
    public static String getDeviceBuildTime () { // time update os version
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
        return simpleDateFormat.format(new Date(Build.TIME));
    }
    // device cpu abi
    public static String getDeviceCPUABI () { // giả lập chip xử lý cho thiết bị ảo
        return Build.CPU_ABI;
    }
    // device cpu abi 2
    public static String getDeviceCPUABI2 () { // giả lập chip xử lý cho thiết bị ảo
        return Build.CPU_ABI2;
    }
    // device device
    public static String getDeviceDevice () { // unknown?
        return Build.DEVICE;
    }
    // device serial
    public static String getDeviceDeviceSerial () {
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception ignored) {
        }
        return serial;
    }
    // device display
    public static String getDeviceDisplay () { // unknown?
        return Build.DISPLAY;
    }
    // device hardware
    public static String getDeviceHardware () {
        return Build.HARDWARE;
    }
    // device host
    public static String getDeviceHost () { // unknown?
        return Build.HOST;
    }
    // device icc card
    public static String getDeviceICCCard (Activity activity, TelephonyManager telephonyManager) { // the mach tich hop
        if (telephonyManager.hasIccCard()) {
            return activity.getString(R.string.device_icc_card_exist);
        } else {
            return activity.getString(R.string.device_icc_card_not_exist);
        }
    }
    // device imei
    public static String getDeviceIMEI (TelephonyManager telephonyManager) {
        return telephonyManager.getDeviceId();
    }
    // device kernel
    public static String getDeviceKernel () {
        return System.getProperty("os.version");
    }
    // device manufacturer
    public static String getDeviceManufacturer () {
        return Build.MANUFACTURER;
    }
    // device model
    public static String getDeviceModel () {
        return Build.MODEL;
    }
    // device product
    public static String getDeviceProduct () { // unknown?
        return Build.PRODUCT;
    }
    // device phonenumber
    public static String getDevicePhonenumber (Activity activity) {
        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService("phone");
        if (telephonyManager.getLine1Number() != null) {
            return telephonyManager.getLine1Number();
        }
        return activity.getString(R.string.unknow);
    }
    // device release
    public static String getDeviceRelease () { // unknown?
        return Build.VERSION.RELEASE;
    }
    // device root
    public static String getDeviceRoot (Activity activity) {
        if (RootUtil.isDeviceRooted()) {
            return activity.getString(R.string.device_rooted);
        } else {
            return activity.getString(R.string.device_root_not);
        }
    }
    // device sim serial
    public static String getSimSerial (TelephonyManager telephonyManager) {
        return telephonyManager.getSimSerialNumber();
    }
    // device subcriber id
    public static String getSubscriberID (TelephonyManager telephonyManager) {
        return telephonyManager.getSubscriberId();
    }
    // device tags
    public static String getDeviceTags () {
        return Build.TAGS;
    }
    // device type
    public static String getDeviceType () {
        return Build.TYPE;
    }
    // device uptime
    public static String getDeviceUptime () {
        long uptime = SystemClock.elapsedRealtime() / 1000;
        if (uptime == 0) {
            uptime = 1;
        }
        return convert(uptime);
    }
    private static String pad(int n) {
        if (n >= 10) {
            return String.valueOf(n);
        }
        return "0" + String.valueOf(n);
    }
    private static String convert(long uptime) {
        int seconds = (int) (uptime % 60);
        int minutes = (int) ((uptime / 60) % 60);
        int hours = (int) (uptime / 3600);
        if (hours > 0) {
            return hours + ":" + pad(minutes) + ":" + pad(seconds);
        }
        return pad(minutes) + ":" + pad(seconds);
    }
    // device user
    public static String getDeviceUser () { // unknown?
        return Build.USER;
    }
}