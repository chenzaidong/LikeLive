package cc.myandroid.likelive.model.impl;

import java.util.Date;
import java.util.List;

import cc.myandroid.likelive.api.RoomInfoContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.http.HttpMethods;
import cc.myandroid.likelive.model.bean.CurrentTuhaoResult;
import cc.myandroid.likelive.model.bean.RoomInfoResult;
import cc.myandroid.likelive.model.bean.WeekTuhaoResult;
import cc.myandroid.likelive.utils.L;
import rx.Subscriber;

/**
 * Created by chenzd on 2017/2/19.
 */

public class RoomInfoModel implements RoomInfoContract.IRoomInfoModel {
    private Subscriber subscriber;
    /**
     * 获取场贡献榜
     */
    @Override
    public void getCurrentTuhaoList(int roomId, final ListCallback callback) {
        subscriber = new Subscriber<List<CurrentTuhaoResult>>() {
            @Override
            public void onCompleted() {
                L.i("RoomInfoModel.getCurrentTuhaoList.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("RoomInfoModel.getCurrentTuhaoList.onError:"+e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(List<CurrentTuhaoResult> currentTuhaoResults) {
                callback.success(currentTuhaoResults);
            }
        };
        HttpMethods.getInstance().getCurrentTuhaoList(subscriber,"currentTuhaoList","room","app",2,roomId,new Date().getTime());
    }

    @Override
    public void getWeekTuhaoList(int roomId,final ListCallback callback) {
        subscriber = new Subscriber<List<WeekTuhaoResult>>() {
            @Override
            public void onCompleted() {
                L.i("RoomInfoModel.getWeekTuhaoList.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("RoomInfoModel.getWeekTuhaoList.onError:"+e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(List<WeekTuhaoResult> weekTuhaoResults) {
                callback.success(weekTuhaoResults);
            }
        };
        HttpMethods.getInstance().getWeekTuhaoList(subscriber,"weekTuhaoList","room",2,roomId,new Date().getTime());
    }

    /**
     * 获得房间信息
     */
    @Override
    public void getRoomInfo(int roomId, final Callback callback) {
        subscriber = new Subscriber<RoomInfoResult>() {
            @Override
            public void onCompleted() {
                L.i("RoomInfoModel.getRoomInfo.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("RoomInfoModel.getRoomInfo.onError:"+e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(RoomInfoResult roomInfoResult) {
                L.i("RoomInfoModel.getRoomInfo.onNext:"+roomInfoResult);
                callback.success(roomInfoResult);
            }
        };
        // a=getInfo
        // &c=room
        // &m=app
        // &refrom=2
        // &roomid=32705
        // &t=1487150000101
        // &type=0
        HttpMethods.getInstance().getRoomInfo(subscriber,"getInfo","room","app",2,roomId,new Date().getTime(),0);
    }

    @Override
    public void onUnsubscribe() {
        if (subscriber != null&&!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }


}
