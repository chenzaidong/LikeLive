package cc.myandroid.likelive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.cyberplayer.core.BVideoView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.api.RoomInfoContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.model.bean.CurrentTuhaoResult;
import cc.myandroid.likelive.model.bean.Player;
import cc.myandroid.likelive.model.bean.RoomInfoResult;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.model.bean.WeekTuhaoResult;
import cc.myandroid.likelive.presenter.RoomInfoPresenter;
import cc.myandroid.likelive.ui.customview.CustomMarqueeTextView;
import cc.myandroid.likelive.utils.ImageUtil;
import cc.myandroid.likelive.utils.SPUtils;
import cc.myandroid.likelive.utils.StringFormatUtil;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class RoomInfoActivity extends BaseActivity implements RoomInfoContract.IRoomInfoView, View.OnClickListener {

    @BindView(R.id.baidu_video_view)
    BVideoView baiduVideoView;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_player_name)
    TextView tvPlayerName;
    @BindView(R.id.tv_room_user_num)
    TextView tvRoomUserNum;
    @BindView(R.id.ll_player_info)
    RelativeLayout llPlayerInfo;
    @BindView(R.id.tv_share_description)
    CustomMarqueeTextView tvShareDescription;
    @BindView(R.id.ll_room_info)
    LinearLayout llRoomInfo;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.iv_chat)
    ImageView ivChat;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.iv_gift)
    ImageView ivGift;
    @BindView(R.id.iv_chocolate)
    ImageView ivChocolate;
    @BindView(R.id.iv_kiss)
    ImageView ivKiss;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll_live)
    LinearLayout llLive;
    @BindView(R.id.activity_room_info)
    RelativeLayout activityRoomInfo;
    @BindView(R.id.iv_focus)
    ImageView ivFocus;
    private RoomInfoContract.IRoomInfoPresenter iRoomInfoPresenter;
    private PopupWindow mPopupWindow;
    private RoomInfoResult data;
    private User mUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_info);
        ButterKnife.bind(this);
        init();
    }

    /**
     * 数据初始化
     */
    private void init() {
        iRoomInfoPresenter = new RoomInfoPresenter(this);
        mUser = SPUtils.getUser();//获取登陆用户信息
        int roomId = getIntent().getIntExtra("roomId", -1);
        if (roomId != -1) {
            iRoomInfoPresenter.getRoomInfo(roomId);//获得房间信息
            iRoomInfoPresenter.getCurrentTuhaoList(roomId);//获得场榜
            iRoomInfoPresenter.getWeekTuhaoList(roomId);//获得周榜
        }
        int nums = (int) Math.floor(Math.random()*(1000-500+1)+500);//随机参数人数
        tvRoomUserNum.setText(String.valueOf(nums));
    }

    /**
     * 播放直播
     *
     * @param video_url
     */
    private void startVideo(String video_url) {
        //设置视频的缩放模式
        baiduVideoView.setVideoScalingMode(BVideoView.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        /**
         * 设置解码模式 为保证兼容性，当前仅支持软解
         */
        baiduVideoView.setDecodeMode(BVideoView.DECODE_SW);
        baiduVideoView.selectResolutionType(BVideoView.RESOLUTION_TYPE_AUTO);
        baiduVideoView.setVideoPath(video_url);
        baiduVideoView.start();
    }

    @Override
    public void onGetCurrentTuhaoList(List<CurrentTuhaoResult> data) {

    }

    @Override
    public void onGetWeekTuhaoList(List<WeekTuhaoResult> data) {

    }

    /**
     * 获取房间信息回调方法
     *
     * @param data
     */
    @Override
    public void onGetRoomInfo(RoomInfoResult data) {
        this.data = data;
        if (data != null) {
            startVideo(data.getPlayer_info().getVideo_url());
            ImageUtil.laodImage(this, data.getPlayer_info().getIcon(), ivIcon, ImageUtil.CIRCLE, 0);
            tvPlayerName.setText(data.getPlayer_info().getNickname());
            tvShareDescription.setText(data.getShare_description());
            if (!data.getRefrom().equals("0")) {
                baiduVideoView.setLayoutParams(new RelativeLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            }
            if (mUser != null) {
                List<Player> list = DBManager.getInstance().queryPlayerList(data.getPlayer_info().getPlayerid());
                if (list.size() > 0) {
                    ivFocus.setVisibility(View.GONE);
                } else {
                    ivFocus.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void focus() {
        if (mUser == null) {
            startActivity(new Intent(RoomInfoActivity.this, LoginActivity.class));
        } else {
            if (data != null) {
                Player player = new Player();
                player.setIcon(data.getPlayer_info().getIcon());
                player.setNickname(data.getPlayer_info().getNickname());
                player.setRoomid(data.getRoomid());
                player.setUserId(mUser.getId());
                player.setPlayerId(data.getPlayer_info().getPlayerid());
                DBManager.getInstance().insertPlayer(player);
                ivFocus.setVisibility(View.GONE);
                Toast.makeText(this, "关注成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 分享功能
     */
    @Override
    public void share() {
        if (data != null) {
            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();
            // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
            oks.setTitle(StringFormatUtil.replace(data.getShare_title(), "酒窝", "爱直播"));
            // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
            oks.setTitleUrl(StringFormatUtil.replace(data.getShare_url(), "酒窝", "爱直播"));
            // text是分享文本，所有平台都需要这个字段
            oks.setText(StringFormatUtil.replace(data.getShare_description(), "酒窝", "爱直播"));
            //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
            oks.setImageUrl(data.getPlayer_info().getPlayer_image());
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment(StringFormatUtil.replace(data.getShare_description(), "酒窝", "爱直播"));
            // 启动分享GUI
            oks.show(this);
        } else {
            Toast.makeText(this, "没有获取到房间信息", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 网络获取数据失败
     *
     * @param message
     */
    @Override
    public void onGetFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (baiduVideoView != null && baiduVideoView.isPlaying()) {
            baiduVideoView.pause();
            baiduVideoView.stopPlayback();
        }
    }

    @OnClick({R.id.iv_icon, R.id.iv_focus, R.id.iv_chat, R.id.iv_share, R.id.iv_gift, R.id.iv_chocolate, R.id.iv_kiss})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                break;
            case R.id.iv_focus:
                focus();
                break;
            case R.id.iv_chat:
                break;
            case R.id.iv_share:
                share();
                break;
            case R.id.iv_gift:
                startActivity(new Intent(this, GiftInfoActivity.class));
                break;
            case R.id.iv_chocolate:
                break;
            case R.id.iv_kiss:
                break;
            case R.id.btn_num:
                showGiftNum();
                break;
        }
    }

    /**
     * 显示选中礼物数量界面
     */
    private void showGiftNum() {

    }
}
