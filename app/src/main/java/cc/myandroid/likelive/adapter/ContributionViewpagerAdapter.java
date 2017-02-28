package cc.myandroid.likelive.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 贡献榜viewpager适配器
 * Created by Administrator on 2017/2/23.
 */

public class ContributionViewpagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment>fraList;
    private List<String>cTitle;
    public ContributionViewpagerAdapter(FragmentManager fm,List<Fragment>fraList,List<String>cTitle) {
        super(fm);
        this.fraList=fraList;
        this.cTitle=cTitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fraList.get(position);
    }

    @Override
    public int getCount() {
        return fraList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return cTitle.get(position);
    }
}
