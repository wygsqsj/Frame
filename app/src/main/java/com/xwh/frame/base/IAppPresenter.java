package com.xwh.frame.base;

/**
 * Presenter的接口，用于同步生命周期
 * Created by XH on 2017/11/9.
 */

public interface IAppPresenter {

    void onResume();

    void onDestroy();
}