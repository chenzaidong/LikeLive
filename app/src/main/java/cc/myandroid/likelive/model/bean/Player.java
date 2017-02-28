package cc.myandroid.likelive.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created bychenzd on 2017/2/23.
 * 关注的主播对象实体类
 */
@Entity
public class Player {
    @Id(autoincrement = true)
    private Long id;//id
    private String playerId;//player_id
    private String nickname;
    private String roomid;//房间id
    private String icon;//头像
    private long userId;//对应的User ID
    @Generated(hash = 1357543665)
    public Player(Long id, String playerId, String nickname, String roomid,
            String icon, long userId) {
        this.id = id;
        this.playerId = playerId;
        this.nickname = nickname;
        this.roomid = roomid;
        this.icon = icon;
        this.userId = userId;
    }
    @Generated(hash = 30709322)
    public Player() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPlayerId() {
        return this.playerId;
    }
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getRoomid() {
        return this.roomid;
    }
    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
}
