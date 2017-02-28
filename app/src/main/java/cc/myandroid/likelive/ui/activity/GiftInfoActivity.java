package cc.myandroid.likelive.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.GiftAdapter;
import cc.myandroid.likelive.api.GiftContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.GetGiftResult;
import cc.myandroid.likelive.presenter.GiftInfoPresenter;

public class GiftInfoActivity extends BaseActivity implements GiftContract.IGiftInfoView {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.btn_num)
    Button btnNum;
    @BindView(R.id.btn_done)
    Button btnDone;

    private List<GetGiftResult> gifts;
    private GiftContract.IGiftInfoPresenter iGiftInfoPresenter;
    private View checkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        getWindow().setGravity(Gravity.BOTTOM);       //设置对齐
        iGiftInfoPresenter = new GiftInfoPresenter(this);
        iGiftInfoPresenter.getGiftList();
    }


    /**
     * 获得礼物信息回调方法
     *
     * @param gifts 从网络获取的礼物信息对象
     */
    @Override
    public void onGetGiftInfo(List<GetGiftResult> gifts) {
        if (gifts == null) {
            Toast.makeText(this, "网络获取失败", Toast.LENGTH_SHORT).show();
        } else {
            this.gifts =gifts;
            GiftAdapter giftAdapter = new GiftAdapter(this);
            recyclerview.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false));
            recyclerview.setAdapter(giftAdapter);
            giftAdapter.setDatas(gifts);
            giftAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, Object data) {
                    for (int i = 0; i < recyclerview.getChildCount(); i++) {
                        View view = recyclerview.getChildAt(i);
                        if (((int) view.getTag()) == position) {
                            view.setBackgroundResource(R.drawable.gift_checked_bg);
                            view.setTag(R.id.bg, "checked");//添加标记
                            checkView = view;//将选择的View保存
                        } else {
                            view.setTag(R.id.bg, "unchecked");
                            view.setBackgroundResource(R.drawable.gift_unchecked_bg);
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onGetFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.btn_pay, R.id.btn_num, R.id.btn_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pay:
                break;
            case R.id.btn_num:
                break;
            case R.id.btn_done:
                if (checkView != null && checkView.getTag(R.id.bg).equals("checked")) {
                    int postion = (int) checkView.getTag();
                    Toast.makeText(this, gifts.get(postion).getName(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "您还没有选择礼物", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
