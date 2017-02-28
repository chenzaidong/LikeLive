package cc.myandroid.likelive.model.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import cc.myandroid.likelive.api.SearchContract;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.http.HttpMethods;
import cc.myandroid.likelive.model.bean.SearchHotRecommendResult;
import cc.myandroid.likelive.model.bean.SearchPlayerResult;
import cc.myandroid.likelive.utils.L;
import rx.Subscriber;

/**
 * Created by chenzd on 2017/2/19.
 */

public class SearchModel implements SearchContract.ISearchModel {
    private Subscriber subscriber;

    @Override
    public void search(String keyword, final ListCallback<SearchPlayerResult> callback) throws UnsupportedEncodingException {
        subscriber = new Subscriber<List<SearchPlayerResult>>() {
            @Override
            public void onCompleted() {
                L.i("SearchModel.search.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("SearchModel.search.onError:" + e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(List<SearchPlayerResult> searchPlayerResults) {
                callback.success(searchPlayerResults);
            }
        };
        // a=getSearchPlayerList
        // &c=player
        // &keyword=%E7%BA%AF%E5%AD%90
        // &m=app
        // &page=1
        // &page_size=20
        // &refrom=2
        // &t=1487148809580
        HttpMethods.getInstance().getSearchPlayerList(subscriber, "getSearchPlayerList", "player", keyword, "app", 1, 20, 2, new Date().getTime());
    }

    @Override
    public void getSearchHotRecommend(final ListCallback<SearchHotRecommendResult> callback) {
        subscriber = new Subscriber<List<SearchHotRecommendResult>>() {
            @Override
            public void onCompleted() {
                L.i("SearchModel.getSearchHotRecommend.onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("SearchModel.getSearchHotRecommend.onError:" + e.getMessage());
                callback.failed(e.getMessage());
            }

            @Override
            public void onNext(List<SearchHotRecommendResult> searchHotRecommendResults) {
                callback.success(searchHotRecommendResults);
            }
        };
        // a=getSearchHotRecommendList
        // &c=player
        // &m=app
        // &refrom=2
        // &t=1487148765522
        HttpMethods.getInstance().getSearchHotRecommendList(subscriber, "getSearchHotRecommendList", "player", "app", 2, new Date().getTime());
    }

    @Override
    public void onUnsubscribe() {
        if (subscriber != null && !subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }
}
