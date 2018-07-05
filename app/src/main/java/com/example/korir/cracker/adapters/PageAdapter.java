package com.example.korir.cracker.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.korir.cracker.fragments.ChannelFragment;
import com.example.korir.cracker.fragments.LiveFragment;
import com.example.korir.cracker.fragments.PlayListFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    int numOfPages;




    public PageAdapter(FragmentManager fragmentManager,int numOfPages) {
        super(fragmentManager);
        this.numOfPages=numOfPages;
    }


    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                ChannelFragment channels=new ChannelFragment();
                return  channels;
            case 1:
                PlayListFragment playlist=new PlayListFragment();
                return playlist;
            case 2:
                LiveFragment live=new LiveFragment();
                return live;
            default:
        }
        return null;
    }

    @Override
    public int getCount() {
        return  numOfPages;
    }
}
