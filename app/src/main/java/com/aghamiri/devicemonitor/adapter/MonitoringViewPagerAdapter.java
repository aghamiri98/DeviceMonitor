package com.aghamiri.devicemonitor.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.aghamiri.devicemonitor.Global;
import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.fragment.FragmentBattery;
import com.aghamiri.devicemonitor.fragment.FragmentDevice;
import com.aghamiri.devicemonitor.fragment.FragmentMemory;
import com.aghamiri.devicemonitor.fragment.FragmentOs;
import com.aghamiri.devicemonitor.fragment.FragmentScreen;
import com.aghamiri.devicemonitor.fragment.FragmentWifi;



public class MonitoringViewPagerAdapter extends FragmentPagerAdapter {

    public MonitoringViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentScreen.newInstance();
            case 1:
                return FragmentBattery.newInstance();
            case 2:
                return FragmentWifi.newInstance();
            case 3:
                return FragmentMemory.newInstance();
            case 4:
                return FragmentDevice.newInstance();
            case 5:
                return FragmentOs.newInstance();

            default:
                return FragmentOs.newInstance();
        }
    }


    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Global.context.getString(R.string.screen_information);
            case 1:
                return Global.context.getString(R.string.battery_information);
            case 2:
                return Global.context.getString(R.string.wifi_information);
            case 3:
                return Global.context.getString(R.string.memory_information);

            case 4:
                return Global.context.getString(R.string.device_information);
            case 5:
                return Global.context.getString(R.string.os_information);

            default:
                return "";

        }
    }
}
