package com.xwh.frame.utils.dialog.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xwh.frame.R;
import com.xwh.frame.utils.SystemUtil;
import com.xwh.frame.utils.dialog.animatorbuilder.StarBuilder;
import com.xwh.frame.utils.dialog.drawable.LoadingDrawable;


/**
 * Created by xwh on 2017/11/23.
 */

public class LoadingImageView extends android.support.v7.widget.AppCompatImageView {

    private LoadingDrawable mDrawable;

    public LoadingImageView(Context context) {
        this(context, null);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        try {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LoadingImageView);
            int color = ta.getColor(R.styleable.LoadingImageView_color,
                    SystemUtil.getColor(context, R.color.darkturquoise));
            ta.recycle();
            setLoadingBuilder(new StarBuilder());
            setColorFilter(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLoadingBuilder(StarBuilder starBuilder) {
        mDrawable = new LoadingDrawable(starBuilder);
        mDrawable.initParams(getContext());
        setImageDrawable(mDrawable);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        final boolean visible = visibility == VISIBLE && getVisibility() == VISIBLE;
        if (visible) {
            startAnimation();
        } else {
            stopAnimation();
        }
    }

    private void startAnimation() {
        if (mDrawable != null) {
            mDrawable.start();
        }
    }

    private void stopAnimation() {
        if (mDrawable != null) {
            mDrawable.stop();
        }
    }

}
