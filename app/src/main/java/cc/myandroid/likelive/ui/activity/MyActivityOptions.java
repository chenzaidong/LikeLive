package cc.myandroid.likelive.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alanapi.switchbutton.SwitchButton;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.utils.ImageCatchUtil;
import cc.myandroid.likelive.utils.SPUtils;

public class MyActivityOptions extends BaseActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    private SwitchButton mSwitchButton;
    private TextView tvLogout;
    private RelativeLayout rl_clean;
    private TextView tvCatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__options);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        findView();
        setOnListener();
    }

    /**
     * 设置监听器
     */
    private void setOnListener() {
        mSwitchButton.setOnCheckedChangeListener(this);
        tvLogout.setOnClickListener(this);
        rl_clean.setOnClickListener(this);
    }

    /**
     * 找到控件
     */
    private void findView() {
        mSwitchButton = (SwitchButton) findViewById(R.id.mSwitchButton);
        rl_clean = (RelativeLayout) findViewById(R.id.clear_away);
        tvCatch = (TextView) findViewById(R.id.tv_cache);
        tvCatch.setText(ImageCatchUtil.getInstance().getCacheSize());
        tvLogout = (TextView) findViewById(R.id.tv_logout);

        if (SPUtils.getUser()==null){
            tvLogout.setVisibility(View.GONE);
        }else {
            tvLogout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置开关显示颜色的变换
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Toast.makeText(this, "打开", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "关闭", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 返回图片事件
     *
     * @param view
     */
    public void onClickBack(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_logout:
                SPUtils.deleteUser();
                finish();
                break;
            case R.id.clear_away:
                ImageCatchUtil.getInstance().clearImageAllCache(new Callback<String>() {
                    @Override
                    public void success(String data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvCatch.setText(ImageCatchUtil.getInstance().getCacheSize());
                                Toast.makeText(MyActivityOptions.this, "清除完成", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    @Override
                    public void failed(String result) {

                    }
                });
                break;
        }
    }
}
