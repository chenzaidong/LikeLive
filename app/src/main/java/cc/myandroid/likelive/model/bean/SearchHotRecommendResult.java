package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 搜索页面主播推荐gson解析实体类
 */

public class SearchHotRecommendResult {
    /**
     * playerid : 12333
     * nickname : 宠儿是个毒妮儿
     * roomid : 24403
     * is_live : 1
     * icon : http://img2.user.kanimg.com/usrimg/608676448/50x50?time=20170219
     * is_focus : 0
     * focus_nums : 12253
     * is_first : 0
     * medal : [{"medal":"fansLv","level":"6","reason":"宠儿","nickname":"宠儿","rank":"7"},{"medal":"planet","level":"12","reason":null,"nickname":null,"rank":null},{"medal":"cosmos","level":"1","reason":null,"nickname":null,"rank":null},{"medal":"act17LvA","level":"1","reason":null,"nickname":null,"rank":null}]
     * level : 8
     * platform : 0
     * video_url : http://flv.jiuwo.xunlei.com/live/12333.flv
     */

    private String playerid;
    private String nickname;
    private String roomid;
    private int is_live;
    private String icon;
    private int is_focus;
    private int focus_nums;
    private int is_first;
    private int level;
    private int platform;
    private String video_url;
    private List<MedalBean> medal;

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public int getIs_live() {
        return is_live;
    }

    public void setIs_live(int is_live) {
        this.is_live = is_live;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(int is_focus) {
        this.is_focus = is_focus;
    }

    public int getFocus_nums() {
        return focus_nums;
    }

    public void setFocus_nums(int focus_nums) {
        this.focus_nums = focus_nums;
    }

    public int getIs_first() {
        return is_first;
    }

    public void setIs_first(int is_first) {
        this.is_first = is_first;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public List<MedalBean> getMedal() {
        return medal;
    }

    public void setMedal(List<MedalBean> medal) {
        this.medal = medal;
    }

    @Override
    public String toString() {
        return "SearchHotRecommendResult{" +
                "playerid='" + playerid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", roomid='" + roomid + '\'' +
                ", is_live=" + is_live +
                ", icon='" + icon + '\'' +
                ", is_focus=" + is_focus +
                ", focus_nums=" + focus_nums +
                ", is_first=" + is_first +
                ", level=" + level +
                ", platform=" + platform +
                ", video_url='" + video_url + '\'' +
                ", medal=" + medal +
                '}';
    }

    public static class MedalBean {
        /**
         * medal : fansLv
         * level : 6
         * reason : 宠儿
         * nickname : 宠儿
         * rank : 7
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
