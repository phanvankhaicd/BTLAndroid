package com.example.corona.ViewController.Home.FragmentHome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.corona.Model.HistoryDeclare;
import com.example.corona.R;
import com.example.corona.ViewController.Declare.HistoryDeclareAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeclareFragment extends Fragment {

    public DeclareFragment() {
        // Required empty public constructor
    }
    private ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_declare, container, false);
        init(view);
        ArrayList<HistoryDeclare> data = new ArrayList<>();
        data.add(new HistoryDeclare(11,"2020-05-01T14:14:39.000+0000","YES","YES","YES","YES","NO",
                "BAD","Sốt"));
        data.add(new HistoryDeclare(11,"2020-05-01T14:14:39.000+0000","YES","YES","YES","YES","NO",
                "BAD","Sốt"));
        data.add(new HistoryDeclare(11,"2020-05-01T14:14:39.000+0000","YES","YES","YES","YES","NO",
                "BAD","Sốt"));
        data.add(new HistoryDeclare(11,"2020-05-01T14:14:39.000+0000","YES","YES","YES","YES","NO",
                "BAD","Sốt"));

        HistoryDeclareAdapter adapter = new HistoryDeclareAdapter(data,getContext());
        lv.setAdapter(adapter);
        return view;
    }

    private void init(View view) {
        lv = view.findViewById(R.id.lv_history);
    }
}
