package cc.myandroid.likelive.utils;

import android.os.Looper;

import com.bumptech.glide.Glide;

import java.io.File;
import java.math.BigDecimal;

import cc.myandroid.likelive.base.MyApp;
import cc.myandroid.likelive.callback.Callback;

/**
 * Created by chenzd on 2017/2/27.
 * Glide缓存工具类
 */

public class ImageCatchUtil {

    private static final String CHACHE="image_cache";

    private static ImageCatchUtil inst;
    public static ImageCatchUtil getInstance() {
        if (inst == null) {
            inst = new ImageCatchUtil();
        }
        return inst;
    }

    /**
     * 清除图片磁盘缓存 异步处理
     */
    public void clearImageDiskCache(final Callback<String> callback) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(MyApp.getInstance()).clearDiskCache();
                        callback.success("OK");
                    }
                }).start();
            } else {
                Glide.get(MyApp.getInstance()).clearDiskCache();
                callback.success("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 清除图片内存缓存
     */
    public void clearImageMemoryCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(MyApp.getInstance()).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除图片所有缓存
     */
    public void clearImageAllCache(Callback<String> callback) {
        clearImageDiskCache(callback);
        clearImageMemoryCache();
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public String getCacheSize() {
        try {
            L.e(MyApp.getInstance().getExternalCacheDir().getPath());
            return getFormatSize(getFolderSize(new File(MyApp.getInstance().getCacheDir() + "/"+CHACHE)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    private static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
