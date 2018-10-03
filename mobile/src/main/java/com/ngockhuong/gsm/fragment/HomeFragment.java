package com.ngockhuong.gsm.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ngockhuong.gsm.R;

public class HomeFragment extends Fragment {
    private Button btnTurnOn, btnTurnOff;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        getWidgets(view);
        setWidgets();
        addWidgetEvents();

        return view;
    }

    private void init() {

    }

    private void getWidgets(View view) {
        btnTurnOn = (Button) view.findViewById(R.id.btn_turn_on);
        btnTurnOff = (Button) view.findViewById(R.id.btn_turn_off);
    }

    private void setWidgets() {

    }

    private void addWidgetEvents() {
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Bật", Toast.LENGTH_SHORT).show();
            }
        });

        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Tắt", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
