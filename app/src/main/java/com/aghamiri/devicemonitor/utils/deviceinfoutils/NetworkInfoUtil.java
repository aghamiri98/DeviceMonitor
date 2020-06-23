package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;


import com.aghamiri.devicemonitor.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class NetworkInfoUtil {
    private static int signal = 1000;
    // network signal
    public static String getNetworkSignal (TelephonyManager telephonyManager, Activity activity) {
        MyPhoneStateListener MyListener = new MyPhoneStateListener();
        telephonyManager.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        String result;
        if (signal == 1000) {
            result = activity.getString(R.string.unknow);
        } else {
            result = signal + " dBm";
        }
        if (signal >= -53 && signal != 85) {
            result = result + " (" + activity.getString(R.string.network_signal_strong) + ")";
        } else if (signal >= -73 && signal < -53) {
            result = result + " (" + activity.getString(R.string.network_signal_normal) + ")";
        } else if (signal < -73) {
            result = result + " (" + activity.getString(R.string.network_signal_weak) + ")";
        }

        return result;
    }
    private static class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            signal = signalStrength.getGsmSignalStrength() * 2 - 113;
        }

    }
    // network baseband
    public static String getNetworkBaseband (Activity activity) {
        String line;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + "gsm.version.baseband");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        }
        catch (IOException ex) {
            return activity.getString(R.string.unknow);
        }
        return line;
    }
    // network country
    public static String getNetworkCountry (TelephonyManager telephonyManager) {
        return telephonyManager.getNetworkCountryIso();
    }
    // network type
    public static String getNetworkType (Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if(info == null || !info.isConnected())
            return activity.getString(R.string.network_network_type_not_connected);
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return activity.getString(R.string.network_network_type_wifi);
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return activity.getString(R.string.network_network_type_2g_gprs);
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return activity.getString(R.string.network_network_type_2g_edge);
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return activity.getString(R.string.network_network_type_2g_cdma);
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return activity.getString(R.string.network_network_type_2g_1xrtt);
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return activity.getString(R.string.network_network_type_2g_iden);
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return activity.getString(R.string.network_network_type_3g_umts);
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return activity.getString(R.string.network_network_type_3g_evdo_0);
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return activity.getString(R.string.network_network_type_3g_evdo_a);
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return activity.getString(R.string.network_network_type_3g_hsdpa);
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return activity.getString(R.string.network_network_type_3g_hsupa);
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return activity.getString(R.string.network_network_type_3g_hspa);
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    return activity.getString(R.string.network_network_type_3g_evdo_b);
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return activity.getString(R.string.network_network_type_3g_ehrpd);
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return activity.getString(R.string.network_network_type_3g_hspap);
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return activity.getString(R.string.network_network_type_4g);
                default:
                    return activity.getString(R.string.unknow);
            }
        }
        return activity.getString(R.string.unknow);
    }
    // network operator id
    public static String getNetworkOperatorID (TelephonyManager telephonyManager) {
        return telephonyManager.getNetworkOperator();
    }
    // network operator name
    public static String getNetworkOperatorName (TelephonyManager telephonyManager) {
        return telephonyManager.getNetworkOperatorName();
    }
    // network phone type
    public static String getPhoneType (TelephonyManager telephonyManager, Activity activity) {
        switch (telephonyManager.getPhoneType()) {
            case TelephonyManager.PHONE_TYPE_NONE:
                return activity.getString(R.string.network_phone_type_none);
            case TelephonyManager.PHONE_TYPE_GSM:
                return activity.getString(R.string.network_phone_type_gsm);
            case TelephonyManager.PHONE_TYPE_CDMA:
                return activity.getString(R.string.network_phone_type_cdma);
            case TelephonyManager.PHONE_TYPE_SIP:
                return activity.getString(R.string.network_phone_type_sip);
            default:
                return activity.getString(R.string.unknow);
        }
    }
    // network service mode
    public static String getNetworkServiceMode (Activity activity) {
        ServiceState serviceState = new ServiceState();
        return serviceState.getIsManualSelection() ? activity.getString(R.string.network_service_mode_manual) : activity.getString(R.string.network_service_mode_automatic);
    }
    // network sim
    public static String getSimStatus (Activity activity, TelephonyManager telephonyManager) {
        int status = telephonyManager.getSimState();
        switch (status) {
            case TelephonyManager.SIM_STATE_ABSENT:
                return activity.getString (R.string.network_sim_absent);
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                return activity.getString (R.string.network_sim_network_locked);
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                return activity.getString (R.string.network_sim_pin_required);
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                return activity.getString (R.string.network_sim_puk_required);
            case TelephonyManager.SIM_STATE_READY:
                return activity.getString (R.string.network_sim_ready);
            case TelephonyManager.SIM_STATE_UNKNOWN:
                return activity.getString (R.string.unknow);
        }
        return activity.getString (R.string.unknow);
    }
    // network sim serial
    public static String getSimSerial (TelephonyManager telephonyManager) {
        return telephonyManager.getSimSerialNumber();
    }
}

