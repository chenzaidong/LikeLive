package cc.myandroid.likelive.api;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cc.myandroid.likelive.callback.ListCallback;
import cc.myandroid.likelive.model.bean.SearchHotRecommendResult;
import cc.myandroid.likelive.model.bean.SearchPlayerResult;

/**
 * Created by chenzd on 2017/2/19.
 * 搜索页面相关接口
 */

public class SearchContract {
   public interface  ISearchView{
       void onGetSearchHotRecommend(List<SearchHotRecommendResult> data);
       void foucs();
       void unFoucs();
       void onSearch(List<SearchPlayerResult> data);
       void showProgress();//可以显示进度条
       void hideProgress();//可以隐藏进度条
       void onGetFailed(String message);//获取失败
       void onStart();
       void onStop();
   }

    public interface  ISearchPresenter{
        void search(String keyword) throws UnsupportedEncodingException;
        void getSearchHotRecommend();
         void onStart();
         void onStop();
    }

    public interface ISearchModel{
        void search(String keyword, ListCallback<SearchPlayerResult> callback) throws UnsupportedEncodingException;
        void getSearchHotRecommend(ListCallback<SearchHotRecommendResult> callback);
        void onUnsubscribe();
    }
}
