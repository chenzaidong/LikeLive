package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 获取礼物gson解析实体类
 */

public class GetGiftResult {
    /**
     * giftid : 38
     * name : 巧克力
     * score : 4
     * gift_type : 1
     * coin : 2
     * url : http://img.jiuwo.kanimg.com/web/app/1.0.1/gift/chocolate.png?v=2.4.1
     * memo : 填满心意
     * memo2 : 赠送特定礼物数值将触发炫酷特效</em>
     * even_num : 1
     * effect :
     * is_guizu : 0
     * guizu_level :
     * wine_ratio : 1.000
     * wineroll_ratio : 1.000
     * guizubag_ratio : 1.000
     * weekstar_guanming_deadline : 2017-02-22 00:00:01
     * weekstar_guanming : wolf★小星专属冠名
     * default_send_num : 10
     * effect_list : []
     * gift_level : 0
     * stock : 0
     */

    private int giftid;
    private String name;
    private int score;
    private int gift_type;
    private int coin;
    private String url;
    private String memo;
    private String memo2;
    private int even_num;
    private String effect;
    private int is_guizu;
    private String guizu_level;
    private String wine_ratio;
    private String wineroll_ratio;
    private String guizubag_ratio;
    private String weekstar_guanming_deadline;
    private String weekstar_guanming;
    private String default_send_num;
    private int gift_level;
    private int stock;
    private List<?> effect_list;

    public int getGiftid() {
        return giftid;
    }

    public void setGiftid(int giftid) {
        this.giftid = giftid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGift_type() {
        return gift_type;
    }

    public void setGift_type(int gift_type) {
        this.gift_type = gift_type;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo2() {
        return memo2;
    }

    public void setMemo2(String memo2) {
        this.memo2 = memo2;
    }

    public int getEven_num() {
        return even_num;
    }

    public void setEven_num(int even_num) {
        this.even_num = even_num;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public int getIs_guizu() {
        return is_guizu;
    }

    public void setIs_guizu(int is_guizu) {
        this.is_guizu = is_guizu;
    }

    public String getGuizu_level() {
        return guizu_level;
    }

    public void setGuizu_level(String guizu_level) {
        this.guizu_level = guizu_level;
    }

    public String getWine_ratio() {
        return wine_ratio;
    }

    public void setWine_ratio(String wine_ratio) {
        this.wine_ratio = wine_ratio;
    }

    public String getWineroll_ratio() {
        return wineroll_ratio;
    }

    public void setWineroll_ratio(String wineroll_ratio) {
        this.wineroll_ratio = wineroll_ratio;
    }

    public String getGuizubag_ratio() {
        return guizubag_ratio;
    }

    public void setGuizubag_ratio(String guizubag_ratio) {
        this.guizubag_ratio = guizubag_ratio;
    }

    public String getWeekstar_guanming_deadline() {
        return weekstar_guanming_deadline;
    }

    public void setWeekstar_guanming_deadline(String weekstar_guanming_deadline) {
        this.weekstar_guanming_deadline = weekstar_guanming_deadline;
    }

    public String getWeekstar_guanming() {
        return weekstar_guanming;
    }

    public void setWeekstar_guanming(String weekstar_guanming) {
        this.weekstar_guanming = weekstar_guanming;
    }

    public String getDefault_send_num() {
        return default_send_num;
    }

    public void setDefault_send_num(String default_send_num) {
        this.default_send_num = default_send_num;
    }

    public int getGift_level() {
        return gift_level;
    }

    public void setGift_level(int gift_level) {
        this.gift_level = gift_level;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<?> getEffect_list() {
        return effect_list;
    }

    public void setEffect_list(List<?> effect_list) {
        this.effect_list = effect_list;
    }

    @Override
    public String toString() {
        return "GetGiftResult{" +
                "giftid=" + giftid +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", gift_type=" + gift_type +
                ", coin=" + coin +
                ", url='" + url + '\'' +
                ", memo='" + memo + '\'' +
                ", memo2='" + memo2 + '\'' +
                ", even_num=" + even_num +
                ", effect='" + effect + '\'' +
                ", is_guizu=" + is_guizu +
                ", guizu_level='" + guizu_level + '\'' +
                ", wine_ratio='" + wine_ratio + '\'' +
                ", wineroll_ratio='" + wineroll_ratio + '\'' +
                ", guizubag_ratio='" + guizubag_ratio + '\'' +
                ", weekstar_guanming_deadline='" + weekstar_guanming_deadline + '\'' +
                ", weekstar_guanming='" + weekstar_guanming + '\'' +
                ", default_send_num='" + default_send_num + '\'' +
                ", gift_level=" + gift_level +
                ", stock=" + stock +
                ", effect_list=" + effect_list +
                '}';
    }
}
