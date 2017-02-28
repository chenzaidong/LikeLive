package cc.myandroid.likelive.constant;

/**
 * Created by chenzd on 2017/2/15.
 * 网络请求URL
 */

public class URLConstant {
    /**
     *  根地址
     */
    public static final String BASE_URL="http://dynamic.jiuwo.xunlei.com/";
    /**
     * 广告地址
     */
    public static final String BANNER="/a=getBannerInfo&c=site&m=app&refrom=2";
    /**
     * 最新
     */
    public static final String TOTAL_START_URL="/a=getTodayStarList&c=player&m=app";

    /**
     * 明星排行榜
     */
    public static final String BIGSTAR_URL="/a=getBigStarList&c=player&m=app&refrom=2";

    /**
     * 搜索热播推荐
     */
    public static final String SEARCHHOT_URL="/a=getSearchHotRecommendList&c=player&m=app&refrom=2";

    /**
     * 搜索
     */
    public static final String SEARCH_URL="/a=getSearchPlayerList&c=player";

    /**
     * 场贡献榜
     */
    public static final String CURRENTTUHAO_URL="/a=currentTuhaoList&c=room&m=app&refrom=2";

    /**
     * 周贡献榜
     */
    public static final String WEEKTUHAO_URL="/a=weekTuhaoList&c=room&refrom=2";

    /**
     * 礼物
     */
    public static final String GIFT_URL="/a=getList&c=gift&is_get_guizu=1&m=app&refrom=2";
    /**
     * 直播房间信息
     */
    public static final String ROOM_INFO_URL="/a=getInfo&c=room&m=app&refrom=2";
}
