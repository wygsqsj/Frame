package com.xwh.frame.utils.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.xwh.frame.R;
import com.xwh.frame.utils.dialog.view.LoadingImageView;
import com.xwh.frame.utils.dialog.view.LoadingTextView;

import java.lang.ref.WeakReference;

/**
 * 星状加载框
 * Created by xwh on 2017/11/23.
 */

public class StarDialog {
    private final WeakReference<Context> mContext;
    private final int mThemeResId;
    private int mLoadingBuilderColor;
    private String mHintText;
    private Dialog mDialog;
    private int mHintTextColor = -1;
    private boolean mCancelable = true;
    private boolean mCanceledOnTouchOutside = true;

    public StarDialog(Context context) {
        this(context, R.style.alert_dialog);
    }

    public StarDialog(Context context, int mThemeResId) {
        this.mContext = new WeakReference<>(context);
        this.mThemeResId = mThemeResId;
    }

    public StarDialog setLoadingColor(int color) {
        this.mLoadingBuilderColor = color;
        return this;
    }

    public StarDialog setHintText(String text) {
        this.mHintText = text;
        return this;
    }

    public StarDialog setHintTextColor(int color) {
        this.mHintTextColor = color;
        return this;
    }

    public StarDialog setCancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public StarDialog setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        mCanceledOnTouchOutside = canceledOnTouchOutside;
        return this;
    }

    public void show() {
        if (mDialog != null) {
            mDialog.show();
        } else {
            mDialog = create();
            mDialog.show();
        }
    }

    public boolean isShowing() {
        return mDialog != null && mDialog.isShowing();
    }

    private Dialog create() {
        if (isContextNotExist()) {
            throw new RuntimeException("Context is null...");
        }
        if (mDialog != null) {
            cancel();
        }
        mDialog = new Dialog(this.mContext.get(), this.mThemeResId);
        View contentView = createContentView();
        //textview的配置
        LoadingTextView text = (LoadingTextView) contentView.findViewById(R.id.loading_text);
        if (!TextUtils.isEmpty(mHintText)) {
            text.setText(mHintText);
            text.setColorFilter(this.mHintTextColor == -1 ? this.mLoadingBuilderColor : this.mHintTextColor);
        }
        //imageView的配置
        LoadingImageView image = (LoadingImageView) contentView.findViewById(R.id.loading_image);
        image.setColorFilter(this.mLoadingBuilderColor);
        //填充dialog
        mDialog.setContentView(contentView);
        mDialog.setCancelable(this.mCancelable);
        mDialog.setCanceledOnTouchOutside(this.mCanceledOnTouchOutside);
        return mDialog;
    }

    private
    @NonNull
    View createContentView() {
        if (isContextNotExist()) {
            throw new RuntimeException("Context is null...");
        }
        return View.inflate(this.mContext.get(), R.layout.loading_dialog, null);
    }

    public void cancel() {
        if (mDialog != null) {
            mDialog.cancel();
        }
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener listener) {
        if (mDialog == null) {
            mDialog = create();
        }
        mDialog.setOnCancelListener(listener);
    }

    private boolean isContextNotExist() {
        Context context = this.mContext.get();
        return context == null;
    }
}
