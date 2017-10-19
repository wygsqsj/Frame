package com.xwh.frame.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.xwh.frame.utils.LogUtil;

/**
 * Created by xwh on 2017/10/18.
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends AppCompatActivity implements IBaseView {

    protected P presenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        LogUtil.i("onCreate");
        setContentView(initLayoutResID());
        initBasePresenter();
        initViews();
        initSet();
        initListener();
    }


    private void initBasePresenter() {
        presenter = initPresenter();
        presenter.attach((V) this);
    }


    protected abstract P initPresenter();

    protected abstract int initLayoutResID();

    protected void initViews() {
    }

    ;

    protected void initSet() {
    }

    ;

    protected void initData() {
    }

    ;

    protected void initListener() {
    }

    ;


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
    }
}