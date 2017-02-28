package cc.myandroid.likelive.api;

import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.BannerResult;
import cc.myandroid.likelive.model.bean.HotRecommendResult;

/**
 * Created by chenzd on 2017/2/22.
 * 获取热门直播相关接口
 */

public class HotRecommendContract {
    public interface IHotRecommendView {
        void onGetBanner(BannerResult data);//获取广告条
        void onGetHotRecommend(HotRecommendResult data);//获取最新直播信息
        void onGetFailed(String message);//获取失败
    }

    public interface IHotRecommendPresenter{
        void getBanner();//获取广告条
        void getHotRecommend(int page,int pageSize);//获取最新直播信息
        void onStart();
        void onStop();
    }

    public interface IHotRecommendModel{

        /** 获取广告条信息
        * @param callback
        */
        void getBanner(Callback<BannerResult> callback);

        /**
         * 获取热门推荐直播信息
         * @param page 页码
         * @param pageSize  每页展示数据量
         * @param callback
         */
        void getHotRecommend(int page,int pageSize,Callback<HotRecommendResult> callback);
        /**
         * 取消订阅
         */
        void onUnsubscribe();
    }
}
