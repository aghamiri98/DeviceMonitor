package com.aghamiri.devicemonitor.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.BatteryInfoUtil;


public class FragmentBattery extends Fragment {


  private static final String TAG = "FragmentScreen";
  private View view;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    view = inflater.inflate(R.layout.fragment_battery, container, false);

    Intent batteryIntent = getActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    TextView txtchargeValue = (TextView) view.findViewById(R.id.txtchargeValue);
    TextView txtCapacityValue = (TextView) view.findViewById(R.id.txtCapacityValue);
    TextView txtHealthValue = (TextView) view.findViewById(R.id.txtHealthValue);
    TextView txtStatusValue = (TextView) view.findViewById(R.id.txtStatusValue);
    TextView txtTechnologyValue = (TextView) view.findViewById(R.id.txtTechnologyValue);
    TextView txtTempValue = (TextView) view.findViewById(R.id.txtTempValue);
    TextView txtVoltageValue = (TextView) view.findViewById(R.id.txtVoltageValue);
    TextView txtPowerValue = (TextView) view.findViewById(R.id.txtPowerValue);

    txtchargeValue.setText(BatteryInfoUtil.getBatteryLevel(batteryIntent));
    txtCapacityValue.setText(BatteryInfoUtil.getBatteryCapacity(getActivity()));
    txtHealthValue.setText(BatteryInfoUtil.getBatteryHealth(batteryIntent, getActivity()));
    txtStatusValue.setText(BatteryInfoUtil.getBatteryStatus(batteryIntent, getActivity()));
    txtTechnologyValue.setText(BatteryInfoUtil.getBatteryTechnology(batteryIntent));
    txtTempValue.setText(BatteryInfoUtil.getBatteryTemp(batteryIntent));
    txtVoltageValue.setText(BatteryInfoUtil.getBatteryVoltage(batteryIntent));
    txtPowerValue.setText(BatteryInfoUtil.getBatteryPowerSource(batteryIntent, getActivity()));


    return view;
  }


  public static FragmentBattery newInstance() {

    Bundle args = new Bundle();


    FragmentBattery fragment = new FragmentBattery();
    fragment.setArguments(args);
    return fragment;
  }


}
