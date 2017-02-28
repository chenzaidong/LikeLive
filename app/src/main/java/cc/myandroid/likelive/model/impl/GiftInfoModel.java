package cc.myandroid.likelive.model.impl;

import java.util.Date;
import java.util.List;

import cc.myandroid.likelive.api.GiftContract;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.http.HttpMethods;
import cc.myandroid.likelive.model.bean.GetGiftResult;
import cc.myandroid.likelive.utils.L;
import rx.Subscriber;

/**
 * Created by chenzd on 2017/2/21.
 *
 */

public class GiftInfoModel implements GiftContract.IGiftInfoModel {
    private Subscriber subscriber;
    @Override
    public void onUnsubscribe() {
        if (subscriber!=null&&subscriber.isUnsubscribed())subscriber.unsubscribe();
    }
    @Override
    public void getGiftList(final ListCallback callback) {
        subscriber = new Subscriber<List<GetGiftResult>>() {
            @Override
            public void onCompleted() {
                L.i("GiftInfoModel.getGiftList.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("GiftInfoModel.getGiftList.onError:"+e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(List<GetGiftResult> getGiftResults) {
                callback.success(getGiftResults);
            }
        };
        // a=getList
        // &c=gift
        // &is_get_guizu=1
        // &m=app
        // &refrom=2
        // &t=1487149999736
        HttpMethods.getInstance().getGiftList(subscriber,"getList","gift",1,"app",2,new Date().getTime());
    }
}
