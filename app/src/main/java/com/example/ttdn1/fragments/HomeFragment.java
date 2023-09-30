package com.example.ttdn1.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ttdn1.MainActivity;
import com.example.ttdn1.R;
import com.example.ttdn1.adapter.HomeAdapter;
import com.example.ttdn1.model.Home;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView rcv;
    private HomeAdapter homeAdapter;
    private MainActivity mainActivity;
    List<Home> list;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        list = new ArrayList<>();
        list.add(new Home("Take care of you", "Admina Thembi", R.drawable.rectanglee));
        list.add(new Home("The stranger inside you", "Jeane Lebras", R.drawable.rectangler));
        list.add(new Home("Edwall of beauty mind ", "Jacob Givson", R.drawable.rectanglev));
        list.add(new Home("The stranger inside you", "Jeane Lebras", R.drawable.rectangler));
        list.add(new Home("Edwall of beauty mind ", "Jacob Givson", R.drawable.rectanglev));
        rcv = view.findViewById(R.id.rcvView);
        mainActivity = (MainActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcv.setLayoutManager(linearLayoutManager);
        homeAdapter = new HomeAdapter(list);
        rcv.setAdapter(homeAdapter);
        return view;
    }

//    public void getHome(){
//        Home home1 = new Home("Take care of you", "Admina Thembi", R.drawable.rectanglee);
//        Home home2 = new Home("The stranger inside you", "Jeane Lebras", R.drawable.rectangler);
//        Home home3 = new Home("Edwall of beauty mind ", "Jacob Givson", R.drawable.rectanglev);
//        Home home4 = new Home("name", "music", R.drawable.rectanglev);
//
//        list.add(home1);
//        list.add(home2);
//        list.add(home3);
//        list.add(home4);
//    }
}