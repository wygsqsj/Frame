package com.xwh.frame.mvp.view;

import com.xwh.frame.base.IBaseView;
import com.xwh.frame.mvp.model.bean.Joke;

/**
 * Created by xwh on 2017/10/19.
 */
public interface ISplashView extends IBaseView {
    void loadsucceed(Joke joke);
}
