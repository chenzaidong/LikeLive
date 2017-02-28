package cc.myandroid.likelive.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseRecyclerAdapter;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.model.bean.BigStarResult;
import cc.myandroid.likelive.model.bean.Player;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.ui.activity.LoginActivity;
import cc.myandroid.likelive.utils.ImageUtil;
import cc.myandroid.likelive.utils.SPUtils;

/**
 * Created by chenzd on 2017/2/20.
 * 明星排行榜适配器 实现多布局
 */

public class BigStarAdapter extends BaseRecyclerAdapter<BigStarResult.ListBean> implements View.OnClickListener {
    private Context mContext;
    private User mUser;
    private List<Player> players;

    public BigStarAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreat(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.big_start_item, parent, false);
        User user = SPUtils.getUser();//获取当前已经登陆的user对象
        if (user != null) {
            List<User> users = DBManager.getInstance().quereUserList(user.getId());
            if (users != null) mUser = users.get(0);
            if (mUser != null) players = mUser.getPlayers();
        }
        return new MyHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int position, BigStarResult.ListBean data) {
        if (viewHolder instanceof MyHolder) {
            MyHolder holder = (MyHolder) viewHolder;
            switch (position) {
                case 0:
                    holder.tvOrder.setBackgroundResource(R.mipmap.leaderboard_anchor_first);
                    break;
                case 1:
                    holder.tvOrder.setBackgroundResource(R.mipmap.leaderboard_anchor_second);
                    break;
                case 2:
                    holder.tvOrder.setBackgroundResource(R.mipmap.leaderboard_anchor_third);
                    break;
                default:
                    holder.tvOrder.setText((position + 1) + "");
                    break;
            }
            holder.btnFocus.setOnClickListener(this);
            holder.btnFocus.setTag(data);
            holder.tvNickname.setText(data.getNickname());
            ImageUtil.laodImage(mContext, data.getIcon(), holder.ivIcon, ImageUtil.CIRCLE, 0);
            if (data.getIs_live() == 0) {
                holder.ivIsLive.setVisibility(View.GONE);
            } else {
                holder.ivIsLive.setVisibility(View.VISIBLE);
                holder.ivIsLive.setImageResource(R.mipmap.pc_live_search);
            }
            if (players != null) {
                int size = players.size();
                for (int i = 0; i < size; i++) {
                    if (players.get(i).getPlayerId().equals(data.getPlayerid())) {
                        holder.btnFocus.setText("已关注");
                        holder.btnFocus.setBackgroundResource(R.drawable.focused_bg);
                        holder.btnFocus.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (mUser == null) {
            mContext.startActivity(new Intent(mContext, LoginActivity.class));
        } else {
            Button button = (Button) v;
            BigStarResult.ListBean tag = (BigStarResult.ListBean) button.getTag();
            if (button.getText().equals("关注")) {
                Player player = new Player();
                player.setIcon(tag.getIcon());
                player.setNickname(tag.getNickname());
                player.setRoomid(tag.getRoomid());
                player.setUserId(mUser.getId());
                player.setPlayerId(tag.getPlayerid());
                DBManager.getInstance().insertPlayer(player);
                v.setBackgroundResource(R.drawable.focused_bg);
                button.setText("已关注");
                button.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            } else {//取消关注
                button.setBackgroundResource(R.drawable.focused_bg);
                button.setText("关注");
                DBManager.getInstance().deletePlayer(tag.getPlayerid());
                button.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
            }

        }

    }


    static class MyHolder extends Holder {
        @BindView(R.id.tv_order)
        TextView tvOrder;
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.btn_focus)
        Button btnFocus;
        @BindView(R.id.iv_isLive)
        ImageView ivIsLive;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

