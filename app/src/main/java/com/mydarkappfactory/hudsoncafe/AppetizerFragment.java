package com.mydarkappfactory.hudsoncafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AppetizerFragment extends Fragment {

    View view;
    ListView listView;

    public AppetizerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.appetizers, container, false);


        listView = (ListView) view.findViewById(R.id.listView);

        ListAdapter customAdapter = new ListAdapter(getContext(), R.layout.row_layout, SubmenuActivity.dishesA);

        listView.setAdapter(customAdapter);

        return view;



    }
}
