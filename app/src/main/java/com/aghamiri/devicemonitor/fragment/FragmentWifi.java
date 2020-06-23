package com.aghamiri.devicemonitor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.WifiInfoUtil;


public class FragmentWifi extends Fragment {


    private static final String TAG = "FragmentScreen";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_wifi, container, false);


        TextView txtWifiNameValue = (TextView) view.findViewById(R.id.txtWifiNameValue);
        TextView txtIPAddressValue = (TextView) view.findViewById(R.id.txtIPAddressValue);
        TextView txtMACAddressValue = (TextView) view.findViewById(R.id.txtMACAddressValue);

        txtWifiNameValue.setText(WifiInfoUtil.getWifiName(getActivity()));
        txtIPAddressValue.setText(WifiInfoUtil.getIPAddress(getActivity()));
        txtMACAddressValue.setText(WifiInfoUtil.getWifiMACAddress(getActivity()));


        return view;
    }


    public static FragmentWifi newInstance() {

        Bundle args = new Bundle();


        FragmentWifi fragment = new FragmentWifi();
        fragment.setArguments(args);
        return fragment;
    }


}
