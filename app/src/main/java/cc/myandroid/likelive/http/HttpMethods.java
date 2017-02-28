package cc.myandroid.likelive.http;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cc.myandroid.likelive.api.ApiService;
import cc.myandroid.likelive.constant.URLConstant;
import cc.myandroid.likelive.exception.ApiException;
import cc.myandroid.likelive.model.bean.BannerResult;
import cc.myandroid.likelive.model.bean.BigStarResult;
import cc.myandroid.likelive.model.bean.CurrentTuhaoResult;
import cc.myandroid.likelive.model.bean.GetGiftResult;
import cc.myandroid.likelive.model.bean.HotRecommendResult;
import cc.myandroid.likelive.model.bean.HttpResult;
import cc.myandroid.likelive.model.bean.RoomInfoResult;
import cc.myandroid.likelive.model.bean.SearchHotRecommendResult;
import cc.myandroid.likelive.model.bean.SearchPlayerResult;
import cc.myandroid.likelive.model.bean.TodayStarResult;
import cc.myandroid.likelive.model.bean.WeekTuhaoResult;
import cc.myandroid.likelive.utils.L;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static cc.myandroid.likelive.exception.ApiException.DATA_PARSING_ERRORS;
import static cc.myandroid.likelive.exception.ApiException.NETWORK_CONNECTION_ERROR;
import static cc.myandroid.likelive.exception.ApiException.SOCKET_TIMEOUT_EXCEPTION;

/**
 * Created by chenzd on 2017/2/19.
 * 分装网络请求类
 */

public class HttpMethods {
    private Retrofit mRetrofit;
    private ApiService mApiService;
    /**
     * 设置网络超时时间(秒)
     */
    private static final int DEFAULT_TIMEOUT = 5;

    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder().baseUrl(URLConstant.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    //获取对象
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    /**
     * 处理获取广告条信息的网络请求
     */
    public void getBanner(Subscriber<BannerResult> subscriber, String a, String c, String m, int refrom, long t) {
        Observable observable = mApiService.getBanner(a, c, m, refrom, t)
                .map(new HttpResultFunc<BannerResult>())
                .onErrorResumeNext(new ErrorResultFunc<BannerResult>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取热门直播
     */
    public void getHotRecommend(Subscriber<HotRecommendResult> subscriber, String a, String c, String is_filter_act, String m, int page, int page_size, int refrom, long t) {
        Observable observable = mApiService.getHotRecommend(a, c, is_filter_act, m, page, page_size, refrom, t)
                .map(new HttpResultFunc<HotRecommendResult>())
                .onErrorResumeNext(new ErrorResultFunc<HotRecommendResult>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取最新直播
     */
    public void getTodayStarList(Subscriber<TodayStarResult> subscriber, String a, String c, String m, int page, int page_size, int refrom, long t) {
        Observable observable = mApiService.getTodayStarList(a, c, m, page, page_size, refrom, t)
                .map(new HttpResultFunc<TodayStarResult>())
                .onErrorResumeNext(new ErrorResultFunc<TodayStarResult>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取明星排行榜
     */
    public void getBigStarList(Subscriber<BigStarResult> subscriber, String a, String c, String m, int refrom, long t) {
        Observable observable = mApiService.getBigStarList(a, c, m, refrom, t)
                .map(new HttpResultFunc<BigStarResult>())
                .onErrorResumeNext(new ErrorResultFunc<BigStarResult>());
        toSubscribe(observable, subscriber);
    }


    /**
     * 获取搜索页面热门主播
     */
    public void getSearchHotRecommendList(Subscriber<List<SearchHotRecommendResult>> subscriber, String a, String c, String m, int refrom, long t) {
        Observable observable = mApiService.getSearchHotRecommendList(a, c, m, refrom, t)
                .map(new HttpResultFunc<List<SearchHotRecommendResult>>())
                .onErrorResumeNext(new ErrorResultFunc<List<SearchHotRecommendResult>>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取搜索页面热门主播
     */
    public void getSearchPlayerList(Subscriber<List<SearchPlayerResult>> subscriber, String a, String c, String keyword, String m, int page, int page_size, int refrom, long t) {
        Observable observable = mApiService.getSearchPlayerList(a, c, keyword, m, page, page_size, refrom, t)
                .map(new HttpResultFunc<List<SearchPlayerResult>>())
                .onErrorResumeNext(new ErrorResultFunc<List<SearchPlayerResult>>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取场贡献榜
     */

    public void getCurrentTuhaoList(Subscriber<List<CurrentTuhaoResult>> subscriber, String a, String c, String m, int roomid, int refrom, long t) {
        Observable observable = mApiService.getCurrentTuhaoList(a, c, m, refrom, roomid, t)
                .map(new HttpResultFunc<List<CurrentTuhaoResult>>())
                .onErrorResumeNext(new ErrorResultFunc<List<CurrentTuhaoResult>>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取周贡献榜
     */
    public void getWeekTuhaoList(Subscriber<List<WeekTuhaoResult>> subscriber, String a, String c, int roomid, int refrom, long t) {
        Observable observable = mApiService.getWeekTuhaoList(a, c, refrom, roomid, t)
                .map(new HttpResultFunc<List<WeekTuhaoResult>>())
                .onErrorResumeNext(new ErrorResultFunc<List<WeekTuhaoResult>>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 获取场贡献榜
     */
    public void getGiftList(Subscriber<List<GetGiftResult>> subscriber, String a, String c, int is_get_guizu, String m, int refrom, long t) {
        Observable observable = mApiService.getGiftList(a, c, is_get_guizu, m, refrom, t)
                .map(new HttpResultFunc<List<GetGiftResult>>())
                .onErrorResumeNext(new ErrorResultFunc<List<GetGiftResult>>());
        toSubscribe(observable, subscriber);
    }

    // a=getInfo
    // &c=room
    // &m=app
    // &refrom=2
    // &roomid=32705
    // &t=1487150000101
    // &type=0

    /**
     * 获取房间信息
     */
    public void getRoomInfo(Subscriber<RoomInfoResult> subscriber, String a, String c, String m, int refrom, int roomid, long t, int type) {
        Observable observable = mApiService.getRoomInfo(a, c, m, refrom, roomid, t, type)
                .map(new HttpResultFunc<RoomInfoResult>())
                .onErrorResumeNext(new ErrorResultFunc<RoomInfoResult>());
        toSubscribe(observable, subscriber);
    }

    /**
     * 封装方法
     *
     * @param o
     * @param s
     * @param <T>
     */
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {
        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getResult() != 0) {
                throw new ApiException(httpResult.getMessage());
            }
            return httpResult.getData();
        }
    }

    /**
     * 错误信息过滤器
     *
     * @param <T>
     */
    private class ErrorResultFunc<T> implements Func1<Throwable, Observable<T>> {
        @Override
        public Observable<T> call(Throwable throwable) {
            int code = -1;
            if (throwable instanceof HttpException || throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
                code = NETWORK_CONNECTION_ERROR;
            } else if (throwable instanceof JsonParseException
                    || throwable instanceof JSONException
                    || throwable instanceof ParseException) {
                code = DATA_PARSING_ERRORS;
            } else if (throwable instanceof SocketTimeoutException) {
                code = SOCKET_TIMEOUT_EXCEPTION;
            } else {
                L.e("HttpMethods.ErrorResultFunc：" + throwable.toString());
            }
            return Observable.error(new ApiException(code));
        }
    }
}
