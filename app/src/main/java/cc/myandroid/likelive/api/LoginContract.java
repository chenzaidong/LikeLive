package cc.myandroid.likelive.api;

import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.User;

/**
 * Created by chenzd on 2017/2/19.
 * 登陆模块相关接口
 */

public class LoginContract {
    public interface  ILoginView{
        void onLogin(User user);//登陆成功后的回调
        void loginFailed(String message);//登陆失败
        void qqLogin();
        void weiboLogin();
        void toRegist();
        void findPassword();
    }
    public interface ILoginPresenter{
        void login(String phoneNum,String password);
        void qqLogin();
        void weiboLogin();
        void onStart();
        void onStop();
    }
    public interface ILoginModel{
        void login(String phoneNum,String password,Callback<User> callback);
    }
}
