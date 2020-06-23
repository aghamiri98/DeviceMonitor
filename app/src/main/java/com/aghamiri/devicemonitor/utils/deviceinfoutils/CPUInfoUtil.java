package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.app.Activity;
import android.os.Build;


import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.utils.cpuutils.CPUClockSpeed;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;



public class CPUInfoUtil {
    private Activity activity;
    private String cpuModel;
    private int numberOfCores;
    private String cpuClockSpeed;

    public CPUInfoUtil (Activity activity) {
        this.activity = activity;
       // cpuModel ();
    }

    public void cpuModel () {
        numberOfCores = Runtime.getRuntime().availableProcessors();
        if (numberOfCores > 4) {
            cpuModel = getCPUModelName();
            if (cpuModel != "") {
                int identifier = activity.getResources().getIdentifier(cpuModel + "cls", "string", activity.getPackageName());
                cpuClockSpeed = identifier != 0 ? activity.getResources().getString(identifier) : "";
                int identifier2 = activity.getResources().getIdentifier(cpuModel, "string", activity.getPackageName());
                cpuModel = identifier2 != 0 ? activity.getResources().getString(identifier2) : "";
            } else {
                cpuModel = getCPUModel(fileCPUInfo());
                cpuClockSpeed = d(c(String.valueOf(0))) + " - " + d(b(String.valueOf(0)));
            }
        } else {
            cpuModel = (Build.MANUFACTURER + "_" + Build.MODEL + "_cpu").replaceAll("\\s", "").replaceAll("-", "").toLowerCase();
            int identifier = activity.getResources().getIdentifier(cpuModel, "string", activity.getPackageName());
            cpuModel = identifier != 0 ? activity.getResources().getString(identifier) : "";
            cpuClockSpeed = d(c(String.valueOf(0))) + " - " + d(b(String.valueOf(0)));
            if (cpuModel == "") {
                cpuModel = getCPUModel(fileCPUInfo());
                String revision = cpuModel.substring(cpuModel.indexOf("\nRevision"));
                cpuModel = cpuModel.replace(revision, "");
            }
        }
    }
    private String fileCPUInfo () {
        StringBuffer stringBuffer = new StringBuffer();
        if (new File("/proc/cpuinfo").exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/cpuinfo")));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine + "\n");
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
    private String getCPUModel(String cpuModel) {
        String name;
        if (cpuModel.contains("Hardware")) {
            name = cpuModel.substring(cpuModel.indexOf("Hardware"));
        } else if (cpuModel.contains("hardware")) {
            name = cpuModel.substring(cpuModel.indexOf("hardware"));
        } else if (cpuModel.contains("Model name")) {
            name = cpuModel.substring(cpuModel.indexOf("Model name"));
        } else if (cpuModel.contains("model name")) {
            name = cpuModel.substring(cpuModel.indexOf("model name"));
        } else {
            return "";
        }
        return name.substring(name.indexOf(":") + 2);
    }
    private String getCPUModelName() {
        int i;
//        int identifier = activity.getResources().getIdentifier("cpu_model", "array", activity.getPackageName());
        String[] stringArray = activity.getResources().getStringArray(R.array.cpu_model);
        int length = stringArray.length;
        String d = getCPUModel(fileCPUInfo());
        for (i = 0; i < length; i++) {
            if (d.toLowerCase().contains(stringArray[i].toLowerCase())) {
                return stringArray[i];
            }
        }
        return "";
    }
    public int b(String str) {
        int i = 0;
        try {
            i = CPUClockSpeed.a(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int c(String str) {
        int i = 0;
        try {
            i = CPUClockSpeed.b(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
    public static String d(int i) {
        double parseDouble = Double.parseDouble(String.valueOf(i));
        double d = 1000.0d * 1000.0d;
        return parseDouble < 1000.0d ? String.valueOf(parseDouble) + " KHz" : (parseDouble < 1000.0d || parseDouble >= d) ? (parseDouble < d || parseDouble >= d * 1000.0d) ? "1" : b(parseDouble / d) + " GHz" : String.valueOf(parseDouble / 1000.0d) + " MHz";
    }
    public static String b(double d) {
        return new DecimalFormat("#.##").format(d);
    }

    // cpu model
    public String getCPUModel () {
        return cpuModel;
    }
    // cores
    public String getCPUCores () {
        return "" + numberOfCores;
    }
    // cpu clock speed
    public String getCPUClockSpeed () {
        return cpuClockSpeed;
    }
    // load average
    public String getCPULoadAverage () {
        String cpuLoadAverage;
        String path;
        try {
            path = new RandomAccessFile("/proc/loadavg", "r").readLine();
            String[] split = path.split(" +");
            cpuLoadAverage = split[0] + "   last 1 min" + "\n" + split[1] + "   last 5 min" + "\n" + split[2] + "   last 15 min";
        } catch (IOException e2) {
            return "";
        }
        return cpuLoadAverage;
    }
    // cpu instruction set
    public String getCPUInstructionSet () {
        return Build.CPU_ABI + "," + Build.CPU_ABI2;
    }
    // cpu governor
    public String getCPUGovernor () {
        String cpuGovernor = "Unknown";
        String path = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_governor";
        if (new File(path).exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
                while (true) {
                    path = bufferedReader.readLine();
                    if (path == null) {
                        break;
                    }
                    cpuGovernor = path;
                }
                bufferedReader.close();
            } catch (IOException ignored) {
            }
        }
        return cpuGovernor;
    }
    // cpu java heap
    public String getCPUJavaHeap () {
        long cpuJavaHeap = Runtime.getRuntime().maxMemory();
        long kb = 1024 * 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        long tb = gb * 1024;
        long pb = tb * 1024;
        return cpuJavaHeap < 1024 ? convertByte((double) cpuJavaHeap) + " byte" :
                (cpuJavaHeap < 1024 || cpuJavaHeap >= kb) ?
                (cpuJavaHeap < kb || cpuJavaHeap >= mb) ?
                (cpuJavaHeap < mb || cpuJavaHeap >= gb) ?
                (cpuJavaHeap < gb || cpuJavaHeap >= tb) ?
                (cpuJavaHeap < tb || cpuJavaHeap >= pb) ?
                cpuJavaHeap >= pb ?
                convertByte(((double) cpuJavaHeap) / ((double) pb)) + " EB" : "???" :
                convertByte(((double) cpuJavaHeap) / ((double) tb)) + " PB" :
                convertByte(((double) cpuJavaHeap) / ((double) gb)) + " TB" :
                convertByte(((double) cpuJavaHeap) / ((double) mb)) + " GB" :
                convertByte(((double) cpuJavaHeap) / ((double) kb)) + " MB" :
                convertByte(((double) cpuJavaHeap) / ((double) 1024)) + " KB";
    }
    private String convertByte (double d) {
        return new DecimalFormat("#.##").format(d);
    }
}
