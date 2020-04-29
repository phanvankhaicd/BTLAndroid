package com.example.corona.ViewController.Home.FragmentHome;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.corona.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QandAFrangment extends Fragment {

    public QandAFrangment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_q_and_a, container, false);
    }
}
