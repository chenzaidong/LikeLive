package cc.myandroid.likelive.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.ui.activity.LoginActivity;
import cc.myandroid.likelive.ui.activity.MyActivityOptions;
import cc.myandroid.likelive.ui.activity.MyActivityPersonalCenter;
import cc.myandroid.likelive.ui.activity.WeixinpayActivity;
import cc.myandroid.likelive.utils.ImageUtil;
import cc.myandroid.likelive.utils.SPUtils;

/**
 * Created by mingshan on 2017/2/16.
 * 我的 视图界面
 */

public class MyFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout voucher_center, personal_center, options_center;
    private Button btn_login;
    private Context mContext;
    private Button btnA, btnB, btnC, btnD, btnE, btnF, btnG;
    private String money;
    private AlertDialog alertdialog;
    private TextView tvNickName;
    private ImageView ivHeader;
    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_fragment, container, false);
        init(view);
        return view;
    }

    /**
     * 初始化
     */
    private void init(View view) {
        findView(view);
        setOnListener();
    }

    /**
     * 判断当前fragment是否隐藏
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//当显示的时候
            getUserInfo();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    //从SP文件中读取当前登陆用户信息
    private void getUserInfo() {
        user = SPUtils.getUser();//获取当前登陆用户
        if (user == null) {
            ivHeader.setImageResource(R.mipmap.login_icon);
            btn_login.setVisibility(View.VISIBLE);
            tvNickName.setVisibility(View.GONE);
        } else {
            if (TextUtils.isEmpty(user.getIcon())) {
                ivHeader.setImageResource(R.mipmap.login_icon);
            } else {
                ImageUtil.laodImage(mContext, user.getIcon(), ivHeader, ImageUtil.CIRCLE, 0);
            }
            btn_login.setVisibility(View.GONE);
            tvNickName.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(user.getNickname())) {
                tvNickName.setText("未设置用户名");
            } else {
                tvNickName.setText(user.getNickname());
            }
        }
    }

    /**
     * 设置监听器
     */
    private void setOnListener() {
        voucher_center.setOnClickListener(this);
        personal_center.setOnClickListener(this);
        options_center.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    /**
     * 找控件
     */
    private void findView(View view) {
        voucher_center = (RelativeLayout) view.findViewById(R.id.voucher_center);
        personal_center = (RelativeLayout) view.findViewById(R.id.personal_center);
        options_center = (RelativeLayout) view.findViewById(R.id.options_center);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        tvNickName = (TextView) view.findViewById(R.id.tv_nickname);
        ivHeader = (ImageView) view.findViewById(R.id.iv_header);
    }

    /**
     * 设置点击
     *
     * @param
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voucher_center://充值中心
                charge();
                break;
            case R.id.options_center://设置中心
                startActivity(new Intent(mContext, MyActivityOptions.class));
                break;
            case R.id.personal_center://个人中心
                user = SPUtils.getUser();
                if (user == null) {//如果没有登陆则打开登陆界面
                    startActivity(new Intent(mContext, LoginActivity.class));
                } else {
                    startActivity(new Intent(mContext, MyActivityPersonalCenter.class));
                }
                break;
            case R.id.btn_login://点击登录
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.buttonA:
                money = btnA.getText().toString();
                alertdialog.dismiss();
                UpdateMoney();
                break;
            case R.id.buttonB:
                money = btnB.getText().toString();
                UpdateMoney();
                alertdialog.dismiss();
                break;
            case R.id.buttonC:
                money = btnC.getText().toString();
                UpdateMoney();
                alertdialog.dismiss();
                break;
            case R.id.buttonD:
                money = btnD.getText().toString();
                UpdateMoney();
                alertdialog.dismiss();
                break;
            case R.id.buttonE:
                money = btnE.getText().toString();
                UpdateMoney();
                alertdialog.dismiss();
                break;
            case R.id.buttonF:
                money = btnF.getText().toString();
                UpdateMoney();
                alertdialog.dismiss();
                break;
            case R.id.buttonG:
                money = btnG.getText().toString();
                UpdateMoney();
                alertdialog.dismiss();
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    //打开充值对话框
    public void charge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View dialogview = getActivity().getLayoutInflater().inflate(R.layout.activity_pay_center, null);
        builder.setView(dialogview);
        alertdialog = builder.create();
        alertdialog.show();

        btnA = (Button) dialogview.findViewById(R.id.buttonA);
        btnB = (Button) dialogview.findViewById(R.id.buttonB);
        btnC = (Button) dialogview.findViewById(R.id.buttonC);
        btnD = (Button) dialogview.findViewById(R.id.buttonD);
        btnE = (Button) dialogview.findViewById(R.id.buttonE);
        btnF = (Button) dialogview.findViewById(R.id.buttonF);
        btnG = (Button) dialogview.findViewById(R.id.buttonG);


        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnE.setOnClickListener(this);
        btnF.setOnClickListener(this);
        btnG.setOnClickListener(this);
    }

    //跳转到支付页面
    public void UpdateMoney() {
        Intent intent = new Intent();
        intent.putExtra("money", money);
        intent.setClass(getContext(), WeixinpayActivity.class);
        startActivity(intent);
    }
}
