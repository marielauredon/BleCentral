package io.mjm.blecentral.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.bluetooth.BluetoothDevice;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.mjm.blecentral.R;
import io.mjm.blecentral.adapters.DevicesArrayAdapter;
import io.mjm.blecentral.models.DeviceViewModel;

public class DeviceScanFragment extends Fragment {
    private DeviceViewModel mViewModel;

    View view;

    Button btn_SCAN;
    BluetoothDevice deviceCourant;
    List<BluetoothDevice> listeEntite = null;
    DevicesArrayAdapter adapterDevice = null;
    ListView listviewDevices = null;


    public static DeviceScanFragment newInstance() {
        return new DeviceScanFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new DeviceViewModel();
        listeEntite = new ArrayList<BluetoothDevice>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.device_scan_fragment, container, false);

        // Bouton
        btn_SCAN = view.findViewById(R.id.btn_SCAN);

        btn_SCAN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startScanning();
            }
        });

// listview
        listviewDevices = view.findViewById(R.id.listviewDevices);
        adapterDevice = new DevicesArrayAdapter(getActivity(), listeEntite);
        listviewDevices.setAdapter(adapterDevice);
        listviewDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Object o = listviewDevices.getItemAtPosition(position);
                deviceCourant = (BluetoothDevice) o;
                mViewModel.setSelected(deviceCourant);
                // stopScanning();
            }
        });

        getActivity().setTitle(R.string.device_scan_fragment_label);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.getBluetoothDevices().observe(getActivity(), new Observer<List<BluetoothDevice>>() {
            @Override
            public void onChanged(List<BluetoothDevice> devices) {
                adapterDevice.setmLeDevices(devices);
            }
        });

    }

}