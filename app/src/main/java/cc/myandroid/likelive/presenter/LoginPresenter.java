package cc.myandroid.likelive.presenter;

import android.util.Log;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import cc.myandroid.likelive.api.LoginContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.model.impl.LoginModel;
import cc.myandroid.likelive.utils.EncryptionUtil;
import cc.myandroid.likelive.utils.SPUtils;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Created by chenzd on 2017/2/19.
 */

public class LoginPresenter implements LoginContract.ILoginPresenter {
    private LoginContract.ILoginModel iLoginModel;
    private LoginContract.ILoginView iLoginView;

    public LoginPresenter(LoginContract.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModel();
    }

    /**
     * 登陆
     *
     * @param phoneNum
     * @param password
     */
    @Override
    public void login(String phoneNum, String password) {
        String psw = null;
        try {
            psw = EncryptionUtil.EncodeMD5(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        iLoginModel.login(phoneNum, psw, new Callback<User>() {
            @Override
            public void success(User data) {
                SPUtils.saveUser(data);
                iLoginView.onLogin(data);
            }

            @Override
            public void failed(String result) {
                iLoginView.loginFailed(result);
            }
        });
    }

    /**
     * QQ登陆
     */
    @Override
    public void qqLogin() {

    }

    /**
     * 微博登陆
     */
    @Override
    public void weiboLogin() {
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e("---------", "用户合法");
                String icon = platform.getDb().getUserIcon();
                String userName = platform.getDb().getUserName();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        weibo.showUser(null);
        //iLoginView.onLogin();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
