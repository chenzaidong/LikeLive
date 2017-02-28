package cc.myandroid.likelive.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.HomeViewPagerAdapter;
import cc.myandroid.likelive.ui.activity.BigStarActivity;
import cc.myandroid.likelive.ui.activity.SearchActivity;
import cc.myandroid.likelive.ui.customview.CustomHomeHeadView;

/**
 * 主页fragment
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.customhomeheadview)
    CustomHomeHeadView customhomeheadview;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private Context context;
    private HomeViewPagerAdapter mAdapter;
    private List<Fragment> datas;
    private List<String> titles;

    /**
     * 视图初始化
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 视图和数据绑定
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customhomeheadview.setBigStarOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, BigStarActivity.class));
            }
        });
        customhomeheadview.setSearchOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, SearchActivity.class));
            }
        });
        customhomeheadview.setupWithViewPager(viewpager);
        AutoUtils.auto(customhomeheadview);
        viewpager.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * 数据初始化
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titles = Arrays.asList("推荐", "最新");
        datas = new ArrayList<>();
        datas.add(new HotRecommendFragment());
        datas.add(new TodayStarFrament());
        mAdapter = new HomeViewPagerAdapter(getFragmentManager(),datas,titles);
    }
}
