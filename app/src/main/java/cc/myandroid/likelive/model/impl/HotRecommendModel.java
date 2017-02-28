package cc.myandroid.likelive.model.impl;

import java.util.Date;

import cc.myandroid.likelive.api.HotRecommendContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.http.HttpMethods;
import cc.myandroid.likelive.model.bean.BannerResult;
import cc.myandroid.likelive.model.bean.HotRecommendResult;
import cc.myandroid.likelive.utils.L;
import rx.Subscriber;

/**
 * Created by chenzd on 2017/2/22.
 * 获得热门直播Model实现类
 */

public class HotRecommendModel implements HotRecommendContract.IHotRecommendModel {
    private Subscriber subscriber;
    @Override
    public void getBanner(final Callback callback) {
        subscriber = new Subscriber<BannerResult>() {
            @Override
            public void onCompleted() {
                L.i("getBanner:onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.i("getBanner:onError:"+e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(BannerResult bannerResult) {
                L.i("getBanner:onNext:"+bannerResult.toString());
                callback.success(bannerResult);
            }

        };
        HttpMethods.getInstance().getBanner(subscriber,"getBannerInfo","site","app",2,new Date().getTime());
    }

    @Override
    public void getHotRecommend(int page, int pageSize, final Callback<HotRecommendResult> callback) {
        subscriber = new Subscriber<HotRecommendResult>() {
            @Override
            public void onCompleted() {
                L.i("getHotRecommend:onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.i("getHotRecommend:onError"+e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(HotRecommendResult hotRecommendResult) {
                L.i("getHotRecommend:onNext"+hotRecommendResult);
                callback.success(hotRecommendResult);
            }
        };
        // a=getHotRecommendList
        // &c=player
        // &is_filter_act=1
        // &m=app
        // &page=1
        // &page_size=20
        // &refrom=2
        // &t=1487148499769
        HttpMethods.getInstance().getHotRecommend(subscriber,"getHotRecommendList","player","1","app",page,pageSize,2,new Date().getTime());
    }
    public void onUnsubscribe(){
        if (subscriber != null&&!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
