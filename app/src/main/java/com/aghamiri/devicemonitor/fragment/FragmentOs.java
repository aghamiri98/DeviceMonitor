package com.aghamiri.devicemonitor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.AndroidInfoUtil;


public class FragmentOs extends Fragment {


    private static final String TAG = "FragmentScreen";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_os, container, false);


        TextView txtVersionNameValue = (TextView) view.findViewById(R.id.txtVersionNameValue);
        TextView txtVersionCodeValue = (TextView) view.findViewById(R.id.txtVersionCodeValue);
        TextView txtBuildIDValue = (TextView) view.findViewById(R.id.txtBuildIDValue);
        TextView txtBuildTinmeValue = (TextView) view.findViewById(R.id.txtBuildTinmeValue);

        txtVersionNameValue.setText(AndroidInfoUtil.getAndroidVersionName());
        txtVersionCodeValue.setText(AndroidInfoUtil.getAndroidVersionCode());
        txtBuildIDValue.setText(AndroidInfoUtil.getAndroidBuildID());
        txtBuildTinmeValue.setText(AndroidInfoUtil.getAndroidBuildTinme());


        return view;
    }


    public static FragmentOs newInstance() {

        Bundle args = new Bundle();


        FragmentOs fragment = new FragmentOs();
        fragment.setArguments(args);
        return fragment;
    }


}
