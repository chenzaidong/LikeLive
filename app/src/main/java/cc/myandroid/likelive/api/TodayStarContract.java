package cc.myandroid.likelive.api;

import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.TodayStarResult;

/**
 * Created by chenzd on 2017/2/22.
 * 获取最新直播相关接口
 */

public class TodayStarContract {
    public interface ITodayStarView{
        void onGetTodayStarList(TodayStarResult data);//获取热门直播信息
        void onGetFailed(String message);//获取失败
    }
    public interface ITodayStarModel{
        /**
         * 获取今日最新直播数据
         * @param page 页码
         * @param pageSize 每页展示数据量
         * @param callback
         */
        void getTodayStarList(int page,int pageSize,Callback<TodayStarResult> callback);

        /**
         * 取消订阅
         */
        void onUnsubscribe();
    }
    public interface ITodayStarPresenter{

        void getTodayStarList(int page,int pageSize);//获取热门直播信息
        void onStart();
        void onStop();
    }
}
