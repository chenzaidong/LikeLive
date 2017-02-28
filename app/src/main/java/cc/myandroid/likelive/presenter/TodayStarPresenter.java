package cc.myandroid.likelive.presenter;

import cc.myandroid.likelive.api.TodayStarContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.TodayStarResult;
import cc.myandroid.likelive.model.impl.TodayStarModel;

/**
 * Created by chenzd on 2017/2/22.
 * 获取最新直播数据Presenter
 */

public class TodayStarPresenter implements TodayStarContract.ITodayStarPresenter {
    private TodayStarContract.ITodayStarModel iTodayStarModel;
    private TodayStarContract.ITodayStarView iTodayStarView;

    public TodayStarPresenter(TodayStarContract.ITodayStarView iTodayStarView) {
        iTodayStarModel = new TodayStarModel();
        this.iTodayStarView = iTodayStarView;
    }

    @Override
    public void getTodayStarList(int page, int pageSize) {
        iTodayStarModel.getTodayStarList(page, pageSize, new Callback<TodayStarResult>() {
            @Override
            public void success(TodayStarResult data) {
                iTodayStarView.onGetTodayStarList(data);
            }

            @Override
            public void failed(String result) {
                iTodayStarView.onGetFailed(result);
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        iTodayStarModel.onUnsubscribe();
    }
}
