package cc.myandroid.likelive.utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by chenzd on 2017/2/23.
 * 自定义Glide的一些参数，缓存位置，大小，图片解码格式等
 */

public class MyGlideModule implements GlideModule {
    private static final int MAX_DISK_CACHE_SIZE=300*1024 * 1024 ;//最大磁盘缓存
    private static int MAX_MERRORY= (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
    private static int MEMORY_CACHE_SIZE = MAX_MERRORY/8;//设置图片内存缓存占用八分之一
    @Override public void applyOptions(Context context, GlideBuilder builder) {

        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE));
        builder.setBitmapPool(new LruBitmapPool(MEMORY_CACHE_SIZE));

        //设置磁盘缓存位置及大小 私有内部缓存目录，图片查看器无法搜索到，放置在应用程序缓存
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,"image_cache",MAX_DISK_CACHE_SIZE));
        //设置BitmapPool缓存内存大小
        //设置图片解码格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}