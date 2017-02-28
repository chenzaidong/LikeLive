package cc.myandroid.likelive.model.impl;

import java.util.List;

import cc.myandroid.likelive.api.LoginContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.model.bean.User;

/**
 * Created by chenzd on 2017/2/19.
 * 用户登陆Model
 */

public class LoginModel implements LoginContract.ILoginModel {
    /**
     * 登陆功能
     *
     * @param phoneNum
     * @param password
     * @param callback
     */
    @Override
    public void login(String phoneNum, String password, Callback<User> callback) {
        List<User> userList = DBManager.getInstance().quereUserList(phoneNum, password);
        if (userList.size() > 0) {
            callback.success(userList.get(0));
        } else {
            callback.failed("帐号或者密码错误");
        }
    }
}
