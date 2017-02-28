package cc.myandroid.likelive.api;

import java.util.List;

import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.model.bean.GetGiftResult;

/**
 * Created by chenzd on 2017/2/21.
 */

public class GiftContract {
    public interface IGiftInfoView{
        void onGetGiftInfo(List<GetGiftResult> gifts);
        void onGetFailed(String message);//获取失败
    }
    public interface IGiftInfoModel{
        void onUnsubscribe();
        void getGiftList(ListCallback callback);//获得礼物
    }
    public interface IGiftInfoPresenter{
        void getGiftList();//获得礼物
        void onStart();
        void onStop();
    }
}
