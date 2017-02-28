package cc.myandroid.likelive.presenter;

import cc.myandroid.likelive.api.BigStarContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.BigStarResult;
import cc.myandroid.likelive.model.impl.BigStarModel;

/**
 * Created by chenzd on 2017/2/19.
 */

public class BigStarPresenter implements BigStarContract.IBigStarPresenter {
    private BigStarContract.IBigStarModel iBigStarModel;
    private BigStarContract.IBigStarView iBigStarView;

    public BigStarPresenter(BigStarContract.IBigStarView iBigStarView){
        iBigStarModel = new BigStarModel();
        this.iBigStarView = iBigStarView;
    }

    /**
     * 获取明星排行榜
     */
    @Override
    public void getBigStarList() {
        iBigStarView.showProgress();
        iBigStarModel.getBigStarList(new Callback<BigStarResult>() {
            @Override
            public void success(BigStarResult data) {
                iBigStarView.onGetBigStarList(data);
                iBigStarView.hideProgress();
            }

            @Override
            public void failed(String result) {
                iBigStarView.onFailed(result);
                iBigStarView.hideProgress();
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        iBigStarModel.onUnsubscribe();
    }
}
