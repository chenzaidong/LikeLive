package cc.myandroid.likelive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 * 主页视图adapter
 */

public class HomeViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> dataFragment;
    private List<String> titles;
    public HomeViewPagerAdapter(FragmentManager fm,List<Fragment> dataFragment,List<String> titles) {
        super(fm);
        this.dataFragment=dataFragment;
        this.titles=titles;
    }

    @Override
    public Fragment getItem(int position) {
        return dataFragment.get(position);
    }

    @Override
    public int getCount() {
        return dataFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
