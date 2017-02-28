package cc.myandroid.likelive.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.myandroid.likelive.R;
import cc.myandroid.likelive.base.BaseActivity;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.ui.fragment.FocusFragment;
import cc.myandroid.likelive.ui.fragment.HomeFragment;
import cc.myandroid.likelive.ui.fragment.MyFragment;

/**
 * 主页视图
 */
public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.replace)
    FrameLayout replace;
    @BindView(R.id.rg_home)
    RadioGroup rgHome;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_focus)
    RadioButton rbFocus;
    @BindView(R.id.rb_my)
    RadioButton rbMy;
    private HomeFragment mHomeFragment;
    private FocusFragment mFocusFragment;
    private MyFragment mMyFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        //TODO 记得删除
        DBManager.insertUserTest();
        initData();//初始化数据
    }

    /**
     * 初始化数据
     */
    private void initData() {
        setDefaultFragment();
        rbHome.setChecked(true);
        rgHome.setOnCheckedChangeListener(this);
    }

    /**
     * 初始化fragment
     */
    private void setDefaultFragment() {
        //设置首页的fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mHomeFragment = new HomeFragment();
        mFocusFragment= new FocusFragment();
        mMyFragment= new MyFragment();
        fragmentTransaction.add(R.id.replace, mHomeFragment, "home");
        fragmentTransaction.add(R.id.replace,mFocusFragment,"focus");
        fragmentTransaction.add(R.id.replace,mMyFragment,"my");
        fragmentTransaction.show(mHomeFragment);
        fragmentTransaction.hide(mFocusFragment);
        fragmentTransaction.hide(mMyFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mHomeFragment);
        fragmentTransaction.hide(mFocusFragment);
        fragmentTransaction.hide(mMyFragment);
        switch (checkedId) {
            case R.id.rb_home:
                fragmentTransaction.show(mHomeFragment);
                break;
            case R.id.rb_focus:
                fragmentTransaction.show(mFocusFragment);
                break;
            case R.id.rb_my:
                fragmentTransaction.show(mMyFragment);
                break;
        }
        fragmentTransaction.commit();
    }
}
