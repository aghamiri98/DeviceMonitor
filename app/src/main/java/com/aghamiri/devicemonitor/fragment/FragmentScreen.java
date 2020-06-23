package com.aghamiri.devicemonitor.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.ScreenInfoUtil;


public class FragmentScreen extends Fragment {


    private static final String TAG = "FragmentScreen";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_screen, container, false);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();

        TextView txtBrithnesValue = (TextView) view.findViewById(R.id.txtBrithnesValue);
        TextView txtDeensityValue = (TextView) view.findViewById(R.id.txtDeensityValue);
        TextView txtDeensityTypeValue = (TextView) view.findViewById(R.id.txtDeensityTypeValue);
        TextView txtResolutionValue = (TextView) view.findViewById(R.id.txtResolutionValue);
        TextView txtScreenSizeValue = (TextView) view.findViewById(R.id.txtScreenSizeValue);

        txtBrithnesValue.setText(ScreenInfoUtil.getScreenBrightness(getActivity()));
        txtDeensityValue.setText(ScreenInfoUtil.getScreenDensity(displayMetrics));
        txtDeensityTypeValue.setText(ScreenInfoUtil.getScreenTypeDensity(displayMetrics));
        txtResolutionValue.setText(ScreenInfoUtil.getScreenResolution(displayMetrics));
        txtScreenSizeValue.setText(ScreenInfoUtil.getScreenSize(displayMetrics));


        return view;
    }


    public static FragmentScreen newInstance() {

        Bundle args = new Bundle();


        FragmentScreen fragment = new FragmentScreen();
        fragment.setArguments(args);
        return fragment;
    }


}
