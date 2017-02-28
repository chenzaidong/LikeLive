package cc.myandroid.likelive.ui.customview;

/**
 * Created by chenzd on 2017/2/20.
 */

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 *
 * 一直走马灯效果控件
 *
 * @author zuolongsnail
 */
public class CustomMarqueeTextView extends TextView {

    public CustomMarqueeTextView(Context context) {
        super(context);
        createView();
    }

    public CustomMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        createView();
    }

    public CustomMarqueeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        createView();
    }

    private void createView() {
        setEllipsize(TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction,
                                  Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused) {
            super.onWindowFocusChanged(focused);
        }
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}