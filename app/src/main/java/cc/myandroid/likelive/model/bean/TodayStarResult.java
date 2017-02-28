package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 最新主播直播gson解析实体类
 */

public class TodayStarResult {
    /**
     * list : [{"playerid":"12491","nickname":"mp-小王子","roomid":"15417","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/71/12491/519_389.jpg?t=1484877722&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/620113326/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":1,"video_url":"http://flv2.jiuwo.xunlei.com/live/12491.flv"},{"playerid":"11540","nickname":"予曦","roomid":"16906","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/9/11540/519_389.jpg?t=1487213642&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/589784576/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":1,"video_url":"http://flv2.jiuwo.xunlei.com/live/11540.flv"},{"playerid":"12333","nickname":"宠儿是个毒妮儿","roomid":"24403","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/129/12333/519_389.jpg?t=1487479563&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/608676448/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv.jiuwo.xunlei.com/live/12333.flv"},{"playerid":"12572","nickname":"东北小虎妞-给大家拜年","roomid":"32101","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/210/12572/519_389.jpg?t=1487476682&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/623804428/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/12572.flv"},{"playerid":"11744","nickname":"霜霜","roomid":"15407","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/71/11744/519_389.jpg?t=1487481723&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/594071171/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/11744.flv"},{"playerid":"11793","nickname":"舞神语儿","roomid":"16202","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/76/11793/519_389.jpg?t=1487476803&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/595097819/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/11793.flv"},{"playerid":"10495","nickname":"荣宝宝热辣韩舞等你来嗨","roomid":"17106","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/24/10495/519_389.jpg?t=1487480643&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/564198992/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv.jiuwo.xunlei.com/live/10495.flv"},{"playerid":"11676","nickname":"刘胖子说：我有酒，你有故事吗？","roomid":"16959","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/9/11676/519_389.jpg?t=1487481723&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/592645081/50x50?time=20170219","tag":["综艺咖"],"tag2":[{"name":"综艺咖","color":"#eed0ff","text_rgb":"#c35bff"}],"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/11676.flv"},{"playerid":"11477","nickname":"小慕慕||寄几玩寄几的～","roomid":"16201","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/76/11477/519_389.jpg?t=1486557963&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/587967259/50x50?time=20170219","tag":["好声音","美女"],"tag2":[{"name":"好声音","color":"#fcf3dd","text_rgb":"#f3981c"},{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/11477.flv"},{"playerid":"11745","nickname":"缘来是小白","roomid":"15408","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/71/11745/519_389.jpg?t=1486966204&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/241650140/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":1,"video_url":"http://flv2.jiuwo.xunlei.com/live/11745.flv"},{"playerid":"12482","nickname":"AP大叔-三月休息","roomid":"25839","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/149/12482/519_389.jpg?t=1487482566&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/42031760/50x50?time=20170219","tag":["综艺咖"],"tag2":[{"name":"综艺咖","color":"#eed0ff","text_rgb":"#c35bff"}],"platform":1,"video_url":"http://flv2.jiuwo.xunlei.com/live/12482.flv"},{"playerid":"11886","nickname":"田心ゆ萌妹咿呀哟！","roomid":"15401","is_live":1,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/71/11886/519_389.jpg?t=1487482686&v1=1029","icon":"http://img2.user.kanimg.com/usrimg/597507938/50x50?time=20170219","tag":["美女"],"tag2":[{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/11886.flv"}]
     * total : 1515
     */

    private int total;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "TodayStarResult{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }

    public static class ListBean {
        /**
         * playerid : 12491
         * nickname : mp-小王子
         * roomid : 15417
         * is_live : 1
         * player_image : http://img.jiuwo.kanimg.com/images/player_image/71/12491/519_389.jpg?t=1484877722&v1=1029
         * icon : http://img2.user.kanimg.com/usrimg/620113326/50x50?time=20170219
         * tag : ["美女"]
         * tag2 : [{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}]
         * platform : 1
         * video_url : http://flv2.jiuwo.xunlei.com/live/12491.flv
         */

        private String playerid;
        private String nickname;
        private String roomid;
        private int is_live;
        private String player_image;
        private String icon;
        private int platform;
        private String video_url;
        private List<String> tag;
        private List<Tag2Bean> tag2;

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

        public String getPlayer_image() {
            return player_image;
        }

        public void setPlayer_image(String player_image) {
            this.player_image = player_image;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public List<Tag2Bean> getTag2() {
            return tag2;
        }

        public void setTag2(List<Tag2Bean> tag2) {
            this.tag2 = tag2;
        }

        public static class Tag2Bean {
            /**
             * name : 美女
             * color : #fce2ea
             * text_rgb : #fa3a70
             */

            private String name;
            private String color;
            private String text_rgb;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getText_rgb() {
                return text_rgb;
            }

            public void setText_rgb(String text_rgb) {
                this.text_rgb = text_rgb;
            }
        }
    }
}
