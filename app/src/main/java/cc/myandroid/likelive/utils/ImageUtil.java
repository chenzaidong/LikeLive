package cc.myandroid.likelive.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import cc.myandroid.likelive.R;

/**
 * Created by chenzd on 2017/2/23.
 * 用于配置图片加载参数
 */

public class ImageUtil {
    public static final int CIRCLE = 1;//圆形
    public static final int ROUND = 2;//圆角

    /**
     * 设置展位图和错误图
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void laodImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.error)
                .into(imageView);
    }

    public static void laodImage(Context context, String url, ImageView imageView, int type,int round) {
        BitmapTransformation bitmapTransformation = null;
        switch (type) {
            case CIRCLE:
                bitmapTransformation = new GlideCircleTransform(context);
                break;
            case ROUND:
                bitmapTransformation = new GlideRoundTransform(context,round);
                break;
        }
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.error)
                .transform(bitmapTransformation)
                .into(imageView);
    }








}

/**
 * 圆形转换器
 */
class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;

        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}


/**
 * Created by qly on 2016/6/22.
 * 将图片转化为圆角
 * 构造中第二个参数定义半径
 */
class GlideRoundTransform extends BitmapTransformation {

    private static float radius = 0f;

    public GlideRoundTransform(Context context) {
        this(context, 4);
    }

    public GlideRoundTransform(Context context, int dp) {
        super(context);
        this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return roundCrop(pool, toTransform);
    }

    private static Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
        canvas.drawRoundRect(rectF, radius, radius, paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName() + Math.round(radius);
    }
}

