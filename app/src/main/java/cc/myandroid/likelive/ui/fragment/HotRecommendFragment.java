package cc.myandroid.likelive.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.HotRecommendAdapter;
import cc.myandroid.likelive.api.HotRecommendContract;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.BannerResult;
import cc.myandroid.likelive.model.bean.HotRecommendResult;
import cc.myandroid.likelive.presenter.HotRecommendPresenter;
import cc.myandroid.likelive.ui.activity.LiveActivity;
import cc.myandroid.likelive.ui.customview.CustomBannerView;

/**
 * Created by chenzd on 2017/2/22.
 */

public class HotRecommendFragment extends Fragment implements HotRecommendContract.IHotRecommendView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private int page =1;
    private static final int PAGE_SIZE=20;
    private Context context;
    private HotRecommendContract.IHotRecommendPresenter iHotRecommendPresenter;
    private HotRecommendAdapter mAdapter;
    /**
     * 初始化视图
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_star_frament, container, false);
        ButterKnife.bind(this, view);
        recyclerview.setLayoutManager(new GridLayoutManager(context,2));
        return view;
    }

    /**
     * 数据绑定
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }

    /**
     * 初始化数据
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iHotRecommendPresenter = new HotRecommendPresenter(this);
        iHotRecommendPresenter.getHotRecommend(page,PAGE_SIZE);//请求网络获取数据最新推荐直播数据
        iHotRecommendPresenter.getBanner();//请求网络 获取广告条信息
        mAdapter = new HotRecommendAdapter(context);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HotRecommendResult.ListBean>() {
            @Override
            public void onItemClick(int position, HotRecommendResult.ListBean data) {
                Intent intent = new Intent(context,LiveActivity.class);
                intent.putExtra("roomId",Integer.parseInt(data.getRoomid()));
                intent.putExtra("video_url",data.getVideo_url());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onGetBanner(BannerResult data) {
        //有数据才显示广告条
        if (data != null&&data.getList()!=null&&data.getList().size()>0) {
            CustomBannerView customBannerView = new CustomBannerView(context);
            if (customBannerView.hasLive(data)){
                customBannerView.setData(data);
                mAdapter.setHearderView(customBannerView);
            }
        }
    }

    @Override
    public void onGetHotRecommend(HotRecommendResult data) {
        mAdapter.setDatas(data.getList());
    }

    /**
     * 显示错误提示
     * @param message
     */
    @Override
    public void onGetFailed(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
