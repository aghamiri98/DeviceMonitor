package com.aghamiri.devicemonitor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aghamiri.devicemonitor.R;
import com.aghamiri.devicemonitor.StorageUtil;
import com.aghamiri.devicemonitor.utils.deviceinfoutils.MemoryInfoUtil;


public class FragmentMemory extends Fragment {


    private static final String TAG = "FragmentScreen";
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_memory, container, false);


        TextView txtTotalRamValue = (TextView) view.findViewById(R.id.txtTotalRamValue);
        TextView txtFreeRamValue = (TextView) view.findViewById(R.id.txtFreeRamValue);
        TextView txtTotalRomValue = (TextView) view.findViewById(R.id.txtTotalRomValue);
        TextView txtFreeRomValue = (TextView) view.findViewById(R.id.txtFreeRomValue);

        txtTotalRamValue.setText(StorageUtil.convertStorage(MemoryInfoUtil.getTotalRam(getActivity())));
        txtFreeRamValue.setText(StorageUtil.convertStorage(MemoryInfoUtil.getFreeRam(getActivity())));
        txtTotalRomValue.setText(StorageUtil.convertStorage(MemoryInfoUtil.getTotalRom()));
        txtFreeRomValue.setText(StorageUtil.convertStorage(MemoryInfoUtil.getFreeRom()));


        return view;
    }


    public static FragmentMemory newInstance() {

        Bundle args = new Bundle();


        FragmentMemory fragment = new FragmentMemory();
        fragment.setArguments(args);
        return fragment;
    }


}
