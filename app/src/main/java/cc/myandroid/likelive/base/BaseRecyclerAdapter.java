package cc.myandroid.likelive.base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzd on 2017/2/20.
 * 自定义adapter抽象类，用来封装adapter中添加头部View,添加itemView事件监听的回调方法
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_TYPE_HEADER = 0;
    private static final int ITEM_TYPE_CONTENT = 1;
    private List<T> mDatas = new ArrayList<>();
    private View mHeaderView;
    private OnItemClickListener mListener;

    /**
     * 设置item的点击事件监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /**
     * 设置头部布局
     *
     * @param hearderView
     */
    public void setHearderView(View hearderView) {
        mHeaderView = hearderView;
        notifyItemInserted(0);
    }

    /**
     * 获取头部布局
     *
     * @return
     */
    public View getmHeaderView() {
        return mHeaderView;
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addDatas(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 设置数据
     *
     * @param datas
     */
    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return ITEM_TYPE_CONTENT;
        if (position == 0) return ITEM_TYPE_HEADER;
        return ITEM_TYPE_CONTENT;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null&&viewType==ITEM_TYPE_HEADER) return new Holder(mHeaderView);
        return onCreat(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==ITEM_TYPE_HEADER)return;
        final int pos = getRealPosition(holder);
        final T data = mDatas.get(pos);
        onBind(holder, pos, data);
        if (mListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(pos,data);
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? mDatas.size() : mDatas.size() + 1;
    }
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getAdapterPosition();
        return mHeaderView == null ? position : position - 1;
    }
    public abstract RecyclerView.ViewHolder onCreat(ViewGroup parent, final int viewType);
    public abstract void onBind(RecyclerView.ViewHolder viewHolder, int realPosition, T data);

    public static class Holder extends RecyclerView.ViewHolder{
        public Holder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }
    }
    public interface OnItemClickListener<T> {
        void onItemClick(int position, T data);
    }
}
