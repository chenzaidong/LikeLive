package cc.myandroid.likelive.ui.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.ContributionViewpagerAdapter;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.ui.fragment.ContributionFragment;

public class ContributionActivity extends BaseActivity {
    private ContributionFragment mContributionFragment;
    private TabLayout tabContribute;
    private ViewPager viewPagerOfContribution;
    private List<Fragment> fraList;
    private List<String>cTitle;
    private ContributionViewpagerAdapter contributeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        init();

    }
    //初始化
    private void init() {
        findView();
        initTab();
    }


    private void findView() {
        tabContribute= (TabLayout) findViewById(R.id.tabContribute);
        viewPagerOfContribution= (ViewPager) findViewById(R.id.viewPagerOfContribution);
    }

    //数据初始化
    public void initTab(){
        cTitle= Arrays.asList("场贡献榜","周贡献榜");
        fraList=new ArrayList<>();
        mContributionFragment=new ContributionFragment();

        fraList.add(mContributionFragment);
        fraList.add(mContributionFragment);
        contributeAdapter=new ContributionViewpagerAdapter(getSupportFragmentManager(),fraList,cTitle);
        viewPagerOfContribution.setAdapter(contributeAdapter);
        tabContribute.setupWithViewPager(viewPagerOfContribution);
    }

}





