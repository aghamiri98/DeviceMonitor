package com.aghamiri.devicemonitor.fragment;

import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.DeviceInfoUtil;


public class FragmentDevice extends Fragment {


    private static final String TAG = "FragmentScreen";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_device, container, false);

        TelephonyManager mTelemamanger = (TelephonyManager) getActivity().getSystemService(getContext().TELEPHONY_SERVICE);

        TextView txtDeviceModelValue = (TextView) view.findViewById(R.id.txtDeviceModelValue);
        TextView txtDeviceBrandValue = (TextView) view.findViewById(R.id.txtDeviceBrandValue);
        TextView txtDeviceBoardValue = (TextView) view.findViewById(R.id.txtDeviceBoardValue);
        TextView txtDeviceSerialValue = (TextView) view.findViewById(R.id.txtDeviceSerialValue);
        TextView txtDeviceIMEIValue = (TextView) view.findViewById(R.id.txtDeviceIMEIValue);
        TextView txtPhonenumberValue = (TextView) view.findViewById(R.id.txtPhonenumberValue);
        TextView txtDeviceRootValue = (TextView) view.findViewById(R.id.txtDeviceRootValue);

        txtDeviceModelValue.setText(DeviceInfoUtil.getDeviceModel());
        txtDeviceBrandValue.setText(DeviceInfoUtil.getDeviceBrand());
        txtDeviceBoardValue.setText(DeviceInfoUtil.getDeviceBoard());
        txtDeviceSerialValue.setText(DeviceInfoUtil.getDeviceDeviceSerial());
        txtDeviceIMEIValue.setText(DeviceInfoUtil.getDeviceIMEI(mTelemamanger));
        txtPhonenumberValue.setText(DeviceInfoUtil.getDevicePhonenumber(getActivity()));
        txtDeviceRootValue.setText(DeviceInfoUtil.getDeviceRoot(getActivity()));


        return view;
    }


    public static FragmentDevice newInstance() {
        Bundle args = new Bundle();
        FragmentDevice fragment = new FragmentDevice();
        fragment.setArguments(args);
        return fragment;
    }


}
