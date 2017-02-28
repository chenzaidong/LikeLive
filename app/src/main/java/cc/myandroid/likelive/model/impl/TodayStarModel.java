package cc.myandroid.likelive.model.impl;

import java.util.Date;

import cc.myandroid.likelive.api.TodayStarContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.http.HttpMethods;
import cc.myandroid.likelive.model.bean.TodayStarResult;
import cc.myandroid.likelive.utils.L;
import rx.Subscriber;

/**
 * Created by chenzd on 2017/2/22.
 * 获取最新直播数据Model
 */

public class TodayStarModel implements TodayStarContract.ITodayStarModel {
    private Subscriber subscriber;
    @Override
    public void getTodayStarList (int page, int pageSize,final Callback<TodayStarResult> callback)  {
        subscriber =new Subscriber<TodayStarResult>(){
            @Override
            public void onCompleted() {
                L.i("getHotRecommend:onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                callback.failed(e.getMessage());
                L.i("getTodayStarList:onError:"+e.getMessage());
            }

            @Override
            public void onNext(TodayStarResult todayStarResult) {
                L.i("getTodayStarList:onNext:"+todayStarResult);
                callback.success(todayStarResult);
            }
        };
        // a=getTodayStarList
        // &c=player
        // &m=app
        // &page=1
        // &page_size=20
        // &refrom=2
        // &t=1487148658220
        HttpMethods.getInstance().getTodayStarList(subscriber,"getTodayStarList","player","app",page,pageSize,2,new Date().getTime());

    }

    /**
     * 取消订阅
     */
    public void onUnsubscribe(){
        if (subscriber != null&&!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
