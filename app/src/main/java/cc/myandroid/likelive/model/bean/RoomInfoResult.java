package cc.myandroid.likelive.model.bean;

import java.util.List;

/**
 * Created by chenzd on 2017/2/16.
 * 直播房间详细信息gson解析实体类
 */

public class RoomInfoResult {
    /**
     * roomid : 32701
     * name : 老友互娱
     * refrom : 2
     * user_room_info : {"identity_type":0,"room_state":0}
     * player_info : {"playerid":"12613","icon":"http://img2.user.kanimg.com/usrimg/626138588/50x50?time=20170220","nickname":"兮兮太阳","is_focus":0,"level":2,"jiuwo_level":{"level":"0","score":0,"upgrade_score":100,"level_score":0},"guizu":0,"video_url":"http://flv2.jiuwo.xunlei.com/live/12613.flv","share_url":"http://jiuwo.xunlei.com/wap/room/play.html?roomid=12613","medal":[],"player_image":"http://img.jiuwo.kanimg.com/images/player_image/216/12613/178_134.jpg?t=1487587448"}
     * recommand_list : [{"playerid":"12613","nickname":"兮兮太阳","player_image":"http://img.jiuwo.kanimg.com/images/player_image/216/12613/178_134.jpg?t=1487587448","icon":"http://img2.user.kanimg.com/usrimg/626138588/50x50?time=20170220","medal":[{"medal":"planet","level":"1","reason":null,"nickname":null,"rank":null}],"roomid":"32701","is_live":1,"room_user_nums":380,"video_url":"http://flv2.jiuwo.xunlei.com/live/12613.flv"},{"playerid":"10446","nickname":"12星座-小星","player_image":"http://img.jiuwo.kanimg.com/images/player_image/111/10446/178_134.jpg?t=1487586243","icon":"http://img2.user.kanimg.com/usrimg/564203513/50x50?time=20170220","medal":[{"medal":"fansLv","level":"5","reason":"小星","nickname":"12星座-星儿","rank":"33"},{"medal":"planet","level":"14","reason":null,"nickname":null,"rank":null},{"medal":"cosmos","level":"1","reason":null,"nickname":null,"rank":null},{"medal":"flowerLv","level":"3","reason":null,"nickname":null,"rank":null},{"medal":"act15LvE","level":"1","reason":null,"nickname":null,"rank":null},{"medal":"giftLv","level":"22","reason":null,"nickname":null,"rank":null},{"medal":"act17LvA","level":"1","reason":null,"nickname":null,"rank":null}],"roomid":"21501","is_live":1,"room_user_nums":844,"video_url":"http://flv.jiuwo.xunlei.com/live/10446.flv"},{"playerid":"11543","nickname":"紫萱 不强求不挽留","player_image":"http://img.jiuwo.kanimg.com/images/player_image/71/11543/178_134.jpg?t=1487588886","icon":"http://img2.user.kanimg.com/usrimg/589762839/50x50?time=20170220","medal":[{"medal":"planet","level":"2","reason":null,"nickname":null,"rank":null}],"roomid":"15406","is_live":1,"room_user_nums":107,"video_url":"http://flv2.jiuwo.xunlei.com/live/11543.flv"},{"playerid":"12666","nickname":"你进来我跳舞~十一","player_image":"http://img.jiuwo.kanimg.com/images/player_image/220/12666/178_134.jpg?t=1487584203","icon":"http://img2.user.kanimg.com/usrimg/629573637/50x50?time=20170220","medal":[{"medal":"planet","level":"1","reason":null,"nickname":null,"rank":null}],"roomid":"33101","is_live":1,"room_user_nums":1220,"video_url":"http://flv2.jiuwo.xunlei.com/live/12666.flv"}]
     * free_times : 0
     * share_url : http://jiuwo.xunlei.com/wap/room/play.html?roomid=32701
     * share_title : 酒窝直播-真人娱乐互动直播
     * share_description : 【You、妖精悠米】正在酒窝直播，邀请你来看>>
     */

    private String roomid;
    private String name;
    private String refrom;
    private UserRoomInfoBean user_room_info;
    private PlayerInfoBean player_info;
    private int free_times;
    private String share_url;
    private String share_title;
    private String share_description;
    private List<RecommandListBean> recommand_list;

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefrom() {
        return refrom;
    }

    public void setRefrom(String refrom) {
        this.refrom = refrom;
    }

    public UserRoomInfoBean getUser_room_info() {
        return user_room_info;
    }

    public void setUser_room_info(UserRoomInfoBean user_room_info) {
        this.user_room_info = user_room_info;
    }

    public PlayerInfoBean getPlayer_info() {
        return player_info;
    }

