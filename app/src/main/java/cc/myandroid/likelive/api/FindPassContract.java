package cc.myandroid.likelive.api;

/**
 * Created by Administrator on 2017/2/27.
 */

public class FindPassContract {
    public interface IFindPassView{
        void onChangeSuccess();//密码成功回调
        void onChangeFailed(String message);//修改失败回调
        void getCaptcha();//获得验证码
        void setCaptchaPrepar();//设置获取验证码按钮可以点击
        void setCaptchaEnable();//设置获取验证码按钮不能点击
        void onGetCaptchaSuccess();//发送验证码成功

    }
    public interface IFindPassPresenter{
        boolean notRegisted(String phoneNum);//是否已注册
        void changePass(String phoneNum,String password,String code);
        void getCaptcha(String phoneNum);
        void onStops();
    }
}
