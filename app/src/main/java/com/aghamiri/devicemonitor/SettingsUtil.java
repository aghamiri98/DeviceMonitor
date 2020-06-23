package com.aghamiri.devicemonitor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class SettingsUtil {
    // rate
    public static void rate (Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
    // share
    public static void share (Context context, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }
/*    // language
    public static void changeLanguage (final Activity activity) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        int numberOfChoice = sharedPreferences.getInt(Values.NUMBER_OF_LANGUAGE, 0);
        String[] languageSupported = {activity.getString(R.string.english), activity.getString(R.string.vietnamese)};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AppCompatAlertDialogStyle)
                .setTitle(activity.getString(R.string.action_language))
                .setSingleChoiceItems(languageSupported, numberOfChoice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setLanguage(activity, Values.LANGUAGE_SUPPORTED_ALIAS[which]);
                        editor.putInt(Values.NUMBER_OF_LANGUAGE, which);
                        editor.putString(Values.LANGUAGE, Values.LANGUAGE_SUPPORTED_ALIAS[which]);
                        editor.commit();
                        Intent intent = new Intent(activity, ActivityRuler.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.finish();
                        activity.startActivity(intent);
                        dialog.dismiss();
                    }
                });
        builder.show();
    }*/
 /*   public static void setLanguage (Activity activity, String lang) {
        Resources res = activity.getApplicationContext().getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(new Locale(lang));
        } else {
            configuration.locale = new Locale(lang);
        }
        res.updateConfiguration(configuration, displayMetrics);
    }*/
    // more
    public static void more (Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.yanimsoft.ir"));
        context.startActivity(intent);
    }
}
