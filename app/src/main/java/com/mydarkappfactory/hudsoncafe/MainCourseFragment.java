package com.mydarkappfactory.hudsoncafe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Mohit on 10/8/2017.
 */

public class MainCourseFragment extends Fragment {

    View view;

    public MainCourseFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.main_course, container, false);
        return view;
    }
}