    public void setPlayer_info(PlayerInfoBean player_info) {
        this.player_info = player_info;
    }

    public int getFree_times() {
        return free_times;
    }

    public void setFree_times(int free_times) {
        this.free_times = free_times;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_description() {
        return share_description;
    }

    public void setShare_description(String share_description) {
        this.share_description = share_description;
    }

    public List<RecommandListBean> getRecommand_list() {
        return recommand_list;
    }

    public void setRecommand_list(List<RecommandListBean> recommand_list) {
        this.recommand_list = recommand_list;
    }

    public static class UserRoomInfoBean {
        /**
         * identity_type : 0
         * room_state : 0
         */

        private int identity_type;
        private int room_state;

        public int getIdentity_type() {
            return identity_type;
        }

        public void setIdentity_type(int identity_type) {
            this.identity_type = identity_type;
        }

        public int getRoom_state() {
            return room_state;
        }

        public void setRoom_state(int room_state) {
            this.room_state = room_state;
        }
    }

    public static class PlayerInfoBean {
        /**
         * playerid : 12613
         * icon : http://img2.user.kanimg.com/usrimg/626138588/50x50?time=20170220
         * nickname : 兮兮太阳
         * is_focus : 0
         * level : 2
         * jiuwo_level : {"level":"0","score":0,"upgrade_score":100,"level_score":0}
         * guizu : 0
         * video_url : http://flv2.jiuwo.xunlei.com/live/12613.flv
         * share_url : http://jiuwo.xunlei.com/wap/room/play.html?roomid=12613
         * medal : []
         * player_image : http://img.jiuwo.kanimg.com/images/player_image/216/12613/178_134.jpg?t=1487587448
         */

        private String playerid;
        private String icon;
        private String nickname;
        private int is_focus;
        private int level;
        private JiuwoLevelBean jiuwo_level;
        private int guizu;
        private String video_url;
        private String share_url;
        private String player_image;
        private List<?> medal;

        public String getPlayerid() {
            return playerid;
        }

        public void setPlayerid(String playerid) {
            this.playerid = playerid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getIs_focus() {
            return is_focus;
        }

        public void setIs_focus(int is_focus) {
            this.is_focus = is_focus;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public JiuwoLevelBean getJiuwo_level() {
            return jiuwo_level;
        }

        public void setJiuwo_level(JiuwoLevelBean jiuwo_level) {
            this.jiuwo_level = jiuwo_level;
        }

        public int getGuizu() {
            return guizu;
        }

        public void setGuizu(int guizu) {
            this.guizu = guizu;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getPlayer_image() {
            return player_image;
        }

        public void setPlayer_image(String player_image) {
            this.player_image = player_image;
        }

        public List<?> getMedal() {
            return medal;
        }

        public void setMedal(List<?> medal) {
            this.medal = medal;
        }

        public static class JiuwoLevelBean {
            /**
             * level : 0
             * score : 0
             * upgrade_score : 100
             * level_score : 0
             */

            private String level;
            private int score;
            private int upgrade_score;
            private int level_score;

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getUpgrade_score() {
                return upgrade_score;
            }

            public void setUpgrade_score(int upgrade_score) {
                this.upgrade_score = upgrade_score;
            }

            public int getLevel_score() {
                return level_score;
            }

            public void setLevel_score(int level_score) {
                this.level_score = level_score;
            }
        }
    }

    public static class RecommandListBean {
        /**
         * playerid : 12613
         * nickname : 兮兮太阳
         * player_image : http://img.jiuwo.kanimg.com/images/player_image/216/12613/178_134.jpg?t=1487587448
         * icon : http://img2.user.kanimg.com/usrimg/626138588/50x50?time=20170220
         * medal : [{"medal":"planet","level":"1","reason":null,"nickname":null,"rank":null}]
         * roomid : 32701
         * is_live : 1
         * room_user_nums : 380
         * video_url : http://flv2.jiuwo.xunlei.com/live/12613.flv
         */

        private String playerid;
        private String nickname;
        private String player_image;
        private String icon;
        private String roomid;
        private int is_live;
        private int room_user_nums;
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

        public int getRoom_user_nums() {
            return room_user_nums;
        }

        public void setRoom_user_nums(int room_user_nums) {
            this.room_user_nums = room_user_nums;
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

        public static class MedalBean {
            /**
             * medal : planet
             * level : 1
             * reason : null
             * nickname : null
             * rank : null
             */

            private String medal;
            private String level;
            private Object reason;
            private Object nickname;
            private Object rank;

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

            public Object getReason() {
                return reason;
            }

            public void setReason(Object reason) {
                this.reason = reason;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getRank() {
                return rank;
            }

            public void setRank(Object rank) {
                this.rank = rank;
            }
        }
    }
}
