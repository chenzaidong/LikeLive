package cc.myandroid.likelive.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.utils.L;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.api.RoomInfoContract;
import cc.myandroid.likelive.api.RoomInfoContract.IRoomInfoView;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.listener.SoftKeyBoardListener;
import cc.myandroid.likelive.model.bean.CurrentTuhaoResult;
import cc.myandroid.likelive.model.bean.Player;
import cc.myandroid.likelive.model.bean.RoomInfoResult;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.model.bean.WeekTuhaoResult;
import cc.myandroid.likelive.presenter.RoomInfoPresenter;
import cc.myandroid.likelive.ui.activity.LoginActivity;
import cc.myandroid.likelive.ui.activity.MessageAdapter;
import cc.myandroid.likelive.ui.customview.CustomMarqueeTextView;
import cc.myandroid.likelive.ui.customview.CustomRoundView;
import cc.myandroid.likelive.ui.customview.MagicTextView;
import cc.myandroid.likelive.utils.DisplayUtil;
import cc.myandroid.likelive.utils.ImageUtil;
import cc.myandroid.likelive.utils.SPUtils;
import cc.myandroid.likelive.utils.StringFormatUtil;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by chenzd on 2017/2/28.
 * 该Fragment是用于dialogFragment中的pager，为了实现滑动隐藏交互Fragment的
 * 交互的操作都在这个界面实现的
 */

