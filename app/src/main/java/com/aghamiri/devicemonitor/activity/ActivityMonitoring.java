package com.aghamiri.devicemonitor.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.aghamiri.devicemonitor.Global;
import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.adapter.MonitoringViewPagerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class ActivityMonitoring extends AppCompatActivity {


   private String[] PERMISSIONS = new String[] {
            Manifest.permission.READ_PHONE_STATE
    };
    public static final int PERMISSIONS_MULTIPLE_REQUEST = 200;
    private static final String TAG = "ActivityMonitoring";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        setUpToolbar();

        if (checkPermissions()){
            // permissions granted.
            setupTabs();
        } else {
            // show dialog informing them that we lack certain permissions
        }

    }

    private void setupTabs() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.ViewPager);
        MonitoringViewPagerAdapter viewPagerAdapter = new MonitoringViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(5);
    }


    private void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.device);
        getSupportActionBar().setTitle(R.string.app_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void showSnack(String text) {
        Snackbar.make(this.findViewById(android.R.id.content),
                text,
                Snackbar.LENGTH_INDEFINITE).setAction("فعال سازی",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkPermissions()){
                                // permissions granted.
                                setupTabs();
                            } else {
                                showSnack("لطفا دسترسی را وارد نمایید");
                                // show dialog informing them that we lack certain permissions
                            }
                        }
                    }
                }).show();
    }


    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:PERMISSIONS) {
            result = ContextCompat.checkSelfPermission(this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), PERMISSIONS_MULTIPLE_REQUEST);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_MULTIPLE_REQUEST:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.
                    setupTabs();
                } else {
                    // no permissions granted.
                    showSnack("لطفا دسترسی را وارد نمایید");
                }
                return;
            }
        }
    }



}
