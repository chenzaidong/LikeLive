package cc.myandroid.likelive.api;

/**
 * Created by chenzd on 2017/2/19.
 * 注册模块相关接口
 */

public class RegistContract {
    public interface  IRegistView{
        void onRegist();//注册完成回调
        void onFailed(String message);//注册失败回调
        void getCaptcha();//获得验证码
        void setCaptchaPrepar();//设置获取验证码按钮可以点击
        void setCaptchaEnable();//设置获取验证码按钮不能点击
        void onGetCaptchaSuccess();//发送验证码成功
    }

    public interface  IRegistPresenter{
        boolean isRegisted(String phoneNum);
        void regist(String phoneNum,String password,String code);
        void getCaptcha(String phoneNum);
        void onStop();
    }
}
