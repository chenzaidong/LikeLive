package cc.myandroid.likelive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.Player;
import cc.myandroid.likelive.utils.ImageUtil;

/**
 * Created by chenzd on 2017/2/27.
 */

public class MyFocusAdapter extends BaseRecyclerAdapter<Player> {



    private Context mContext;

    public MyFocusAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreat(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.big_start_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, Player data) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        ImageUtil.laodImage(mContext, data.getIcon(), holder.ivIcon, ImageUtil.CIRCLE, 0);
        holder.tvNickname.setText(data.getNickname());
        holder.btnFocus.setVisibility(View.GONE);
    }

    static class MyViewHolder extends Holder {
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.btn_focus)
        Button btnFocus;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
