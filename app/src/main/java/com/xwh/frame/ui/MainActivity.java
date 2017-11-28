package com.xwh.frame.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.xwh.frame.R;
import com.xwh.frame.base.BaseActivity;
import com.xwh.frame.mvp.model.bean.UpDateApk;
import com.xwh.frame.mvp.presenter.MainPresenter;
import com.xwh.frame.mvp.view.IMainView;
import com.xwh.frame.service.DownService;
import com.xwh.frame.utils.AppUtil;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.ToastUtil;
import com.xwh.frame.utils.dialog.DialogUtil;

import butterknife.BindView;

/**
 * MainActivity的实现
 * 1.使用BottomNavigationView实现底部导航栏（不能超过5个item）
 */
public class MainActivity
        extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.activity_main_fragment)
    FrameLayout mContent;
    @BindView(R.id.activity_main_navigation)
    BottomNavigationView mNavigation;
    private HomeFragment homeFragment;
    private MessageFragment msgFragment;
    private MyFragment myFragment;
    private Fragment fragment;
    /**
     * 返回键按两次退出,此为记录第一次按下时间
     */
    private long exitTime = 0;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int initLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        homeFragment = HomeFragment.getInstance();
        msgFragment = MessageFragment.getInstance();
        myFragment = MyFragment.getInstance();
        fragment = homeFragment;
        mPresenter.checkUpdate();
    }

    @Override
    protected void initSet() {
        super.initSet();
        showFirstFragment();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                showFragment(homeFragment);
                                break;
                            case R.id.navigation_msg:
                                showFragment(msgFragment);
                                break;
                            case R.id.navigation_my:
                                showFragment(myFragment);
                                break;
                        }

                        return true;
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽 返回键
            if ((System.currentTimeMillis() - exitTime > 2 * 1000)) {
                ToastUtil.showShort(this.getBaseContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 显示首页Fragment
     **/
    private void showFirstFragment() {
        mNavigation.getMenu().getItem(1).setChecked(true);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main_fragment, msgFragment)
                .commitAllowingStateLoss();
        this.fragment = msgFragment;
    }

    /**
     * 显示指定Fragment
     *
     * @param fragment 要显示的Fragment
     */
    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {// 先判断是否被add过
            // 隐藏当前的fragment，add下一个到Activity中
            transaction.hide(this.fragment)
                    .add(R.id.activity_main_fragment, fragment)
                    .commitAllowingStateLoss();
        } else {
            // 隐藏当前的fragment，显示下一个
            transaction.hide(this.fragment)
                    .show(fragment)
                    .commitAllowingStateLoss();
        }
        this.fragment = fragment;
    }

    @Override
    public void checkUpdateSucceed(final UpDateApk upDateApk) {
        if (Integer.valueOf(upDateApk.versionCode) <= AppUtil.getVersionCode(getApplicationContext())) {
            return;
        }
        DialogUtil.showMulti(MainActivity.this,
                String.format(getApplicationContext().getResources().getString(R.string.text_update_title),
                        upDateApk.versionName),
                upDateApk.brief,
                getApplicationContext().getResources().getString(R.string.text_update),
                getApplicationContext().getResources().getString(R.string.text_cancel),
                new DialogUtil.OnDialogClickListener() {
                    @Override
                    public void resultString(String str) {
                       LogUtil.i("点击事件" + upDateApk.url);
                        //开启下载服务
                        Intent intent = new Intent(MainActivity.this, DownService.class);
                        startService(intent);

                    }
                });
    }
}
