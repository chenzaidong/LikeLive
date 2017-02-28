package cc.myandroid.likelive.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseActivity;

public class WeixinpayActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_money;
    private Button btn_pay;
    private ImageView img_back2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixinpay);
        init();
    }
    //初始化
    private void init() {
        findView();
        setMoney();
        setListener();
    }

    private void setListener() {
        btn_pay.setOnClickListener(this);
        img_back2.setOnClickListener(this);
    }

    //找控件
    private void findView() {
        tv_money= (TextView) findViewById(R.id.tv_money);
        btn_pay=(Button) findViewById(R.id.btn_pay);
        img_back2= (ImageView) findViewById(R.id.img_back2);
    }

    public void setMoney(){
        Intent mIntent=getIntent();
        String  money=mIntent.getStringExtra("money");
        Log.e("-----",money);
        tv_money.setText(money+".00");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_pay:
               // Toast.makeText(WeixinpayActivity.this,"支付失败",Toast.LENGTH_SHORT).show();
                payfailed();
                break;
            case R.id.img_back2:
                finish();
                break;
        }
    }
    //支付失败，返回我的
    public void payfailed(){
        Toast.makeText(WeixinpayActivity.this,"支付失败",Toast.LENGTH_SHORT).show();
        finish();
    }
}
