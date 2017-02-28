package cc.myandroid.likelive.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.BigStarAdapter;
import cc.myandroid.likelive.api.BigStarContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.BigStarResult;
import cc.myandroid.likelive.presenter.BigStarPresenter;

/**
 * 明星排行榜视图
 */
public class BigStarActivity extends BaseActivity implements BigStarContract.IBigStarView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private BigStarContract.IBigStarPresenter iBigStarPresenter;
    ProgressDialog dialog;
    private BigStarAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_star);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<BigStarResult.ListBean>() {
            @Override
            public void onItemClick(int position, BigStarResult.ListBean data) {
                if (data.getIs_live()==1){
                    Intent intent = new Intent(BigStarActivity.this,LiveActivity.class);
                    intent.putExtra("roomId",Integer.parseInt(data.getRoomid()));
                    intent.putExtra("video_url",data.getVideo_url());
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 初始化数据
     */
    private void init() {
        setSupportActionBar(toolbar);
        dialog = new ProgressDialog(this);
        iBigStarPresenter = new BigStarPresenter(this);
        iBigStarPresenter.getBigStarList();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new BigStarAdapter(this);
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.size_300)));
        imageView.setImageResource(R.mipmap.leaderboard_anchor_header_bg);
        mAdapter.setHearderView(imageView);
        recyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onGetBigStarList(BigStarResult data) {
        mAdapter.setDatas(data.getList());
    }

    @Override
    public void showProgress() {
        dialog.setTitle("请稍等");
        dialog.setMessage("正在加载数据...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    public void hideProgress() {
        if (dialog.isShowing()) dialog.dismiss();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iBigStarPresenter.onStop();
    }

    @OnClick(R.id.toolbar)
    public void onClick() {
        finish();
    }
}
