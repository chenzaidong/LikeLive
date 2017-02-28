package cc.myandroid.likelive.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.ui.fragment.MyFragment;

public class MyActivity extends BaseActivity {

    private FragmentManager mFragmentManager;
    private MyFragment mMyFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        mFragmentManager=getSupportFragmentManager();
        mMyFragment=new MyFragment();
        FragmentTransaction transaction=mFragmentManager.beginTransaction();
        transaction.replace(R.id.replace,mMyFragment);
        transaction.commit();
    }
}
