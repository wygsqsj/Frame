package com.xwh.frame.utils.widget.dialog.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.xwh.frame.utils.widget.dialog.animatorbuilder.BaseBuilder;


/**
 * Created by xwh on 2017/11/23.
 */

public class LoadingDrawable extends Drawable implements Animatable {

    private final BaseBuilder mBuilder;

    public LoadingDrawable(BaseBuilder mBuilder) {
        this.mBuilder = mBuilder;
        this.mBuilder.setCallback(new Callback() {
            @Override
            public void invalidateDrawable(Drawable d) {
                invalidateSelf();
            }

            @Override
            public void scheduleDrawable(Drawable d, Runnable what, long when) {
                scheduleSelf(what, when);
            }

            @Override
            public void unscheduleDrawable(Drawable d, Runnable what) {
                unscheduleSelf(what);
            }
        });
    }

    public void initParams(Context context) {
        if (mBuilder != null) {
            mBuilder.init(context);
            mBuilder.initParams(context);
        }
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (!getBounds().isEmpty()) {
            this.mBuilder.draw(canvas);
        }
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {
        this.mBuilder.setAlpha(alpha);
    }

    @Override
    public void start() {
        this.mBuilder.start();
    }

    @Override
    public void stop() {
        this.mBuilder.stop();
    }

    @Override
    public boolean isRunning() {
        return this.mBuilder.isRunning();
    }


    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.mBuilder.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight()
    {
        return (int) this.mBuilder.getIntrinsicHeight();
    }

    @Override
    public int getIntrinsicWidth()
    {
        return (int) this.mBuilder.getIntrinsicWidth();
    }
}
