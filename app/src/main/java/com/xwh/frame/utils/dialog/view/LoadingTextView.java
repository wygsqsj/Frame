package com.xwh.frame.utils.dialog.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.xwh.frame.R;
import com.xwh.frame.utils.dialog.animatorbuilder.TextBuilder;
import com.xwh.frame.utils.dialog.drawable.LoadingDrawable;


/**
 * Created by xwh on 2017/11/23.
 */

public class LoadingTextView extends android.support.v7.widget.AppCompatImageView {

    private String mText;
    private TextBuilder textBuilder;
    private LoadingDrawable mDrawable;

    public LoadingTextView(Context context) {
        this(context, null);
    }

    public LoadingTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.LoadingTextView);
        String text = typedArray.getString(R.styleable.LoadingTextView_text);
        typedArray.recycle();
        if (!TextUtils.isEmpty(text)) {
            this.mText = text;
        }
        textBuilder = new TextBuilder();
        mDrawable = new LoadingDrawable(textBuilder);
        mDrawable.initParams(getContext());
        setImageDrawable(mDrawable);
    }


    public void setText(String text) {
        this.mText = text;
        textBuilder.setText(mText);
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
