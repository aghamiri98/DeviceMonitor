package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.BufferedReader;
import java.io.FileReader;


public class MemoryInfoUtil {
    // free ram
    public static long getFreeRam(Activity activity) {
        try
        {
            ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return Math.abs(memoryInfo.availMem);
        }
        catch (Exception e)
        {
            return 0L;
        }

    }
    // total ram
    public static long getTotalRam(Activity activity)
    {
        try
        {
            if(Build.VERSION.SDK_INT >= 16)
            {
                ActivityManager actManager = (ActivityManager) activity.getSystemService("activity");
                MemoryInfo memInfo = new MemoryInfo();
                actManager.getMemoryInfo(memInfo);
                long totalMemory = memInfo.totalMem;
                return totalMemory;
            }
            else
            {
                String str1 = "/proc/meminfo";
                String str2;
                String[] arrayOfString;
                long initial_memory = 0;

                FileReader localFileReader = new FileReader(str1);
                BufferedReader localBufferedReader = new BufferedReader( localFileReader, 8192);
                str2 = localBufferedReader.readLine();//meminfo
                arrayOfString = str2.split("\\s+");
                //total Memory
                initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
                localBufferedReader.close();
                return initial_memory;
            }
        }
        catch (Exception e)
        {
            return 0L;
        }
    }
    // free rom
    public static long getFreeRom () {
        try
        {
            StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
            double freeIS = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                freeIS = (stat.getBlockSizeLong() * stat.getAvailableBlocksLong());
            }
            else
            {
                freeIS = stat.getAvailableBlocks() * stat.getBlockSize();
            }
            return (long) freeIS;
        }
        catch (Exception e)
        {
            return 0;
        }

    }
    // total rom
    public static long getTotalRom () {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double totalIS = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            totalIS = (stat.getBlockSizeLong() *  stat.getBlockCountLong());
        }
        else
        {
            totalIS = stat.getBlockCount() * stat.getBlockSize();
        }
        return (long) totalIS;
    }
}
