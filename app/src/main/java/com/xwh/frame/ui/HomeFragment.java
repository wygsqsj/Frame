package com.xwh.frame.ui;

import android.widget.Button;

import com.xwh.frame.R;
import com.xwh.frame.base.BaseFragment;
import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.mvp.presenter.HomePresenter;
import com.xwh.frame.mvp.view.IHomeView;
import com.xwh.frame.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 首页Fragment
 * <p>
 * Created by XH on 2017/11/27.
 */
public class HomeFragment extends BaseFragment<IHomeView, HomePresenter>
        implements IHomeView {

    private static HomeFragment instance;
    @BindView(R.id.request)
    Button mRequest;

    public static HomeFragment getInstance() {
        if (null == instance) {
            synchronized (HomeFragment.class) {
                if (instance == null) {
                    instance = new HomeFragment();
                }
            }
        }
        return instance;
    }

    private HomeFragment() {
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @OnClick(R.id.request)
    public void onViewClicked() {
        mPresenter.load();
    }

    @Override
    public void loadsucceed(Joke joke) {
        ToastUtil.showShort(this.getContext(), joke.getData().get(0).getContent());
    }
}
