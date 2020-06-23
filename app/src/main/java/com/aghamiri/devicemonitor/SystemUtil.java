package com.aghamiri.devicemonitor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aghamiri.devicemonitor.utils.deviceinfoutils.AndroidInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.BatteryInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.CPUInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.DeviceInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.GPUInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.JVMInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.MemoryInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.NetworkInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.ScreenInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.SensorInfoUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.WifiInfoUtil;


public class SystemUtil {
    // cpu
    public static void getCPUInfo (Activity activity, TextView... text) {
        CPUInfoUtil cpuInfoUtil = new CPUInfoUtil(activity);
        text[0].setText(cpuInfoUtil.getCPUModel());
        text[1].setText(cpuInfoUtil.getCPUCores());
        text[2].setText(cpuInfoUtil.getCPUClockSpeed());
        text[3].setText(cpuInfoUtil.getCPULoadAverage());
        text[4].setText(cpuInfoUtil.getCPUInstructionSet());
        text[5].setText(cpuInfoUtil.getCPUGovernor());
        text[6].setText(cpuInfoUtil.getCPUJavaHeap());
    }

    // gpu
    public static void getGPUInfo(Activity activity, LinearLayout high, LinearLayout low, TextView... text) {
        new GPUInfoUtil(activity, high, low, text);
    }

