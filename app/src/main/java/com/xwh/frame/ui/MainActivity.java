package com.xwh.frame.ui;

import android.os.Bundle;
import android.widget.Button;

import com.xwh.frame.R;
import com.xwh.frame.base.BaseActivity;
import com.xwh.frame.mvp.presenter.MainPresenter;
import com.xwh.frame.mvp.view.IMainView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity
        extends BaseActivity<IMainView, MainPresenter>
        implements IMainView {


    @BindView(R.id.main_quest)
    Button mainQuest;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @OnClick(R.id.main_quest)
    public void onViewClicked() {

    }
}
