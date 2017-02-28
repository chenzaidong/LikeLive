package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 场贡献榜gson解析实体类
 */

public class CurrentTuhaoResult {
    /**
     * userid : 610492274
     * nickname : 阿拉蕾宝宝
     * coin : 132
     * icon : http://img2.user.kanimg.com/usrimg/610492274/50x50?time=20170219
     * level : 5
     * guizu : 0
     * vip : 0
     * shouhu : 0
     * medal : [{"medal":"fansLv","level":5,"reason":"慕慕","nickname":"小慕慕||寄几玩寄几的～","rank":"6"}]
     */

    private String userid;
    private String nickname;
    private int coin;
    private String icon;
    private int level;
    private int guizu;
    private int vip;
    private int shouhu;
    private List<MedalBean> medal;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGuizu() {
        return guizu;
    }

    public void setGuizu(int guizu) {
        this.guizu = guizu;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getShouhu() {
        return shouhu;
    }

    public void setShouhu(int shouhu) {
        this.shouhu = shouhu;
    }

    public List<MedalBean> getMedal() {
        return medal;
    }

    public void setMedal(List<MedalBean> medal) {
        this.medal = medal;
    }

    @Override
    public String toString() {
        return "CurrentTuhaoResult{" +
                "userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", coin=" + coin +
                ", icon='" + icon + '\'' +
                ", level=" + level +
                ", guizu=" + guizu +
                ", vip=" + vip +
                ", shouhu=" + shouhu +
                ", medal=" + medal +
                '}';
    }

    public static class MedalBean {
        /**
         * medal : fansLv
         * level : 5
         * reason : 慕慕
         * nickname : 小慕慕||寄几玩寄几的～
         * rank : 6
         */

        private String medal;
        private int level;
        private String reason;
        private String nickname;
        private String rank;

        public String getMedal() {
            return medal;
        }

        public void setMedal(String medal) {
            this.medal = medal;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }
    }
}
