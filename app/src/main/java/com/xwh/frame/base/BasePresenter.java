package com.xwh.frame.base;

import rx.subjects.PublishSubject;

/**
 * 中间层的基类封装
 * 1.与View层的交互，提供 关联/取消的方法
 * 2.构建抽象方法获取对应的Model层
 * 3.在View层关联生命周期管理，为rxjava数据链断开提供生命周期观察
 * <p>
 * Created by xwh on 2017/10/18.
 */
public abstract class BasePresenter<V extends IBaseView>
        implements IAppPresenter {

    public V view;

    public void attach(V view) {
        this.view = view;
        initModel();
    }

    /**
     * 取消和view层的关联
     */
    public void detach() {
        this.view = null;
    }


    /**
     * 在这里new出对应的model
     */
    protected abstract void initModel();

    /**
     * 界面生命周期同步
     */
    public enum LifeCycleEvent {
        RESUME,
        DESTROY
    }

    /**
     * 使用PublishSubject发送生命周期事件，用于rxjava在界面Destory的时候取消请求链
     */
    protected final PublishSubject<LifeCycleEvent> lifecycleSubject = PublishSubject.create();

    @Override
    public void onResume() {
        lifecycleSubject.onNext(LifeCycleEvent.RESUME);
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(LifeCycleEvent.DESTROY);
    }


}
