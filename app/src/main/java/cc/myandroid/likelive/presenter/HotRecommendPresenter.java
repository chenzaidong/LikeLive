package cc.myandroid.likelive.presenter;

import cc.myandroid.likelive.api.HotRecommendContract;
import cc.myandroid.likelive.callback.Callback;
import cc.myandroid.likelive.model.bean.BannerResult;
import cc.myandroid.likelive.model.bean.HotRecommendResult;
import cc.myandroid.likelive.model.impl.HotRecommendModel;

/**
 * Created by chenzd on 2017/2/22.
 * 热门直播presenter
 */

public class HotRecommendPresenter implements HotRecommendContract.IHotRecommendPresenter {
    private HotRecommendContract.IHotRecommendView iHotRecommendView;
    private HotRecommendContract.IHotRecommendModel iHotRecommendModel;

    public HotRecommendPresenter(HotRecommendContract.IHotRecommendView iHotRecommendView) {
        iHotRecommendModel = new HotRecommendModel();
        this.iHotRecommendView = iHotRecommendView;
    }

    /**
     * 获得广告数据
     */
    public void getBanner() {
        iHotRecommendModel.getBanner(new Callback<BannerResult>() {
            @Override
            public void success(BannerResult data) {
                iHotRecommendView.onGetBanner(data);
            }

            @Override
            public void failed(String result) {
                iHotRecommendView.onGetFailed(result);
            }
        });
    }

    /**
     * 获得热门直播
     *
     * @param page
     * @param pageSize
     */
    @Override
    public void getHotRecommend(int page, int pageSize) {
        iHotRecommendModel.getHotRecommend(page, pageSize, new Callback<HotRecommendResult>() {
            @Override
            public void success(HotRecommendResult data) {
                iHotRecommendView.onGetHotRecommend(data);
            }

            @Override
            public void failed(String result) {
                iHotRecommendView.onGetFailed(result);
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        iHotRecommendModel.onUnsubscribe();
    }
}
