package cc.myandroid.likelive.presenter;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import cc.myandroid.likelive.api.RegistContract;
import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.model.bean.User;
import cc.myandroid.likelive.utils.EncryptionUtil;
import cc.myandroid.likelive.utils.SPUtils;
import cc.myandroid.likelive.utils.StringFormatUtil;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static cn.smssdk.SMSSDK.registerEventHandler;

/**
 * Created by chenzd on 2017/2/19.
 * 注册功能模块presenter
 */

public class RegistPresenter implements RegistContract.IRegistPresenter {
    private static final int GET_OK = 0;//获取验证码完成
    private static final int SUBMIT_OK = 1;//提交验证码完成
    private static final int ERROR = 2;//失败
    private RegistContract.IRegistView iRegistView;
    private String phoneNum, password;
    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == SUBMIT_OK) {   //处理手机 验证正确
                User user = new User();
                user.setPhoneNumber(phoneNum);
                try {
                    user.setPassword(EncryptionUtil.EncodeMD5(password));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                user.setId(DBManager.getInstance().insertUser(user));    //将对象保存到数据库中
                SPUtils.saveUser(user);//将当前注册用户存入SP文件表示当前已经是登陆状态
                iRegistView.onRegist();
            } else if (msg.what == GET_OK) {
                iRegistView.onGetCaptchaSuccess();
            } else {//处理手机 验证错误
                iRegistView.onFailed(msg.obj.toString());
            }
        }
    };

    public RegistPresenter(RegistContract.IRegistView iRegistView) {
        this.iRegistView = iRegistView;
        registerEventHandler(ev);//初始化
    }

    /**
     * ShareSD短信验证回调
     */
    private EventHandler ev = new EventHandler() {
        @Override
        public void afterEvent(int event, int result, Object data) {
            //-----------这里是子线程---------
            //回调完成
            if (result == SMSSDK.RESULT_COMPLETE) {
                //提交验证码成功,如果验证成功会在data里返回数据。data数据类型为HashMap<number,code>
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//短信验证成功
                    HashMap<String, Object> mData = (HashMap<String, Object>) data;
                    //String country = (String) mData.get("country");//返回的国家编号
                    final String phone = (String) mData.get("phone");//返回用户注册的手机号
                    if (phone.equals(phoneNum)) {
                        mHander.sendEmptyMessage(SUBMIT_OK);
                    }
                }
                //发送验证码完成
                else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    mHander.sendEmptyMessage(GET_OK);
                }
                //短信验证失败
                else {
                    Message message = Message.obtain();
                    message.what = ERROR;
                    message.obj = "短信验证错误";
                    mHander.sendMessage(message);
                }
            } else {
                Message message = Message.obtain();
                message.what = ERROR;
                String msg = StringFormatUtil.getErrorMessageDetail(((Throwable) data).getMessage());
                message.obj = msg;
                //L.e(((Throwable) data).getMessage());
                mHander.sendMessage(message);
            }
        }
    };

    /**
     * 注册功能
     *
     * @param phoneNum
     * @param password
     * @param code
     */
    @Override
    public void regist(String phoneNum, String password, String code) {
        this.phoneNum = phoneNum;
        this.password = password;
        SMSSDK.submitVerificationCode("+86", phoneNum, code);//国家号，手机号码，验证码 发送验证
    }

    @Override
    public void getCaptcha(String phoneNum) {
        //发送短信，传入国家号和电话---使用SMSSDK核心类之前一定要在MyApplication中初始化，否侧不能使用
        if (TextUtils.isEmpty(phoneNum)) {
            iRegistView.onFailed("号码不能为空！");
        } else if (!StringFormatUtil.Matches(phoneNum)) {//判断手机号码格式是否匹配
            iRegistView.onFailed("手机号码格式不正确！");
        } else if (isRegisted(phoneNum)) {
            iRegistView.onFailed("该手机号码已经注册！");
        } else {
            iRegistView.setCaptchaEnable();
            SMSSDK.getVerificationCode("+86", phoneNum);
        }
    }

    @Override
    public void onStop() {
        SMSSDK.registerEventHandler(ev); //注销
    }

    /**
     * 判断手机号码是否已经注册
     *
     * @return
     */
    @Override
    public boolean isRegisted(String phoneNum) {
        List<User> userList = DBManager.getInstance().quereUserList(phoneNum);
        if (userList.size() > 0) return true;//已经注册过，返回true
        return false;
    }
}

