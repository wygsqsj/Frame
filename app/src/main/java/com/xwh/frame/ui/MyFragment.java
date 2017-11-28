package com.xwh.frame.ui;

import com.xwh.frame.R;
import com.xwh.frame.base.BaseFragment;
import com.xwh.frame.base.BasePresenter;

/**
 * Created by XH on 2017/11/27.
 */

public class MyFragment extends BaseFragment {

    private static MyFragment instance;

    public static MyFragment getInstance() {
        if (null == instance) {
            synchronized (HomeFragment.class) {
                if (instance == null) {
                    instance = new MyFragment();
                }
            }
        }
        return instance;
    }

    private MyFragment() {
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_my;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
