package com.aghamiri.devicemonitor.utils.deviceinfoutils;

import android.os.Build;


public class JVMInfoUtil {
    // jvm version
    public static String getJVMVersion (){
        return System.getProperty("java.vm.version");
    }
    // jvm class version
    public static String getJVMClassVersion () {
        return System.getProperty("java.class.version");
    }
    // jvm home
    public static String getJVMHome () {
        return System.getProperty("java.home");
    }
    // jvm vendor
    public static String getJVMVendor () {
        return System.getProperty("java.vm.vendor");
    }
    // jvm vm
    public static String getJVMJavaVM () {
        Object property = Build.VERSION.SDK_INT >= 21 ? "ART" : System.getProperty("java.vm.name");
        String property2 = System.getProperty("java.vm.version");
        if (property != null) {
            return new StringBuilder(String.valueOf(property)).append(" ").append(property2).toString();
        }
        return property2;
    }
    // jvm specification
    public static String getJVMSpecification () {
        return System.getProperty("java.vm.specification.name");
    }
    // jvm specification version
    public static String getJVMSpecificationVersion () {
        return System.getProperty("java.runtime.version");
    }
}

