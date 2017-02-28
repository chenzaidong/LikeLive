package cc.myandroid.likelive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.SearchAdapter;
import cc.myandroid.likelive.adapter.SearchReusltAdapter;
import cc.myandroid.likelive.api.SearchContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.SearchHotRecommendResult;
import cc.myandroid.likelive.model.bean.SearchPlayerResult;
import cc.myandroid.likelive.presenter.SearchPresenter;

public class SearchActivity extends BaseActivity implements SearchContract.ISearchView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ic_delete)
    ImageView icDelete;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private SearchAdapter mAdapter;
    private SearchContract.ISearchPresenter iSearchPresenter;
    private SearchReusltAdapter reusltAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void setListener() {
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SearchHotRecommendResult>() {
            @Override
            public void onItemClick(int position, SearchHotRecommendResult data) {
                if (data.getIs_live()==1){
                    Intent intent = new Intent(SearchActivity.this,LiveActivity.class);
                    intent.putExtra("roomId",Integer.parseInt(data.getRoomid()));
                    intent.putExtra("video_url",data.getVideo_url());
                    startActivity(intent);
                }
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    try {
                        iSearchPresenter.search(v.getText().toString());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    private void init() {
        mAdapter = new SearchAdapter(this);
        reusltAdapter = new SearchReusltAdapter(this);
        iSearchPresenter = new SearchPresenter(this);
        iSearchPresenter.getSearchHotRecommend();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onGetSearchHotRecommend(List<SearchHotRecommendResult> data) {
        mAdapter.setDatas(data);
        View headerView = getLayoutInflater().inflate(R.layout.search_item_header_layout,null);
        headerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.size_100)));
        mAdapter.setHearderView(headerView);
    }

    @Override
    public void foucs() {

    }

    @Override
    public void unFoucs() {

    }

    @Override
    public void onSearch(List<SearchPlayerResult> data) {
        if (data!=null){
            mAdapter.setHearderView(null);
            recyclerview.setAdapter(reusltAdapter);
            reusltAdapter.setDatas(data);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        iSearchPresenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick({R.id.iv_back, R.id.ic_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ic_delete:
                etSearch.setText("");
                break;
        }
    }


}
