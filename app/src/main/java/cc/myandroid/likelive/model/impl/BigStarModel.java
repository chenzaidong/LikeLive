package cc.myandroid.likelive.model.impl;

import java.util.Date;

import cc.myandroid.likelive.api.BigStarContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.http.HttpMethods;
import cc.myandroid.likelive.model.bean.BigStarResult;
import cc.myandroid.likelive.utils.L;
import rx.Subscriber;

/**
 * Created by chenzd on 2017/2/19.
 */

public class BigStarModel implements BigStarContract.IBigStarModel {
    private Subscriber subscriber;
    @Override
    public void getBigStarList(final Callback<BigStarResult> callback) {
        subscriber = new Subscriber<BigStarResult>() {
            @Override
            public void onCompleted() {
                L.i("BigStarModel.getBigStarList.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("BigStarModel.getBigStarList.onError"+e);
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(BigStarResult bigStarResult) {
                L.i("BigStarModel.getBigStarList.onNext:"+bigStarResult);
                callback.success(bigStarResult);
            }
        };
        // a=getBigStarList
        // &c=player
        // &m=app
        // &refrom=2
        // &t=1487148702298
        HttpMethods.getInstance().getBigStarList(subscriber,"getBigStarList","player","app",2,new Date().getTime());
    }

    @Override
    public void onUnsubscribe() {
        if (subscriber != null&&!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
