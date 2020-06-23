package com.aghamiri.devicemonitor.utils.cpuutils;

import java.io.InputStream;
import java.util.Scanner;


public class CPUClockSpeed {
    public static int a(String str) {
        return c("/sys/devices/system/cpu/cpu" + str + "/cpufreq/cpuinfo_max_freq");
    }

    public static final String a(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        return stringBuilder.toString();
    }

    public static int b(String str) {
        return c("/sys/devices/system/cpu/cpu" + str + "/cpufreq/cpuinfo_min_freq");
    }

    private static int c(String str) {
        try {
            return Integer.parseInt(a(new ProcessBuilder(new String[]{"/system/bin/cat", str}).start().getInputStream()));
        } catch (Throwable e) {
            try {
                throw new Exception(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return 0;
    }
}
