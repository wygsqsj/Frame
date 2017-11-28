package com.xwh.frame.mvp.view;

import com.xwh.frame.base.IBaseView;
import com.xwh.frame.mvp.model.bean.UpDateApk;

/**
 * Created by XH on 2017/10/19.
 */

public interface IMainView extends IBaseView {
    void checkUpdateSucceed(UpDateApk upDateApk);
}
