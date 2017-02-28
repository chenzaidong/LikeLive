package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 广告条gson解析实体类
 */

public class BannerResult {
    /**
     * show : 1
     * list : [{"img_url":"http://img.jiuwo.kanimg.com/images/banner/201701251618497100.jpg","title":"2.16上线新月 1号位","type":"1","data":{"playerid":"10591","nickname":"尹新月biubiubiu","roomid":"10801","is_live":0,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/13/10591/519_389.jpg?t=1487123883","icon":"http://img2.user.kanimg.com/usrimg/567543209/50x50?time=20170219","tag":["萝莉","美女"],"tag2":[{"name":"萝莉","color":"#fbd6f8","text_rgb":"#f539e2"},{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"room_user_nums":7,"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/10591.flv"}},{"img_url":"http://img.jiuwo.kanimg.com/images/banner/201701091703085392.jpg","title":" 2.13 酒窝直播\u2014文明公约 5号位","type":"2","data":{"url":"http://jiuwo.xunlei.com/wap/info/civilization.html?playerid="}},{"img_url":"http://img.jiuwo.kanimg.com/images/banner/201701042038096854.jpg","title":"2.13 公众号充值 6号位","type":"2","data":{"url":"http://jiuwo.xunlei.com/wap/act/recharge.html?playerid="}}]
     */

    private int show;
    private List<ListBean> list;

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BannerResult{" +
                "show=" + show +
                ", list=" + list +
                '}';
    }

    public static class ListBean {
        /**
         * img_url : http://img.jiuwo.kanimg.com/images/banner/201701251618497100.jpg
         * title : 2.16上线新月 1号位
         * type : 1
         * data : {"playerid":"10591","nickname":"尹新月biubiubiu","roomid":"10801","is_live":0,"player_image":"http://img.jiuwo.kanimg.com/images/player_image/13/10591/519_389.jpg?t=1487123883","icon":"http://img2.user.kanimg.com/usrimg/567543209/50x50?time=20170219","tag":["萝莉","美女"],"tag2":[{"name":"萝莉","color":"#fbd6f8","text_rgb":"#f539e2"},{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}],"room_user_nums":7,"platform":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/10591.flv"}
         */

        private String img_url;
        private String title;
        private String type;
        private DataBean data;

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * playerid : 10591
             * nickname : 尹新月biubiubiu
             * roomid : 10801
             * is_live : 0
             * player_image : http://img.jiuwo.kanimg.com/images/player_image/13/10591/519_389.jpg?t=1487123883
             * icon : http://img2.user.kanimg.com/usrimg/567543209/50x50?time=20170219
             * tag : ["萝莉","美女"]
             * tag2 : [{"name":"萝莉","color":"#fbd6f8","text_rgb":"#f539e2"},{"name":"美女","color":"#fce2ea","text_rgb":"#fa3a70"}]
             * room_user_nums : 7
             * platform : 0
             * video_url : http://flv2.jiuwo.xunlei.com/live/10591.flv
             */

            private String playerid;
            private String nickname;
            private String roomid;
            private int is_live;
            private String player_image;
            private String icon;
            private int room_user_nums;
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

            public int getRoom_user_nums() {
                return room_user_nums;
            }

            public void setRoom_user_nums(int room_user_nums) {
                this.room_user_nums = room_user_nums;
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
                 * name : 萝莉
                 * color : #fbd6f8
                 * text_rgb : #f539e2
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
}
