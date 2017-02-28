package cc.myandroid.likelive.api;

import java.util.List;

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
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chenzd on 2017/2/19.
 */

public interface ApiService {

    /**
     * 获取广告信息
     */
    @GET("caller?")
    Observable<HttpResult<BannerResult>> getBanner(
            @Query("a") String a,
            @Query("c") String c,
            @Query("m") String m,
            @Query("refrom") int refrom,
            @Query("t") long t
    );
    //caller?
    // a=getHotRecommendList
    // &c=player
    // &is_filter_act=1
    // &m=app
    // &page=1
    // &page_size=20
    // &refrom=2
    // &t=1487148499769

    /**
     * 获取热门直播
     */
    @GET("caller?")
    Observable<HttpResult<HotRecommendResult>> getHotRecommend(
            @Query("a") String a,
            @Query("c") String c,
            @Query("is_filter_act") String is_filter_act,
            @Query("m") String m,
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("refrom") int refrom,
            @Query("t") long t
    );

    //caller?
    // a=getTodayStarList
    // &c=player
    // &m=app
    // &page=1
    // &page_size=20
    // &refrom=2
    // &t=1487148658220

    /**
     * 获取最新直播
     *
     */
    @GET("caller?")
    Observable<HttpResult<TodayStarResult>> getTodayStarList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("m") String m,
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("refrom") int refrom,
            @Query("t") long t
    );


    //caller?
    // a=getBigStarList
    // &c=player
    // &m=app
    // &refrom=2
    // &t=1487148702298
    /**
     * 获取明星排行榜
     */
    @GET("caller?")
    Observable<HttpResult<BigStarResult>> getBigStarList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("m") String m,
            @Query("refrom") int refrom,
            @Query("t") long t
    );

    //caller?
    // a=getSearchHotRecommendList
    // &c=player
    // &m=app
    //r &refrom=2
    // &t=1487148765522
    /**
     * 获取搜索页面热门主播
     */
    @GET("caller?")
    Observable<HttpResult<List<SearchHotRecommendResult>>> getSearchHotRecommendList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("m") String m,
            @Query("refrom") int refrom,
            @Query("t") long t
    );


    //caller?
    // a=getSearchPlayerList
    // &c=player
    // &keyword=%E7%BA%AF%E5%AD%90
    // &m=app
    // &page=1
    // &page_size=20
    // &refrom=2
    // &t=1487148809580
    /**
     * 获取搜索
     */
    @GET("caller?")
    Observable<HttpResult<List<SearchPlayerResult>>> getSearchPlayerList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("keyword") String keyword,
            @Query("m") String m,
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("refrom") int refrom,
            @Query("t") long t
    );

    //caller?
    // a=currentTuhaoList&
    // c=room
    // &m=app&
    // refrom=2&
    // roomid=16214
    // &t=1487149414082
    /**
     * 获取场贡献榜
     */
    @GET("caller?")
    Observable<HttpResult<List<CurrentTuhaoResult>>> getCurrentTuhaoList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("m") String m,
            @Query("roomid") int roomid,
            @Query("refrom") int refrom,
            @Query("t") long t
    );


    //caller?
    // a=weekTuhaoList
    // &c=room
    // &refrom=2
    // &roomid=22705
    // &t=1487149495428

    /**
     * 获取周贡献榜
     */
    @GET("caller?")
    Observable<HttpResult<List<WeekTuhaoResult>>> getWeekTuhaoList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("roomid") int roomid,
            @Query("refrom") int refrom,
            @Query("t") long t
    );
    //caller?
    // a=getList
    // &c=gift
    // &is_get_guizu=1
    // &m=app
    // &refrom=2
    // &t=1487149999736
    /**
     * 获得礼物
     */
    @GET("caller?")
    Observable<HttpResult<List<GetGiftResult>>> getGiftList(
            @Query("a") String a,
            @Query("c") String c,
            @Query("is_get_guizu") int is_get_guizu,
            @Query("m") String m,
            @Query("refrom") int refrom,
            @Query("t") long t
    );

    //caller?
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
    @GET("caller?")
    Observable<HttpResult<RoomInfoResult>> getRoomInfo(
            @Query("a") String a,
            @Query("c") String c,
            @Query("m") String m,
            @Query("refrom") int refrom,
            @Query("roomid") int roomid,
            @Query("t") long t,
            @Query("type") int type
    );

}
