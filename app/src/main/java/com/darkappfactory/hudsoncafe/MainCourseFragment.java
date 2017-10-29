package com.darkappfactory.hudsoncafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class MainCourseFragment extends Fragment {

    View view;
    ListView listView;
    DishListAdapter dishListAdapter;

    public MainCourseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.main_course, container, false);

        listView = (ListView) view.findViewById(R.id.listViewM);

        dishListAdapter = new DishListAdapter(getContext(), R.layout.row_layout, SubmenuActivity.dishesM, "MAIN_COURSE", SubmenuActivity.db);
        listView.setAdapter(dishListAdapter);
        return view;
    }
}
