package cc.myandroid.likelive.presenter;

import java.util.List;

import cc.myandroid.likelive.api.GiftContract;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.model.bean.GetGiftResult;
import cc.myandroid.likelive.model.impl.GiftInfoModel;

/**
 * Created by chenzd on 2017/2/21.
 */

public class GiftInfoPresenter implements GiftContract.IGiftInfoPresenter {
    private GiftContract.IGiftInfoModel iGiftInfoModel;
    private GiftContract.IGiftInfoView iGiftInfoView;
    public GiftInfoPresenter(GiftContract.IGiftInfoView iGiftInfoView){
        this.iGiftInfoView = iGiftInfoView;
        iGiftInfoModel = new GiftInfoModel();
    }
    @Override
    public void getGiftList() {
        iGiftInfoModel.getGiftList(new ListCallback<GetGiftResult>() {
            @Override
            public void success(List<GetGiftResult> data) {
                iGiftInfoView.onGetGiftInfo(data);
            }

            @Override
            public void failed(String result) {
                iGiftInfoView.onGetFailed(result);
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        iGiftInfoModel.onUnsubscribe();
    }
}
