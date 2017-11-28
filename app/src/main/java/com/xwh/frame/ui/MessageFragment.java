package com.xwh.frame.ui;


import com.xwh.frame.R;
import com.xwh.frame.base.BaseFragment;
import com.xwh.frame.base.BasePresenter;

/**
 * Created by XH on 2017/11/27.
 */

public class MessageFragment extends BaseFragment {

    private static MessageFragment instance;

    public static MessageFragment getInstance() {
        if (null == instance) {
            synchronized (HomeFragment.class) {
                if (instance == null) {
                    instance = new MessageFragment();
                }
            }
        }
        return instance;
    }

    private MessageFragment() {
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_message;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
