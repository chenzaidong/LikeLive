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
import cc.myandroid.likelive.adapter.TodayStarAdapter;
import cc.myandroid.likelive.api.TodayStarContract;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.TodayStarResult;
import cc.myandroid.likelive.presenter.TodayStarPresenter;
import cc.myandroid.likelive.ui.activity.LiveActivity;

/**
 * 最新直播 fragment
 */
public class TodayStarFrament extends Fragment implements TodayStarContract.ITodayStarView{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private TodayStarContract.ITodayStarPresenter iTodayStarPresenter;
    private Context context;
    private int page =1;
    private static final int PAGE_SIZE=20;
    private TodayStarAdapter mTodayStarAdapter;
    public TodayStarFrament() {
        iTodayStarPresenter = new TodayStarPresenter(this);
    }

    /**
     * 初始化视图
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_star_frament, container, false);
        ButterKnife.bind(this, view);
        recyclerview.setLayoutManager(new GridLayoutManager(context,2));
        return view;
    }

    /**
     * 网络获取数据结果的回调方法
     * @param data
     */
    @Override
    public void onGetTodayStarList(TodayStarResult data) {
        mTodayStarAdapter.setDatas(data.getList());
    }

    /**
     * 视图和数据绑定
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerview.setAdapter(mTodayStarAdapter);
        mTodayStarAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<TodayStarResult.ListBean>() {
            @Override
            public void onItemClick(int position, TodayStarResult.ListBean data) {
                Intent intent = new Intent(context,LiveActivity.class);
                intent.putExtra("roomId",Integer.parseInt(data.getRoomid()));
                intent.putExtra("video_url",data.getVideo_url());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iTodayStarPresenter.getTodayStarList(page,PAGE_SIZE);//开始请求网络获取数据
        mTodayStarAdapter = new TodayStarAdapter(context);
    }

    /**
     * 网络获取失败回调方法
     * @param message
     */
    @Override
    public void onGetFailed(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
    }
}
