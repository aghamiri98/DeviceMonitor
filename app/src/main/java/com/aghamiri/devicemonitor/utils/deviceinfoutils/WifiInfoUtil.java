package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;


import com.aghamiri.devicemonitor.R;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class WifiInfoUtil {
    // network state support
    private static WifiManager getWifiInfo(Activity activity) {
        return (WifiManager) activity.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    }

    private static WifiInfo getWifiInfo(WifiManager manager) {
        if (manager.isWifiEnabled()) {
            return manager.getConnectionInfo();
        }
        return null;
    }

    private static NetworkInfo.DetailedState getNetworkStateDetail(WifiInfo wifiInfo, Activity activity) {
        if (wifiInfo != null) {
            return WifiInfo.getDetailedStateOf(wifiInfo.getSupplicantState());
        }
        return null;
    }

    // wifi signal
    public static String getWifiSignal(Activity activity) {
        WifiManager manager = (WifiManager) activity.getApplicationContext().getSystemService("wifi");
        int signal = manager.getConnectionInfo().getRssi();
        String result = signal + " dBm";
        if (signal >= -53 && signal != 85) {
            return result + "(" + activity.getApplicationContext().getString(R.string.wifi_strong) + ")";
        } else if (signal >= -73 && signal < -53) {
            return result + "(" + activity.getString(R.string.wifi_normal) + ")";
        } else if (signal < -73) {
            return result + "(" + activity.getString(R.string.wifi_week) + ")";
        }
        return activity.getString(R.string.wifi_signal_not_connected);
    }

    // wifi name
    public static String getWifiName(Activity activity) {
        if (getNetworkStateDetail(getWifiInfo(getWifiInfo(activity)), activity) == NetworkInfo.DetailedState.CONNECTED || getNetworkStateDetail(getWifiInfo(getWifiInfo(activity)), activity) == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            return getWifiInfo(getWifiInfo(activity)).getSSID();
        }
        return activity.getString(R.string.unknow);
    }

    // wifi ip address
    @SuppressLint("LongLogTag")
    public static String getIPAddress(Activity activity) {
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address) && intf.getDisplayName().contains("wlan0")) {
                        String ipAddress = inetAddress.getHostAddress().toString();
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("Socket exception in GetIP Address of Utilities", ex.toString());
        }
        return activity.getString(R.string.wifi_ip_address_unknow);
    }

    // wifi speed
    public static String getWifiSpeed(Activity activity) {
        if (getNetworkStateDetail(getWifiInfo(getWifiInfo(activity)), activity) == NetworkInfo.DetailedState.CONNECTED || getNetworkStateDetail(getWifiInfo(getWifiInfo(activity)), activity) == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            return getWifiInfo(getWifiInfo(activity)).getLinkSpeed() + "mbps";
        }
        return activity.getString(R.string.unknow);
    }

    // wifi mac address
    public static String getWifiMACAddress(Activity activity) {
        if (getNetworkStateDetail(getWifiInfo(getWifiInfo(activity)), activity) == NetworkInfo.DetailedState.CONNECTED || getNetworkStateDetail(getWifiInfo(getWifiInfo(activity)), activity) == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            return getWifiInfo(getWifiInfo(activity)).getMacAddress();
        }
        return activity.getString(R.string.unknow);
    }
}

