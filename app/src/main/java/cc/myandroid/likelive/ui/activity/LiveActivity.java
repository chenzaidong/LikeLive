package cc.myandroid.likelive.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.ui.fragment.LiveViewFragment;
import cc.myandroid.likelive.ui.fragment.MainDialogFragment;

/**
 * Created by chenzd on 2017/2/28.
 * 该界面是LiveViewFragment与交互界面MainDialogFragment的容器
 */


public class LiveActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*这里可以看到的就是我们将初始化直播的Fragment添加到了这个页面作为填充
        * 并且将MainDialogFragment显示在该页面的顶部已达到各种不同交互的需求*/
        LiveViewFragment liveViewFragment = new LiveViewFragment();
        Intent intent = getIntent();

        int roomId = intent.getIntExtra("roomId",-1);
        String videoUrl = intent.getStringExtra("video_url");
        Bundle bundle = new Bundle();
        bundle.putInt("roomId",roomId);
        bundle.putString("video_url",videoUrl);
        liveViewFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().add(R.id.flmain, liveViewFragment).commit();
        MainDialogFragment mainDialogFragment = new MainDialogFragment();
        mainDialogFragment.setArguments(bundle);
        mainDialogFragment.show(getSupportFragmentManager(),"MainDialogFragment");
    }
}