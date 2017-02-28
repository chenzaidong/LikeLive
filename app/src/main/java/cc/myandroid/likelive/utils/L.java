package cc.myandroid.likelive.utils;

import android.util.Log;

/**
 * Created by chenzd on 2017/2/19.
 * 日志打印工具类
 */

public class L {
    private static final String TAG = "---";
    private static final boolean LOCK = true;

    public static void i(String msg) {
        if (LOCK) {
            Log.i(TAG, msg);
        }
    }
    public static void e(String msg) {
        if (LOCK) {
            Log.e(TAG, msg);
        }
    }
}
