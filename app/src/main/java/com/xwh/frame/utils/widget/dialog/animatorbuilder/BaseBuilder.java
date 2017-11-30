package com.xwh.frame.utils.widget.dialog.animatorbuilder;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.FloatRange;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

/**
 * Created by xwh on 2017/11/23.
 */

public abstract class BaseBuilder implements ValueAnimator.AnimatorUpdateListener,
        Animator.AnimatorListener {
    protected static final float DEFAULT_SIZE = 56.0f;
    protected static final long ANIMATION_START_DELAY = 333;
    protected static final long ANIMATION_DURATION = 1333;

    private float mAllSize;
    private float mViewWidth;
    private float mViewHeight;

    private Drawable.Callback mCallback;
    private ValueAnimator mFloatValueAnimator;
    private long mDuration;

    public void init(Context context) {
        mAllSize = dip2px(context, DEFAULT_SIZE * 0.5f - 10);
        mViewWidth = dip2px(context, DEFAULT_SIZE);
        mViewHeight = dip2px(context, DEFAULT_SIZE);
        mDuration = ANIMATION_DURATION;

        initAnimators();
    }

    private void initAnimators() {
        mFloatValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        mFloatValueAnimator.setRepeatCount(Animation.INFINITE);
        mFloatValueAnimator.setDuration(mDuration);
        mFloatValueAnimator.setStartDelay(ANIMATION_START_DELAY);
        mFloatValueAnimator.setInterpolator(new LinearInterpolator());
    }

    public abstract void initParams(Context context);

    protected abstract void onDraw(Canvas canvas);

    public abstract void setAlpha(int alpha);

    protected abstract void prepareStart(ValueAnimator floatValueAnimator);

    protected abstract void prepareEnd();

    protected abstract void computeUpdateValue(ValueAnimator animation, @FloatRange(from = 0.0, to = 1.0) float animatedValue);

    public abstract void setColorFilter(ColorFilter colorFilter);

    public void setCallback(Drawable.Callback callback) {
        this.mCallback = callback;
    }

    public void draw(Canvas canvas) {
        onDraw(canvas);
    }

    public void start() {
        if (mFloatValueAnimator.isStarted()) {
            return;
        }
        mFloatValueAnimator.addUpdateListener(this);
        mFloatValueAnimator.addListener(this);
        mFloatValueAnimator.setRepeatCount(Animation.INFINITE);
        mFloatValueAnimator.setDuration(mDuration);
        prepareStart(mFloatValueAnimator);
        mFloatValueAnimator.start();
    }

    public void stop() {
        mFloatValueAnimator.removeAllUpdateListeners();
        mFloatValueAnimator.removeAllListeners();
        mFloatValueAnimator.setRepeatCount(0);
        mFloatValueAnimator.setDuration(0);
        prepareEnd();
        mFloatValueAnimator.end();
    }

    public boolean isRunning() {
        return mFloatValueAnimator.isRunning();
    }

    @Override
    public final void onAnimationUpdate(ValueAnimator animation) {
        computeUpdateValue(animation, (float) animation.getAnimatedValue());
        invalidateSelf();
    }

    private void invalidateSelf() {
        if (mCallback != null) {
            mCallback.invalidateDrawable(null);
        }
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    public float getIntrinsicHeight() {
        return mViewHeight;
    }

    public float getIntrinsicWidth() {
        return mViewWidth;
    }

    protected final float getViewCenterX() {
        return getIntrinsicWidth() * 0.5f;
    }

    protected final float getViewCenterY() {
        return getIntrinsicHeight() * 0.5f;
    }

    protected final float getAllSize() {
        return mAllSize;
    }

    protected static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return dpValue * scale;
    }
}
