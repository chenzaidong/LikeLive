package cc.myandroid.likelive.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.adapter.MyFocusAdapter;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.ui.activity.LoginActivity;
import cc.myandroid.likelive.ui.activity.RegistActivity;
import cc.myandroid.likelive.utils.SPUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FocusFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.rl_login)
    RelativeLayout rlLogin;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private Button btn_loginInFocus;
    private TextView tv_registInFocus;
    private Context mContext;
    private User user;
    private MyFocusAdapter mAdapter;

    public FocusFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_focus, container, false);
        btn_loginInFocus = (Button) view.findViewById(R.id.btn_loginInFocus);
        tv_registInFocus = (TextView) view.findViewById(R.id.tv_registInFocus);
        setListener();
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {//显示的时候
            getUserInfo();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MyFocusAdapter(mContext);
        recyclerview.setAdapter(mAdapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    private void getUserInfo() {
        user = SPUtils.getUser();//获取当前登陆用户
        if (user == null) {
            recyclerview.setVisibility(View.GONE);
            rlLogin.setVisibility(View.VISIBLE);
        } else {
            recyclerview.setVisibility(View.VISIBLE);
            rlLogin.setVisibility(View.GONE);
            List<User> userList = DBManager.getInstance().quereUserList(user.getId());
            mAdapter.setDatas(userList.get(0).getPlayers());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }


    //设监听
    private void setListener() {
        btn_loginInFocus.setOnClickListener(this);
        tv_registInFocus.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_loginInFocus:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.tv_registInFocus:
                startActivity(new Intent(mContext, RegistActivity.class));
                break;
        }
    }
}
