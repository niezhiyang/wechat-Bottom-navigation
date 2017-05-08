package kitrobot.com.wechat_bottom_navigation;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * on 2017/5/8.
 * 类的描述:
 */

public class MyAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> mFragments;
    public MyAdapter(FragmentManager fm , ArrayList<Fragment> fragments) {
        super(fm);
        mFragments=fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
