package cc.myandroid.likelive.base;

import android.app.Application;
import android.content.Context;

import com.baidu.cyberplayer.core.BVideoView;

import cc.myandroid.likelive.db.DBManager;
import cc.myandroid.likelive.utils.SPUtils;
import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;

/**
 * Created by chenzd on 2017/2/15.
 */

public class MyApp extends Application {
    private static MyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /**
         * 百度VideoView初始化
         */
        BVideoView.setAK("e2d47efc98ff4cd58a0a0dfd3d0f762d");
        /**
         * 初始化ShareSDK
         */
        ShareSDK.initSDK(this);

        /**
         *
         * **/
        SMSSDK.initSDK(this, "1b9b65f8cc5c4", "7f4695796c16229ae6c8bad33806ca1d");

        /**
         * 数据库初始化
         */
        DBManager.init(this);

        /**
         * SharedPreferences对象初始化
         */
        SPUtils.init(this);
    }

    public static Context getInstance() {
        return instance;
    }
}
