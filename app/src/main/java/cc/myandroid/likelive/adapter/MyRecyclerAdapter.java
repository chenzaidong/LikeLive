package cc.myandroid.likelive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import cc.myandroid.likelive.R;


/**
 * Created by Administrator on 2017/2/24.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    //头部类型
    public static final int TYPE_HEADER = 0;
    //普通类型
    public static final int TYPE_NORMAL = 1;

    private List<String> mDatas ;

    private View mHeaderView;

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    // /设置头部方法
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }
    //给数据
    public void addDatas(List<String> datas) {
        mDatas=new ArrayList<>();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null)
            return TYPE_NORMAL;
        if(position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER)
            return new Holder(mHeaderView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.contribute_listitem, parent, false);
        return new Holder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        //
        if(getItemViewType(position) == TYPE_HEADER)
            return;
        final int pos = getRealPosition(viewHolder);
        final String data = mDatas.get(pos);
        if(viewHolder instanceof Holder) {
            //TODO 给数据
           /* ((Holder) viewHolder).tv_contributorName.setText();
            ((Holder) viewHolder).circleImg_portrait.set
            ((Holder) viewHolder).img_place.set
            ((Holder) viewHolder).tv_contributionCount.set*/


            if(mListener == null) return;
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos, data);
                }
            });
        }
    }
    //获取item实际位置
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        //return mHeaderView == null ? position : position - 1;
        if (mHeaderView==null){
            return position;
        }
        return position-1;
    }
    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tv_NicknameFirst;//第一名昵称
        ImageView circleImgFirst;//第一名头像
        TextView tv_FirstContriCount;//第一名贡献值
        TextView tv_level;//排名
        ImageView circleImg_portrait;//普通头像
        TextView tv_contributorName;//普通昵称
        TextView tv_contributionCount;//普通贡献值
        public Holder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView) return;
            tv_level= (TextView)itemView.findViewById(R.id.tv_level);//排名
            circleImg_portrait= (ImageView) itemView.findViewById(R.id.circleImg_portrait);//普通头像
            tv_contributorName= (TextView) itemView.findViewById(R.id.tv_contributorName);//普通昵称
            tv_contributionCount= (TextView) itemView.findViewById(R.id.tv_contributionCount);//普通贡献值
            circleImgFirst= (ImageView) itemView.findViewById(R.id.circleImgFirst);//第一名头像
            tv_NicknameFirst= (TextView) itemView.findViewById(R.id.tv_FirstNickname);//第一名昵称
            tv_FirstContriCount= (TextView) itemView.findViewById(R.id.tv_FirstContriCount);//第一名贡献值
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position, String data);
    }
}
