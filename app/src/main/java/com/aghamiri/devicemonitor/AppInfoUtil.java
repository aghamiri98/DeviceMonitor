package com.aghamiri.devicemonitor;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;



public class AppInfoUtil {
    // version app
    public static String getVersionApp (Activity activity) {
        try {
            return activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    // contact
    public static void connectContact (Activity activity) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
       // emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{activity.getString(R.string.yanimsoft_contact)});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}
