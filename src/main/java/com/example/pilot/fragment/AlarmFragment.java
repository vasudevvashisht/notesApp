package com.example.pilot.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pilot.AlarmService;
import com.example.pilot.R;

public class AlarmFragment extends Fragment implements View.OnClickListener{

    private Button startService, stopService;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alarm,null);
        initViews();
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        return view;

    }

    private void initViews() {

        startService = view.findViewById(R.id.start_service);
        stopService = view.findViewById(R.id.stop_service);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                getActivity().startService(new Intent(getActivity(), AlarmService.class));
                break;
            case R.id.stop_service:
                getActivity().stopService(new Intent(getActivity(),AlarmService.class));
                break;

        }
    }
}
