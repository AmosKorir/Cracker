package com.example.korir.cracker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.korir.cracker.R;
import com.example.korir.cracker.models.YoutubeModel;

import java.util.ArrayList;

public class VideoPostAdapter extends RecyclerView.Adapter<VideoPostAdapter.YoutubePostHolder> {

        private ArrayList<YoutubeModel> datasset;
        private Context context;

    public VideoPostAdapter(Context context,ArrayList<YoutubeModel> datasset ) {
        this.datasset = datasset;
        this.context = context;
    }

    @NonNull
    @Override
    public YoutubePostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_item_layout,parent,false);
        YoutubePostHolder postHolder=new YoutubePostHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubePostHolder holder, int position) {
        TextView titletext=holder.textViewtitle;
        TextView destext=holder.textViewDes;
        TextView publishtext=holder.textViewDate;
        ImageView image=holder.imageViewthumb;

        YoutubeModel model=datasset.get(position);
        titletext.setText(model.getTitle());
        destext.setText(model.getDescription());
        publishtext.setText(model.getPublishAt());

    }

    @Override
    public int getItemCount() {
        return datasset.size();
    }

    public static class YoutubePostHolder extends RecyclerView.ViewHolder{
        TextView textViewtitle;
        TextView textViewDes;
        TextView textViewDate;
       ImageView imageViewthumb;

        public YoutubePostHolder(View itemView) {
            super(itemView);
            this.textViewtitle=(TextView)itemView.findViewById(R.id.txttitle);
            this.textViewDate=(TextView)itemView.findViewById(R.id.txtdate);
            this.textViewDes=(TextView)itemView.findViewById(R.id.txtdes);
            this.imageViewthumb=(ImageView)itemView.findViewById(R.id.imagethumb);
        }
    }

}
