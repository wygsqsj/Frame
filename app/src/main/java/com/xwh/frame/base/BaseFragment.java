package com.xwh.frame.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.dialog.LoadDialog;
import com.xwh.frame.utils.net.config.ExceptionHandler;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xwh on 2017/11/27.
 */

public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>>
        extends Fragment implements IBaseView {

    private LoadDialog mLoadDialog;
    protected P mPresenter;
    protected View rootView;
    private Unbinder unbinder;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.i("fragment -- onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogUtil.i("fragment --- onCreateView");
        rootView = inflater.inflate(initLayoutResID(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        ButterKnife.bind(rootView);
        initBasePresenter();
        initViews();
        initListener();
        initSet();
        initData();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.i("fragment -- onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        LogUtil.i("fragment -- onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogUtil.i("fragment -- onPause");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        LogUtil.i("fragment -- onDestroy");
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null)
            mPresenter.detach();
        if (mLoadDialog != null)
            mLoadDialog.destroy();
    }

    protected abstract int initLayoutResID();

    private void initBasePresenter() {
        mPresenter = initPresenter();
        if (mPresenter != null)
            mPresenter.attach((V) this);
    }

    protected abstract P initPresenter();

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
        mLoadDialog.showProgressDialog(this.getActivity(), "loding....");
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
