package com.mydarkappfactory.hudsoncafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class MainCourseFragment extends Fragment {

    View view;
    ListView listView;

    public MainCourseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.main_course, container, false);

        listView = (ListView) view.findViewById(R.id.listViewM);

        ListAdapter customAdapter = new ListAdapter(getContext(), R.layout.row_layout, SubmenuActivity.dishesM);

        listView.setAdapter(customAdapter);

        return view;
    }
}
