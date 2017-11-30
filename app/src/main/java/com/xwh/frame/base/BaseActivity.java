package com.xwh.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xwh.frame.R;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.StatusBarCompat;
import com.xwh.frame.utils.widget.dialog.LoadDialog;
import com.xwh.frame.utils.net.config.ExceptionHandler;

import butterknife.ButterKnife;

/**
 * Activity基类的抽取
 * 1.布局ID的获取设置为抽象方法，交由具体的Activity实现
 * 2.将加载框的具体实现放到基类中，子类可以在加载数据时调用
 * 3.页面设置方法构建出来，子类需要时重写即可
 * <p>
 * Created by xwh on 2017/10/18.
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends AppCompatActivity implements IBaseView {

    protected P mPresenter;
    private LoadDialog mLoadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("onCreate");
        setContentView(initLayoutResID());
        //状态栏的高度适配
        StatusBarCompat.compat(this, R.color.colorPrimary);
        ButterKnife.bind(this);
        initBasePresenter();
        initViews();
        initListener();
        initData();
        initSet();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("onStart");
    }

    @Override
    protected void onPause() {
        LogUtil.i("onPause");
        super.onPause();
    }

    @Override
    protected void onRestart() {
        LogUtil.i("onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        LogUtil.i("onResume");
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStop() {
        LogUtil.i("onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogUtil.i("onDestroy");
        super.onDestroy();
        mPresenter.detach();
        if (mLoadDialog != null)
            mLoadDialog.destroy();
    }


    private void initBasePresenter() {
        mPresenter = initPresenter();
        if (mPresenter != null)
            mPresenter.attach((V)this);
    }

    protected abstract P initPresenter();

    protected abstract int initLayoutResID();

    protected void initViews() {
    }

    protected void initSet() {
    }


    protected void initData() {
    }


    protected void initListener() {
    }


    @Override
    public void showProgressDialog() {
        if (mLoadDialog == null) {
            mLoadDialog = new LoadDialog();
        }
        mLoadDialog.showProgressDialog(this, "loding....");
    }

    @Override
    public void hideProgressDialog() {
        if (mLoadDialog != null) {
            mLoadDialog.hideProgressDialog();
        }
    }

    @Override
    public void immedHideProDialog() {
        if (mLoadDialog != null) {
            mLoadDialog.immedHideProDialog();
        }
    }

    @Override
    public void fail(ExceptionHandler.ResponseThrowable exception) {

    }
}
