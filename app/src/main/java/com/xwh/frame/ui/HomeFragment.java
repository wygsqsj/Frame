package com.xwh.frame.ui;

import android.annotation.SuppressLint;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xwh.frame.R;
import com.xwh.frame.adapter.JokeAdapter;
import com.xwh.frame.base.BaseFragment;
import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.mvp.presenter.HomePresenter;
import com.xwh.frame.mvp.view.IHomeView;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.widget.RecyclerItemDivider;

import butterknife.BindView;
import butterknife.Unbinder;


/**
 * 首页Fragment
 * <p>
 * Created by XH on 2017/11/27.
 */
public class HomeFragment extends BaseFragment<IHomeView, HomePresenter>
        implements IHomeView {

    @BindView(R.id.home_recycler)
    RecyclerView recyclerView;

    private JokeAdapter mJokeAdapter;
    private static HomeFragment instance;

    Unbinder unbinder;

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

    @SuppressLint("ValidFragment")
    private HomeFragment() {
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.load();
        mJokeAdapter = new JokeAdapter();
    }

    @Override
    protected void initSet() {
        super.initSet();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new RecyclerItemDivider(getActivity()));
        recyclerView.setAdapter(mJokeAdapter);
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    public void loadsucceed(Joke joke) {
        LogUtil.i("haha"+joke.getData().toString());
        mJokeAdapter.refresh(joke.getData());
    }

}
