package cc.myandroid.likelive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.GetGiftResult;

/**
 * Created by chenzd on 2017/2/21.
 */

public class GiftAdapter extends BaseRecyclerAdapter<GetGiftResult> {

    private Context context;

    public GiftAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreat(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gift_item_layout, parent, false);
        AutoUtils.auto(view);
        return new MyHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, GetGiftResult data) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.itemView.setTag(realPosition);
        myHolder.itemView.setTag(R.id.bg,"unchecked");
        Glide.with(context).load(data.getUrl()).into(myHolder.ivGift);
        myHolder.tvGold.setText(String.valueOf(data.getCoin()));
        myHolder.tvName.setText(data.getName());
        myHolder.itemView.setBackgroundResource(R.drawable.gift_unchecked_bg);
    }

    static  class MyHolder extends Holder {
        @BindView(R.id.iv_gift)
        ImageView ivGift;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_gold)
        TextView tvGold;
        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
