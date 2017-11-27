package com.xwh.frame.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xwh.frame.R;
import com.xwh.frame.base.BaseActivity;
import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.mvp.presenter.MainPresenter;
import com.xwh.frame.mvp.view.IMainView;

import butterknife.BindView;

public class MainActivity
        extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

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

    @Override
    protected void initSet() {
        super.initSet();
        mNavigation.setSelectedItemId(R.id.navigation);
    }

    @Override
    protected void initListener() {
        super.initListener();
        mNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.content, TabFragment.from(item.getItemId()).fragment())
                                .commit();
                        return true;
                    }
                });
    }

    @Override
    public void loadsucceed(Joke joke) {

    }

    private enum TabFragment {
        home(R.id.navigation_home, HomeFragment.class),
        msg(R.id.navigation_msg, MessageFragment.class),
        my(R.id.navigation_msg, MyFragment.class);

        private Fragment fragment;
        private final int menuId;
        private final Class<? extends Fragment> clazz;

        TabFragment(@IdRes int menuId, Class<? extends Fragment> clazz) {
            this.menuId = menuId;
            this.clazz = clazz;
        }

        @NonNull
        public Fragment fragment() {
            if (fragment == null) {
                try {
                    fragment = clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    fragment = new Fragment();
                }
            }
            return fragment;
        }

        public static TabFragment from(int itemId) {
            for (TabFragment fragment : values()) {
                if (fragment.menuId == itemId) {
                    return fragment;
                }
            }
            return home;
        }

        public static void onDestroy() {
            for (TabFragment fragment : values()) {
                fragment.fragment = null;
            }
        }
    }

}
