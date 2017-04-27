package com.jcoder.app.folink.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jcoder.app.folink.fragment.FolinkImagesFragment;
import com.jcoder.app.folink.fragment.FolinkNewsFragment;
import com.jcoder.app.folink.fragment.FolinkProfileFragment;

/**
 * Created by Rory on 2016/10/13.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    public final static int PAGE_COUNT = 3;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FolinkNewsFragment.newInstance();
            case 1:
                return FolinkImagesFragment.newInstance();
            case 2:
                return FolinkProfileFragment.newInstance();
            default:
                return FolinkNewsFragment.newInstance();
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }



    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}


