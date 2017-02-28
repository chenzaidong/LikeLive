package cc.myandroid.likelive.presenter;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cc.myandroid.likelive.api.SearchContract;
import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.model.bean.SearchHotRecommendResult;
import cc.myandroid.likelive.model.bean.SearchPlayerResult;
import cc.myandroid.likelive.model.impl.SearchModel;

/**
 * Created by chenzd on 2017/2/19.
 */

public class SearchPresenter implements SearchContract.ISearchPresenter {
    private SearchContract.ISearchModel iSearchModel;
    private SearchContract.ISearchView iSearchView;
    public SearchPresenter(SearchContract.ISearchView iSearchView){
        iSearchModel= new SearchModel();
        this.iSearchView = iSearchView;
    }

    /**
     * 搜索主播
     * @param keyword
     * @throws UnsupportedEncodingException
     */
    @Override
    public void search(String keyword) throws UnsupportedEncodingException {
        iSearchModel.search(keyword, new ListCallback<SearchPlayerResult>() {
            @Override
            public void success(List<SearchPlayerResult> data) {
                iSearchView.onSearch(data);
            }

            @Override
            public void failed(String result) {
                iSearchView.onGetFailed(result);
            }
        });
    }

    /**
     * 获取搜索界面热门主播推荐
     */
    @Override
    public void getSearchHotRecommend() {
        iSearchModel.getSearchHotRecommend(new ListCallback<SearchHotRecommendResult>() {
            @Override
            public void success(List<SearchHotRecommendResult> data) {
                iSearchView.onGetSearchHotRecommend(data);
            }

            @Override
            public void failed(String result) {
                iSearchView.onGetFailed(result);
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        iSearchModel.onUnsubscribe();
    }
}