public class LayerFragment extends Fragment implements View.OnClickListener, IRoomInfoView {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
            }
        }
    };

    /**
     * 界面相关
     */
    private LinearLayout llgiftcontent;
    private ListView lvmessage;
    private ImageView tvSendtwo;
    private ImageView tvSendthree;
    private ImageView tvSendfor;
    private EditText etInput;
    private ImageView tvChat;
    private TextView sendInput;
    private LinearLayout llInputParent;

    /**
     * 动画相关
     */
    private NumAnim giftNumAnim;
    private TranslateAnimation inAnim;
    private TranslateAnimation outAnim;
    /**
     * 数据相关
     */
    private List<View> giftViewCollection = new ArrayList<>();
    private List<String> messageData = new LinkedList<>();
    private MessageAdapter messageAdapter;
    private RoomInfoContract.IRoomInfoPresenter iRoomInfoPresenter;
    private RoomInfoResult data;
    private TextView tvPlayerName;
    private TextView tvRoomUserNum;
    private ImageView ivHeader;
    private Context mContext;
    private CustomMarqueeTextView tvShareInfo;
    private User mUser;
    private ImageView ivFocus;
    private ImageView ivShare;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layer, container, false);
        findView(view);
        setListener();
        inAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.gift_in);
        outAnim = (TranslateAnimation) AnimationUtils.loadAnimation(getActivity(), R.anim.gift_out);
        giftNumAnim = new NumAnim();
        clearTiming();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iRoomInfoPresenter = new RoomInfoPresenter(this);
        int roomId = getArguments().getInt("roomId");
        iRoomInfoPresenter.getRoomInfo(roomId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = getActivity();
        mUser = SPUtils.getUser();
    }

    //设置监听
    private void setListener() {
        tvChat.setOnClickListener(this);
        tvSendtwo.setOnClickListener(this);
        tvSendthree.setOnClickListener(this);
        tvSendfor.setOnClickListener(this);
        sendInput.setOnClickListener(this);
        ivFocus.setOnClickListener(this);
        ivShare.setOnClickListener(this);
    }

    //查询控件
    private void findView(View view) {
        llgiftcontent = (LinearLayout) view.findViewById(R.id.llgiftcontent);
        lvmessage = (ListView) view.findViewById(R.id.lvmessage);
        tvChat = (ImageView) view.findViewById(R.id.tvChat);
        tvSendtwo = (ImageView) view.findViewById(R.id.tvSendtwo);
        tvSendthree = (ImageView) view.findViewById(R.id.tvSendthree);
        tvSendfor = (ImageView) view.findViewById(R.id.tvSendfor);
        llInputParent = (LinearLayout) view.findViewById(R.id.llinputparent);
        etInput = (EditText) view.findViewById(R.id.etInput);
        sendInput = (TextView) view.findViewById(R.id.sendInput);
        tvPlayerName = (TextView) view.findViewById(R.id.tv_player_name);
        tvRoomUserNum = (TextView) view.findViewById(R.id.tv_room_user_num);
        ivHeader = (ImageView) view.findViewById(R.id.iv_icon);
        tvShareInfo = (CustomMarqueeTextView) view.findViewById(R.id.tv_share_description);
        ivFocus = (ImageView) view.findViewById(R.id.iv_focus);
        ivShare = (ImageView) view.findViewById(R.id.iv_share);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llInputParent.getVisibility() == View.VISIBLE) {
                    tvChat.setVisibility(View.VISIBLE);
                    llInputParent.setVisibility(View.GONE);
                    hideKeyboard();
                }
            }
        });
        softKeyboardListnenr();
        for (int x = 0; x < 20; x++) {
            messageData.add("Johnny: 默认聊天内容" + x);
        }
        messageAdapter = new MessageAdapter(getActivity(), messageData);
        lvmessage.setAdapter(messageAdapter);
        lvmessage.setSelection(messageData.size());
    }

    @Override
    public void onResume() {
        super.onResume();
        mUser = SPUtils.getUser();
    }

    @Override
    public void onClick(View v) {
        if (mUser == null) {
            startActivity(new Intent(mContext, LoginActivity.class));
        } else {
            switch (v.getId()) {
                case R.id.tvChat:/*聊天*/
                    showChat();
                    break;
                case R.id.sendInput:/*发送*/
                    sendText();
                    break;
                case R.id.tvSendtwo:/*礼物2*/
                    showGift("吻", 2);
                    break;
                case R.id.tvSendthree:/*礼物3*/
                    showGift("巧克力", 3);
                    break;
                case R.id.tvSendfor:/*礼物4*/
                    showGift("礼物", 4);
                    break;
                case R.id.iv_focus:/*关注*/
                    focus();
                    break;
                case R.id.iv_share:
                    share();
                    break;
            }
        }
    }

    /**
     * 添加礼物view,(考虑垃圾回收)
     */
    private View addGiftView() {
        View view = null;
        if (giftViewCollection.size() <= 0) {
            /*如果垃圾回收中没有view,则生成一个*/
            view = LayoutInflater.from(getActivity()).inflate(R.layout.item_gift, null);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.topMargin = 10;
            view.setLayoutParams(lp);
            llgiftcontent.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View view) {
                }

                @Override
                public void onViewDetachedFromWindow(View view) {
                    giftViewCollection.add(view);
                }
            });
        } else {
            view = giftViewCollection.get(0);
            giftViewCollection.remove(view);
        }
        return view;
    }

    /**
     * 删除礼物view
     */
    private void removeGiftView(final int index) {
        final View removeView = llgiftcontent.getChildAt(index);
        outAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                llgiftcontent.removeViewAt(index);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                removeView.startAnimation(outAnim);
            }
        });
    }

    /**
     * 显示礼物的方法
     */
    private void showGift(String tag, int type) {
        View giftView = llgiftcontent.findViewWithTag(tag);
        if (giftView == null) {/*该用户不在礼物显示列表*/
            if (llgiftcontent.getChildCount() > 2) {/*如果正在显示的礼物的个数超过两个，那么就移除最后一次更新时间比较长的*/
                View giftView1 = llgiftcontent.getChildAt(0);
                CustomRoundView picTv1 = (CustomRoundView) giftView1.findViewById(R.id.crvheadimage);
                long lastTime1 = (Long) picTv1.getTag();
                View giftView2 = llgiftcontent.getChildAt(1);
                CustomRoundView picTv2 = (CustomRoundView) giftView2.findViewById(R.id.crvheadimage);
                long lastTime2 = (Long) picTv2.getTag();
                if (lastTime1 > lastTime2) {/*如果第二个View显示的时间比较长*/
                    removeGiftView(1);
                } else {/*如果第一个View显示的时间长*/
                    removeGiftView(0);
                }
            }

            giftView = addGiftView();/*获取礼物的View的布局*/
            giftView.setTag(tag);/*设置view标识*/

            CustomRoundView crvheadimage = (CustomRoundView) giftView.findViewById(R.id.crvheadimage);
            CustomRoundView ivGift = (CustomRoundView) giftView.findViewById(R.id.ivgift);
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.logo);
            if (mUser.getIcon() != null) {
                bitmap = BitmapFactory.decodeFile(mUser.getIcon());
            }
            crvheadimage.setImageBitmap(bitmap);
            switch (type) {
                case 2:
                    ivGift.setImageResource(R.mipmap.kiss);
                    break;
                case 3:
                    ivGift.setImageResource(R.mipmap.chocolate);
                    break;
                case 4:
                    ivGift.setImageResource(R.mipmap.room_gift_entrance);
                    break;
            }
            TextView nickname = (TextView) giftView.findViewById(R.id.tv_nickname);
            nickname.setText(mUser.getNickname());

            final MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
            giftNum.setText("x1");/*设置礼物数量*/
            crvheadimage.setTag(System.currentTimeMillis());/*设置时间标记*/
            giftNum.setTag(1);/*给数量控件设置标记*/

            llgiftcontent.addView(giftView);/*将礼物的View添加到礼物的ViewGroup中*/
            llgiftcontent.invalidate();/*刷新该view*/
            giftView.startAnimation(inAnim);/*开始执行显示礼物的动画*/
            inAnim.setAnimationListener(new Animation.AnimationListener() {/*显示动画的监听*/
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    giftNumAnim.start(giftNum);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        } else {/*该用户在礼物显示列表*/
            CustomRoundView crvheadimage = (CustomRoundView) giftView.findViewById(R.id.crvheadimage);/*找到头像控件*/
            Bitmap bitmap = BitmapFactory.decodeFile(mUser.getIcon());
            crvheadimage.setImageBitmap(bitmap);
            MagicTextView giftNum = (MagicTextView) giftView.findViewById(R.id.giftNum);/*找到数量控件*/
            int showNum = (Integer) giftNum.getTag() + 1;
            giftNum.setText("x" + showNum);
            giftNum.setTag(showNum);
            crvheadimage.setTag(System.currentTimeMillis());
            giftNumAnim.start(giftNum);
        }
    }

    /**
     * 显示聊天布局
     */
    private void showChat() {
        tvChat.setVisibility(View.GONE);
        llInputParent.setVisibility(View.VISIBLE);
        llInputParent.requestFocus();
        showKeyboard();
    }

    /**
     * 发送消息
     */
    private void sendText() {
        if (!etInput.getText().toString().trim().isEmpty()) {
            messageData.add("Johnny: " + etInput.getText().toString().trim());
            etInput.setText("");
            messageAdapter.NotifyAdapter(messageData);
            lvmessage.setSelection(messageData.size());
            hideKeyboard();
        } else
            hideKeyboard();
    }


    /**
     * 显示软键盘并因此头布局
     */
    private void showKeyboard() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(etInput, InputMethodManager.SHOW_FORCED);
            }
        }, 100);
    }

    /**
     * 隐藏软键盘并显示头布局
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etInput.getWindowToken(), 0);
    }

    /**
     * 软键盘显示与隐藏的监听
     */
    private void softKeyboardListnenr() {
        SoftKeyBoardListener.setListener(getActivity(), new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {/*软键盘显示：执行隐藏title动画，并修改listview高度和装载礼物容器的高度*/
                dynamicChangeListviewH(100);
                dynamicChangeGiftParentH(true);
            }

            @Override
            public void keyBoardHide(int height) {/*软键盘隐藏：隐藏聊天输入框并显示聊天按钮，执行显示title动画，并修改listview高度和装载礼物容器的高度*/
                tvChat.setVisibility(View.VISIBLE);
                llInputParent.setVisibility(View.GONE);
                dynamicChangeListviewH(150);
                dynamicChangeGiftParentH(false);
            }
        });
    }

    /**
     * 动态的修改listview的高度
     *
     * @param heightPX
     */
    private void dynamicChangeListviewH(int heightPX) {
        ViewGroup.LayoutParams layoutParams = lvmessage.getLayoutParams();
        layoutParams.height = DisplayUtil.dip2px(getActivity(), heightPX);
        lvmessage.setLayoutParams(layoutParams);
    }

    /**
     * 动态修改礼物父布局的高度
     *
     * @param showhide
     */
    private void dynamicChangeGiftParentH(boolean showhide) {
        if (showhide) {/*如果软键盘显示中*/
            if (llgiftcontent.getChildCount() != 0) {
                /*判断是否有礼物显示，如果有就修改父布局高度，如果没有就不作任何操作*/
                ViewGroup.LayoutParams layoutParams = llgiftcontent.getLayoutParams();
                layoutParams.height = llgiftcontent.getChildAt(0).getHeight();
                llgiftcontent.setLayoutParams(layoutParams);
            }
        } else {/*如果软键盘隐藏中*/
            /*就将装载礼物的容器的高度设置为包裹内容*/
            ViewGroup.LayoutParams layoutParams = llgiftcontent.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            llgiftcontent.setLayoutParams(layoutParams);
        }
    }


    /**
     * 定时清除礼物
     */
    private void clearTiming() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int count = llgiftcontent.getChildCount();
                for (int i = 0; i < count; i++) {
                    View view = llgiftcontent.getChildAt(i);
                    CustomRoundView crvheadimage = (CustomRoundView) view.findViewById(R.id.crvheadimage);
                    long nowtime = System.currentTimeMillis();
                    long upTime = (Long) crvheadimage.getTag();
                    if ((nowtime - upTime) >= 3000) {
                        removeGiftView(i);
                        return;
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 3000);
    }

    @Override
    public void onGetCurrentTuhaoList(List<CurrentTuhaoResult> data) {

    }

    @Override
    public void onGetWeekTuhaoList(List<WeekTuhaoResult> data) {

    }

    @Override
    public void onGetRoomInfo(RoomInfoResult data) {
        this.data = data;
        tvPlayerName.setText(data.getPlayer_info().getNickname());
        int nums = (int) Math.floor(Math.random() * (1000 - 500 + 1) + 500);//随机参数人数
        tvRoomUserNum.setText(String.valueOf(nums));
        ImageUtil.laodImage(mContext, data.getPlayer_info().getIcon(), ivHeader, ImageUtil.CIRCLE, 0);
        tvShareInfo.setText(data.getShare_description());
        L.e(">>>" + data.getShare_description());
        if (mUser != null) {
            List<Player> list = DBManager.getInstance().queryPlayerList(data.getPlayer_info().getPlayerid());
            if (list.size() > 0) {
                ivFocus.setVisibility(View.GONE);
            } else {
                ivFocus.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void focus() {
        if (mUser == null) {
            startActivity(new Intent(mContext, LoginActivity.class));
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
                Toast.makeText(mContext, "关注成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

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
            oks.show(mContext);
        } else {
            Toast.makeText(mContext, "没有获取到房间信息", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onGetFailed(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 数字放大动画
     */
    public class NumAnim {
        private Animator lastAnimator = null;

        public void start(View view) {
            if (lastAnimator != null) {
                lastAnimator.removeAllListeners();
                lastAnimator.end();
                lastAnimator.cancel();
            }
            ObjectAnimator anim1 = ObjectAnimator.ofFloat(view, "scaleX", 1.3f, 1.0f);
            ObjectAnimator anim2 = ObjectAnimator.ofFloat(view, "scaleY", 1.3f, 1.0f);
            AnimatorSet animSet = new AnimatorSet();
            lastAnimator = animSet;
            animSet.setDuration(200);
            animSet.setInterpolator(new OvershootInterpolator());
            animSet.playTogether(anim1, anim2);
            animSet.start();
        }
    }
}