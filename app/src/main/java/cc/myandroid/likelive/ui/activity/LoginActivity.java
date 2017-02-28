package cc.myandroid.likelive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.api.LoginContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.presenter.LoginPresenter;

/**
 * 登陆视图
 */
public class LoginActivity extends BaseActivity implements LoginContract.ILoginView,View.OnClickListener {

    LoginContract.ILoginPresenter iLoginPresenter;
    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private TextView tv_regist;
    private TextView tv_forgetPass;
    private ImageView QQ_Login;
    private ImageView weibo_Login;
    private  ImageView img_back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }
    //初始化
    private void init() {
        ButterKnife.bind(this);
        iLoginPresenter=new LoginPresenter(this);
        findView();
        setListener();
    }

    private void setListener() {
        btn_login.setOnClickListener(this);
        tv_regist.setOnClickListener(this);
        tv_forgetPass.setOnClickListener(this);
        QQ_Login.setOnClickListener(this);
        weibo_Login.setOnClickListener(this);
        img_back1.setOnClickListener(this);
    }

    //找控件
    private void findView() {
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        tv_regist= (TextView) findViewById(R.id.tv_regist);
        tv_forgetPass= (TextView) findViewById(R.id.tv_forgetPass);
        QQ_Login= (ImageView) findViewById(R.id.QQ_Login);
        weibo_Login= (ImageView) findViewById(R.id.weibo_Login);
        img_back1= (ImageView) findViewById(R.id.img_back1);
    }

    @Override
    public void onLogin(User user) {
        Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(LoginActivity.this,"账号或密码有误",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void qqLogin() {

        
    }

    @Override
    public void weiboLogin() {

    }

    @Override
    public void toRegist() {
        startActivity(new Intent(LoginActivity.this,RegistActivity.class));
    }

    @Override
    public void findPassword() {
        startActivity(new Intent(LoginActivity.this,FindPassActivity.class));
    }
    //点击事件

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back1:
                finish();
                break;
            case R.id.btn_login:
                iLoginPresenter.login(et_username.getText().toString(),et_password.getText().toString());
                break;
            case R.id.tv_regist:
                toRegist();
                break;
            case R.id.tv_forgetPass:
                findPassword();
                break;
            case R.id.QQ_Login:
                iLoginPresenter.qqLogin();
                break;
            case R.id.weibo_Login:
                iLoginPresenter.weiboLogin();
                break;
        }
    }
}
