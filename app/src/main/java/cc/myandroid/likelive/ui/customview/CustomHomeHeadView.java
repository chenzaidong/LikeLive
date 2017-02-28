package cc.myandroid.likelive.ui.customview;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.myandroid.likelive.R;

/**
 * Created by chenzd on 2017/2/16.
 * 自定义首页头部布局
 */

public class CustomHomeHeadView extends RelativeLayout {

    @BindView(R.id.ib_search)
    ImageView ibSearch;
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.ib_edit)
    ImageView ibEdit;

    public CustomHomeHeadView(Context context) {
        this(context, null);
    }

    public CustomHomeHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_headview, this, true);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.ib_search, R.id.ib_edit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_search:
                break;
            case R.id.ib_edit:
                break;
        }
    }

    /**
     * 添加tab
     * @param tabs
     */
    public void setTab(List<String> tabs){
        int size = tabs.size();
        for (int i = 0; i < size; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabs.get(i)));
        }
    }

    /**
     * 设置TabLayout和ViewPager关联
     * @param viewPager
     */
    public void setupWithViewPager(ViewPager viewPager){
        mTabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 给搜索按钮设置监听
     *
     * @param listener
     */
    public void setSearchOnClickListener(OnClickListener listener) {
        ibSearch.setOnClickListener(listener);
    }

    /**
     * 给明星排行榜按钮设置监听
     *
     * @param listener
     */
    public void setBigStarOnClickListener(OnClickListener listener) {
        ibEdit.setOnClickListener(listener);
    }

}
