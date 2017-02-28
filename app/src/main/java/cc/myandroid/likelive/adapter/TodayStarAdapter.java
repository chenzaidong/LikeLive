package cc.myandroid.likelive.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.model.bean.TodayStarResult;
import cc.myandroid.likelive.utils.ImageUtil;

/**
 * Created by chenzd on 2017/2/22.
 */

public class TodayStarAdapter extends BaseRecyclerAdapter<TodayStarResult.ListBean> {


    private Context context;
    private LayoutInflater inflater;

    public TodayStarAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreat(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.new_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, TodayStarResult.ListBean data) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        holder.tvTitle.setText(data.getNickname());
        ImageUtil.laodImage(context,data.getPlayer_image(),holder.imgHeadPortrait,ImageUtil.ROUND,20);
        holder.sexBelle.setText(data.getTag().get(0));
        if (data.getIs_live()==1){
            holder.imgTelMode.setVisibility(View.VISIBLE);
            if (data.getPlatform()==0){
                holder.imgTelMode.setImageResource(R.mipmap.recom_pc_live);
            }else {
                holder.imgTelMode.setImageResource(R.mipmap.recom_mobile_live);
            }
        }else {
            holder.imgTelMode.setVisibility(View.GONE);
        }
    }

    static  class MyViewHolder extends Holder {
        @BindView(R.id.img_head_portrait)
        ImageView imgHeadPortrait;
        @BindView(R.id.img_tel_mode)
        ImageView imgTelMode;
        @BindView(R.id.sex_belle)
        TextView sexBelle;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
