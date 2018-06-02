package com.cryptotext.cryptotext;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HandleBottomNavigation extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();

    HandleBottomNavigation(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    public void addFragments(Fragment fragment) {
        mFragments.add(fragment);
    }
}
