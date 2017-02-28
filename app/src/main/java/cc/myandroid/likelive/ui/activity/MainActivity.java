package cc.myandroid.likelive.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }
        }.sendEmptyMessageDelayed(0,2000);
    }
}
