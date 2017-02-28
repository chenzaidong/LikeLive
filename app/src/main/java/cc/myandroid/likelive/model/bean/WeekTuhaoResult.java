package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 周贡献榜gson解析实体类
 */

public class WeekTuhaoResult {
    /**
     * userid : 620259864
     * nickname : 雨欣家@张三丰
     * coin : 1982
     * icon : http://img2.user.kanimg.com/usrimg/620259864/50x50?time=20170218
     * level : 4
     * guizu : 0
     * vip : 0
     * shouhu : 0
     * medal : [{"medal":"fansLv","level":"1","reason":"雨欣","nickname":"雨欣 陪你过情人节","rank":"28"}]
     */

    private String userid;
    private String nickname;
    private int coin;
    private String icon;
    private String level;
    private String guizu;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getGuizu() {
        return guizu;
    }

    public void setGuizu(String guizu) {
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
        return "WeekTuhaoResult{" +
                "userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", coin=" + coin +
                ", icon='" + icon + '\'' +
                ", level='" + level + '\'' +
                ", guizu='" + guizu + '\'' +
                ", vip=" + vip +
                ", shouhu=" + shouhu +
                ", medal=" + medal +
                '}';
    }

    public static class MedalBean {
        /**
         * medal : fansLv
         * level : 1
         * reason : 雨欣
         * nickname : 雨欣 陪你过情人节
         * rank : 28
         */

        private String medal;
        private String level;
        private String reason;
        private String nickname;
        private String rank;

        public String getMedal() {
            return medal;
        }

        public void setMedal(String medal) {
            this.medal = medal;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
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
