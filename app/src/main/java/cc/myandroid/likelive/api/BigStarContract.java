package cc.myandroid.likelive.api;

import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.BigStarResult;

/**
 * Created by chenzd on 2017/2/19.
 * 明星排行榜相关接口
 */

public class BigStarContract {
    public interface IBigStarView{
        void onGetBigStarList(BigStarResult data);//获取明星排行榜回调
        void showProgress();//可以显示进度条
        void hideProgress();//可以隐藏进度条
        void onFailed(String message);//失败回调方法
    }

    public interface IBigStarPresenter{
        void getBigStarList();//获取明星排行榜逻辑处理
        void onStart();
        void onStop();
    }

    public interface IBigStarModel{
        void getBigStarList(Callback<BigStarResult> callback);
        void onUnsubscribe();
    }
}
