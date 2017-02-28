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

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.api.RegistContract;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.presenter.RegistPresenter;
import cc.myandroid.likelive.utils.StringFormatUtil;
import cn.smssdk.SMSSDK;


/**
 * 注册视图
 */
public class RegistActivity extends BaseActivity implements View.OnClickListener, RegistContract.IRegistView {
    private ImageView backArrow;
    private EditText et_phone;
    private EditText et_sureNum;
    private Button btn_getCaptcha;
    private EditText et_pass;
    private Button btn_regist;
    private String number;
    private RegistContract.IRegistPresenter iRegistPresenter;
    private ProgressDialog dialog;

    /**
     * 设置点击发送验证码后1分钟内不能重复点击
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i > 0) {
                btn_getCaptcha.setText(i + "s");
                mHandler.sendEmptyMessageDelayed(i - 1, 1000);
            } else {
               setCaptchaPrepar();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        init();

    }

    //初始化
    private void init() {
        iRegistPresenter = new RegistPresenter(this);
        findView();
        setListener();
    }

    //设置监听
    private void setListener() {
        backArrow.setOnClickListener(this);
        btn_getCaptcha.setOnClickListener(this);
        btn_regist.setOnClickListener(this);
    }

    //找控件
    private void findView() {
        backArrow = (ImageView) findViewById(R.id.backArrow);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sureNum = (EditText) findViewById(R.id.et_sureNum);
        btn_getCaptcha = (Button) findViewById(R.id.btn_getCaptcha);
        et_pass = (EditText) findViewById(R.id.et_pass);
        btn_regist = (Button) findViewById(R.id.btn_regist);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backArrow:
                finish();
                break;
            case R.id.btn_getCaptcha:
                getCaptcha();
                break;
            case R.id.btn_regist:
                regist(et_phone.getText().toString(), et_pass.getText().toString(), et_sureNum.getText().toString());
                break;
        }
    }

    /**
     * 注册方法
     *
     * @param phoneNum 手机号码
     * @param sureNum  验证码
     */
    private void regist(String phoneNum, String password, String sureNum) {
        if (TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(sureNum) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "输入数据不能为空", Toast.LENGTH_SHORT).show();
        } else if (!StringFormatUtil.Matches(phoneNum)) {
            Toast.makeText(this, "手机号码格式不对", Toast.LENGTH_SHORT).show();
        } else {
            dialog = new ProgressDialog(this);
            dialog.setMessage("正在注册...");
            dialog.setTitle("请稍等");
            dialog.show();
            iRegistPresenter.regist(phoneNum, password, sureNum);
        }
    }

    /**
     * 注册完成回调
     */
    @Override
    public void onRegist() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(this, "注册完成", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegistActivity.this, HomeActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    /**
     * 注册失败回调
     *
     * @param message
     */
    @Override
    public void onFailed(String message) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取短信验证码
     */
    @Override
    public void getCaptcha() {
        number = et_phone.getText().toString().trim();
        iRegistPresenter.getCaptcha(number);
    }

    @Override
    public void setCaptchaPrepar() {
        btn_getCaptcha.setText("发送");
        btn_getCaptcha.setBackgroundResource(R.drawable.send);
        btn_getCaptcha.setTextColor(getResources().getColor(R.color.colorAccent));
        btn_getCaptcha.setEnabled(true);
    }

    @Override
    public void setCaptchaEnable() {
        btn_getCaptcha.setBackgroundResource(R.drawable.unsend);
        btn_getCaptcha.setTextColor(getResources().getColor(R.color.colorBlack));
        btn_getCaptcha.setEnabled(false);
        btn_getCaptcha.setText("60s");
        mHandler.sendEmptyMessageDelayed(59, 1000);
    }

    @Override
    public void onGetCaptchaSuccess() {
        Toast.makeText(this, "发送验证码成功，请注意查收", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
