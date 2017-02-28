package cc.myandroid.likelive.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cc.myandroid.likelive.model.bean.User;

/**
 * Created by chenzd on 2017/2/23.
 */

public class SPUtils {
    private static SharedPreferences sp;
    private static final String USER_SP = "userInfo";

    /**
     * SharedPreferences对象初始化，中application中完成
     *
     * @param context
     */
    public static void init(Context context) {
        sp = context.getSharedPreferences(USER_SP, Context.MODE_PRIVATE);
    }

    /**
     * 将User对象保存到SharedPreferences文件中
     *
     * @param user private long id;//用户id
     *             private String nickname;//昵称
     *             private String icon;//头像
     *             private String password;//密码
     *             private String phoneNumber;//
     *             private String gender;
     */
    public static void saveUser(User user) {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.putLong("id", user.getId());
        editor.putString("nickname", user.getNickname());
        editor.putString("icon", user.getIcon());
        editor.putString("password", user.getPassword());
        editor.putString("phoneNumber", user.getPhoneNumber());
        editor.putString("gender", user.getGender());
        editor.apply();
    }

    /**
     * 从SharedPreferences获取User数据
     *
     * @return
     */
    public static User getUser() {
        long id = sp.getLong("id", -1);
        if (id == -1) return null;
        User user = new User();
        user.setId(id);
        user.setNickname(sp.getString("nickname", null));
        user.setIcon(sp.getString("icon", null));
        user.setPassword(sp.getString("password", null));
        user.setPhoneNumber(sp.getString("phoneNumber", null));
        user.setGender(sp.getString("gender", null));
        return user;
    }

    /**
     * 删除
     */
    public static void deleteUser() {
        sp.edit().clear().apply();
    }
}
