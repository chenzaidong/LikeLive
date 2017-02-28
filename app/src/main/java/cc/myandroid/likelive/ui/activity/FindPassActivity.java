package cc.myandroid.likelive.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.api.FindPassContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.presenter.FindPassPresenter;
import cc.myandroid.likelive.utils.StringFormatUtil;
import cn.smssdk.SMSSDK;

public class FindPassActivity extends BaseActivity implements View.OnClickListener,FindPassContract.IFindPassView {
    private ImageView img_backF;
    private EditText et_phoneNum;
    private EditText et_yzm;
    private EditText et_newPass;
    private Button btn_Captcha;
    private Button btn_forgetLogin;
    private ProgressDialog dialog;
    private String number;
    private FindPassContract.IFindPassPresenter iFindPassPresenter;
    /**
     * 设置点击发送验证码后1分钟内不能重复点击
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i > 0) {
                btn_Captcha.setText(i + "s");
                mHandler.sendEmptyMessageDelayed(i - 1, 1000);
            } else {
                setCaptchaPrepar();
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        init();

    }
//初始化
    private void init() {
        iFindPassPresenter=new FindPassPresenter(this);
        findView();
        setListener();
    }
//设置监听
    private void setListener() {
        img_backF.setOnClickListener(this);
        btn_Captcha.setOnClickListener(this);
        btn_forgetLogin.setOnClickListener(this);
    }
//找控件
    private void findView() {
        img_backF= (ImageView) findViewById(R.id.img_backF);
        et_phoneNum= (EditText) findViewById(R.id.et_phoneNum);
        et_yzm= (EditText) findViewById(R.id.et_yzm);
        et_newPass= (EditText) findViewById(R.id.et_newPass);
        btn_Captcha= (Button) findViewById(R.id.btn_Captcha);
        btn_forgetLogin= (Button) findViewById(R.id.btn_forgetLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_backF:
                finish();
                break;
            case R.id.btn_Captcha:
                getCaptcha();
                break;
            case R.id.btn_forgetLogin:
                changePassword(et_phoneNum.getText().toString(),et_newPass.getText().toString(),et_yzm.getText().toString());
                break;
        }
    }


    private void changePassword(String phoneNum, String password, String sureNum) {
        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(sureNum) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "输入数据不能为空", Toast.LENGTH_SHORT).show();
        } else if (!StringFormatUtil.Matches(phoneNum)) {
            Toast.makeText(this, "手机号码格式不对", Toast.LENGTH_SHORT).show();
        } else {
            dialog = new ProgressDialog(this);
            dialog.setMessage("正在修改密码...");
            dialog.setTitle("请稍等");
            dialog.show();
            iFindPassPresenter.changePass(phoneNum,password,sureNum);
            //// TODO: 2017/2/27 更新密码

        }
    }

    @Override
    public void onChangeSuccess() {
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
        Toast.makeText(FindPassActivity.this,"密码修改完成",Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(FindPassActivity.this,LoginActivity.class));
    }

    @Override
    public void onChangeFailed(String message) {
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
        Toast.makeText(FindPassActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCaptcha() {
        number=et_phoneNum.getText().toString().trim();
        iFindPassPresenter.getCaptcha(number);
    }

    @Override
    public void setCaptchaPrepar() {
        btn_Captcha.setText("发送");
        btn_Captcha.setBackgroundResource(R.drawable.send);
        btn_Captcha.setTextColor(getResources().getColor(R.color.colorAccent));
        btn_Captcha.setEnabled(true);
    }

    @Override
    public void setCaptchaEnable() {
        btn_Captcha.setBackgroundResource(R.drawable.unsend);
        btn_Captcha.setTextColor(getResources().getColor(R.color.colorBlack));
        btn_Captcha.setEnabled(false);
        btn_Captcha.setText("60s");
        mHandler.sendEmptyMessageDelayed(59, 1000);
        btn_Captcha.setEnabled(false);
    }

    @Override
    public void onGetCaptchaSuccess() {
        Toast.makeText(FindPassActivity.this,"验证码发送成功，请注意查收",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