    //device
    @SuppressLint("SetTextI18n")
    public static void getDeviceInfo(final Activity activity, final TextView... text) {
        TelephonyManager mTelemamanger = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        text[0].setText(Html.fromHtml("<u>" + DeviceInfoUtil.getDeviceAndroidID(activity) + "</u>"));
        text[0].setTextColor(Color.parseColor("#3f51b5"));
        text[1].setText(DeviceInfoUtil.getDeviceBoard());
        text[2].setText(DeviceInfoUtil.getDeviceBrand());
        text[3].setText(DeviceInfoUtil.getDeviceBuildTime());
        text[4].setText(DeviceInfoUtil.getDeviceCPUABI());
        text[5].setText(DeviceInfoUtil.getDeviceCPUABI2());
        text[6].setText(DeviceInfoUtil.getDeviceDevice());
        text[7].setText(Html.fromHtml("<u>" + DeviceInfoUtil.getDeviceDeviceSerial() + "</u>"));
        text[7].setTextColor(Color.parseColor("#3f51b5"));
        text[8].setText(DeviceInfoUtil.getDeviceDisplay());
        text[9].setText(DeviceInfoUtil.getDeviceHardware());
        text[10].setText(DeviceInfoUtil.getDeviceHost());
        text[11].setText(DeviceInfoUtil.getDeviceICCCard(activity, mTelemamanger));
        text[12].setText(Html.fromHtml("<u>" + DeviceInfoUtil.getDeviceIMEI(mTelemamanger) + "</u>"));
        text[12].setTextColor(Color.parseColor("#3f51b5"));
        text[13].setText(DeviceInfoUtil.getDeviceKernel());
        text[14].setText(DeviceInfoUtil.getDeviceManufacturer());
        text[15].setText(DeviceInfoUtil.getDeviceModel());
        text[16].setText(DeviceInfoUtil.getDeviceProduct());
        text[17].setText(DeviceInfoUtil.getDevicePhonenumber(activity));
        text[18].setText(DeviceInfoUtil.getDeviceRelease());
        text[19].setText(DeviceInfoUtil.getDeviceRoot(activity));
        text[20].setText(Html.fromHtml("<u>" + DeviceInfoUtil.getSimSerial(mTelemamanger) + "</u>"));
        text[20].setTextColor(Color.parseColor("#3f51b5"));
        text[21].setText(Html.fromHtml("<u>" + DeviceInfoUtil.getSubscriberID(mTelemamanger) + "</u>"));
        text[21].setTextColor(Color.parseColor("#3f51b5"));
        text[22].setText(DeviceInfoUtil.getDeviceTags());
        text[23].setText(DeviceInfoUtil.getDeviceType());
        text[24].setText(DeviceInfoUtil.getDeviceUptime());
        text[25].setText(DeviceInfoUtil.getDeviceUser());

        text[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[0]);
            }
        });
        text[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[7]);
            }
        });
        text[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[12]);
            }
        });
        text[20].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[20]);
            }
        });
        text[21].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[21]);
            }
        });
    }

    //android
    @SuppressLint("SetTextI18n")
    public static void getAndroidInfo(final Activity activity, final TextView... text) {
        text[0].setText(AndroidInfoUtil.getAndroidVersionName());
        text[1].setText(AndroidInfoUtil.getAndroidVersionCode());
        text[2].setText(AndroidInfoUtil.getAndroidBootLeader());
        text[3].setText(Html.fromHtml("<u>" + AndroidInfoUtil.getAndroidFingerprint() + "</u>"));
        text[3].setTextColor(Color.parseColor("#3f51b5"));
        text[4].setText(AndroidInfoUtil.getAndroidBuildID());
        text[5].setText(AndroidInfoUtil.getAndroidBuildTinme());
        text[6].setText(AndroidInfoUtil.getAndroidCodeName());
        text[7].setText(AndroidInfoUtil.getAndroidIncremental());
        text[8].setText(AndroidInfoUtil.getAndroidOpenGLES(activity));
        text[9].setText(Html.fromHtml("<u>" + AndroidInfoUtil.getAndroidOpenGLVersion() + "</u>"));
        text[9].setTextColor(Color.parseColor("#3f51b5"));
        text[10].setText(AndroidInfoUtil.getAndroidPlatformVersion());
        text[11].setText(AndroidInfoUtil.getAndroidRILVersion());
        text[12].setText(AndroidInfoUtil.getAndroidSDKVersion());
        text[13].setText(AndroidInfoUtil.getAndroidSystemEncoding());
        text[14].setText(AndroidInfoUtil.getAndroidSystemLanguage());
        text[15].setText(AndroidInfoUtil.getAndroidSystemOSVersion());
        text[16].setText(AndroidInfoUtil.getAndroidSystemRegion());

        text[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[3]);
            }
        });
        text[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[9]);
            }
        });
    }

    //jvm
    @SuppressLint("SetTextI18n")
    public static void getJVMInfo(TextView... text) {
        text[0].setText(JVMInfoUtil.getJVMVersion());
        text[1].setText(JVMInfoUtil.getJVMClassVersion());
        text[2].setText(JVMInfoUtil.getJVMHome());
        text[3].setText(JVMInfoUtil.getJVMVendor());
        text[4].setText(JVMInfoUtil.getJVMJavaVM());
        text[5].setText(JVMInfoUtil.getJVMSpecification());
        text[6].setText(JVMInfoUtil.getJVMSpecificationVersion());
    }

    //wifi
    @SuppressLint("SetTextI18n")
    public static void getWifiInfo(final Activity activity, final TextView... text) {
        text[0].setText("" + WifiInfoUtil.getWifiSignal(activity));
        text[1].setText("" + WifiInfoUtil.getWifiName(activity));
        text[2].setText(Html.fromHtml("<u>" + WifiInfoUtil.getIPAddress(activity) + "</u>"));
        text[2].setTextColor(Color.parseColor("#3f51b5"));
        text[3].setText("" + WifiInfoUtil.getWifiSpeed(activity));
        text[4].setText(Html.fromHtml("<u>" + WifiInfoUtil.getWifiMACAddress(activity) + "</u>"));
        text[4].setTextColor(Color.parseColor("#3f51b5"));

        text[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[2]);
            }
        });
        text[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[4]);
            }
        });
    }

    //sim
    @SuppressLint("SetTextI18n")
    public static void getNetworkInfo(final Activity activity, final TextView... text) {
        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
        text[0].setText(NetworkInfoUtil.getNetworkSignal(telephonyManager, activity));
        text[1].setText(Html.fromHtml("<u>" + NetworkInfoUtil.getNetworkBaseband(activity) + "</u>"));
        text[1].setTextColor(Color.parseColor("#3f51b5"));
        text[2].setText(NetworkInfoUtil.getNetworkCountry(telephonyManager));
        text[3].setText(NetworkInfoUtil.getNetworkType(activity));
        text[4].setText(Html.fromHtml("<u>" + NetworkInfoUtil.getNetworkOperatorID(telephonyManager) + "</u>"));
        text[4].setTextColor(Color.parseColor("#3f51b5"));
        text[5].setText(NetworkInfoUtil.getNetworkOperatorName(telephonyManager));
        text[6].setText(NetworkInfoUtil.getPhoneType(telephonyManager, activity));
        text[7].setText(NetworkInfoUtil.getNetworkServiceMode(activity));
        text[8].setText(NetworkInfoUtil.getSimStatus(activity, telephonyManager));
        text[9].setText(Html.fromHtml("<u>" + NetworkInfoUtil.getSimSerial(telephonyManager) + "</u>"));
        text[9].setTextColor(Color.parseColor("#3f51b5"));

        text[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[1]);
            }
        });
        text[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[4]);
            }
        });
        text[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeInfoToClipboard(activity, text[9]);
            }
        });
    }

    //battery
    @SuppressLint("SetTextI18n")
    public static void getBatteryInfo(Activity activity, TextView... text) {
        Intent batteryIntent = activity.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        text[0].setText(BatteryInfoUtil.getBatteryLevel(batteryIntent));
        text[1].setText(BatteryInfoUtil.getBatteryCapacity(activity));
        text[2].setText(BatteryInfoUtil.getBatteryHealth(batteryIntent, activity));
        text[3].setText(BatteryInfoUtil.getBatteryPowerSource(batteryIntent, activity));
        text[4].setText(BatteryInfoUtil.getBatteryStatus(batteryIntent, activity));
        text[5].setText(BatteryInfoUtil.getBatteryTechnology(batteryIntent));
        text[6].setText(BatteryInfoUtil.getBatteryTemp(batteryIntent));
        text[7].setText(BatteryInfoUtil.getBatteryVoltage(batteryIntent));
    }

    //screen
    @SuppressLint("SetTextI18n")
    public static void getScreenInfo(Activity activity, TextView... text) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        text[0].setText(ScreenInfoUtil.getScreenBrightness(activity));
        text[1].setText(ScreenInfoUtil.getScreenDensity(displayMetrics));
        text[2].setText(ScreenInfoUtil.getScreenTypeDensity(displayMetrics));
        text[3].setText(ScreenInfoUtil.getScreenOrientation(activity));
        text[4].setText(ScreenInfoUtil.getScreenRefreshRate(activity));
        text[5].setText(ScreenInfoUtil.getScreenResolution(displayMetrics));
        text[6].setText(ScreenInfoUtil.getScreenSize(displayMetrics));
        text[7].setText(ScreenInfoUtil.getScreenType(activity));
    }

    //memory
    public static void getMemoryInfo(Activity activity, TextView... text) {
        text[0].setText(StorageUtil.convertStorage(MemoryInfoUtil.getFreeRam(activity)));
        text[1].setText(StorageUtil.convertStorage(MemoryInfoUtil.getTotalRam(activity)));
        text[2].setText(StorageUtil.convertStorage(MemoryInfoUtil.getFreeRom()));
        text[3].setText(StorageUtil.convertStorage(MemoryInfoUtil.getTotalRom()));
    }

    //sensor
    public static void getSensorInfo(Activity activity, TextView... text) {
        SensorInfoUtil sensorInfoUtil = new SensorInfoUtil(activity, text);
        sensorInfoUtil.getSensorAccelerometer();
        sensorInfoUtil.getSensorAll();
        sensorInfoUtil.getSensorAmbientTemperature();
        sensorInfoUtil.getSensorGameRotationVector();
        sensorInfoUtil.getSensorGeomagneticRotationVector();
        sensorInfoUtil.getSensorGravity();
        sensorInfoUtil.getSensorGyroscope();
        sensorInfoUtil.getSensorGyroscopeUncalibrated();
        sensorInfoUtil.getSensorHeartRate();
        sensorInfoUtil.getSensorLight();
        sensorInfoUtil.getSensorLinearAcceleration();
        sensorInfoUtil.getSensorMagneticField();
        sensorInfoUtil.getSensorMagneticFieldUncalibrated();
        sensorInfoUtil.getSensorOrientation();
        sensorInfoUtil.getSensorPressure();
        sensorInfoUtil.getSensorProximity();
        sensorInfoUtil.getSensorRelativeHumidity();
        sensorInfoUtil.getSensorRotationVector();
        sensorInfoUtil.getSensorSignificantMotion();
        sensorInfoUtil.getSensorStepCounter();
        sensorInfoUtil.getSensorStepDetector();
        sensorInfoUtil.getSensorTemperature();
    }

    // clipboard
    private static void takeInfoToClipboard (Activity activity, TextView text) {
        String cutString = String.valueOf(text.getText());
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(cutString);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", cutString);
            clipboard.setPrimaryClip(clip);
        }
        Toast.makeText(activity, "" + activity.getString(R.string.copy_text_to_clipboard) + " " + cutString, Toast.LENGTH_SHORT).show();
    }
}
