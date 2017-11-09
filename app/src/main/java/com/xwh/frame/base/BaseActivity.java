package com.xwh.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xwh.frame.utils.LogUtil;

import butterknife.ButterKnife;

/**
 * Created by xwh on 2017/10/18.
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends AppCompatActivity implements IBaseView {

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("onCreate");
        setContentView(initLayoutResID());
        ButterKnife.bind(this);
        initBasePresenter();
        initViews();
        initSet();
        initListener();
    }

    private void initBasePresenter() {
        mPresenter = initPresenter();
        if (mPresenter != null)
            mPresenter.attach((V) this);
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
    }
}
