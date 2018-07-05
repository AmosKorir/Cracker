package com.example.korir.cracker.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.korir.cracker.R;
import com.example.korir.cracker.adapters.VideoPostAdapter;
import com.example.korir.cracker.models.YoutubeModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {


    public ChannelFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    VideoPostAdapter adapter=null;
    ArrayList<YoutubeModel> arrayList=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_channel, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.channellist);
            inList();
        return view;
    }

    private void inList() {
        arrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

}
