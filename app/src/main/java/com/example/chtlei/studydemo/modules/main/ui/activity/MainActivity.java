package com.example.chtlei.studydemo.modules.main.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.chtlei.studydemo.R;
import com.example.chtlei.studydemo.base.activity.BaseActivity;
import com.example.chtlei.studydemo.core.constant.Constants;
import com.example.chtlei.studydemo.modules.hierarchy.KnowledgeFragment;
import com.example.chtlei.studydemo.modules.homepager.HomePagerFragment;
import com.example.chtlei.studydemo.modules.main.contract.MainContract;
import com.example.chtlei.studydemo.modules.main.presenter.MainPresenter;
import com.example.chtlei.studydemo.modules.navigation.NavigationFragment;
import com.example.chtlei.studydemo.modules.project.ProjectFragment;
import com.example.chtlei.studydemo.modules.wxarticle.WxArticleFragment;
import com.example.chtlei.studydemo.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by chtlei on 19-3-27.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View{
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.fragment_group)
    FrameLayout mFrameGroup;
    TextView mUsTv;

    //fragments
    private HomePagerFragment mHomePagerFragment;
    private KnowledgeFragment mKnowledgeFragment;
    private NavigationFragment mNavigationFragment;
    private WxArticleFragment mWxArticleFragment;
    private ProjectFragment mProjectFragment;
    private int mLastFgIndex = -1;
    private int mCurrentFgIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentFgIndex = savedInstanceState.getInt(Constants.CURRENT_FRAGMENT_KEY);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.CURRENT_FRAGMENT_KEY, mCurrentFgIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(R.string.home_pager);
        }
    }

    @Override
    protected void initView() {
        initDrawerLayout();
        showFragment(mCurrentFgIndex);
        initNavigationView();
        initBottomNavigationView();
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }

    private void showFragment(int index) {
        mCurrentFgIndex = index;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        mLastFgIndex = index;
        switch (index) {
            case Constants.TYPE_HOME_PAGER:
                mTitle.setText(getString(R.string.home_pager));
                if (mHomePagerFragment == null) {
                    mHomePagerFragment = HomePagerFragment.newInstance();
                    transaction.add(R.id.fragment_group, mHomePagerFragment);
                }
                transaction.show(mHomePagerFragment);
                break;
            case Constants.TYPE_KNOWLEDGE:
                mTitle.setText(getString(R.string.knowledge_hierarchy));
                if (mKnowledgeFragment == null) {
                    mKnowledgeFragment = KnowledgeFragment.newInstance();
                    transaction.add(R.id.fragment_group, mKnowledgeFragment);
                }
                transaction.show(mKnowledgeFragment);
                break;
            case Constants.TYPE_NAVIGATION:
                mTitle.setText(getString(R.string.navigation));
                if (mNavigationFragment == null) {
                    mNavigationFragment = NavigationFragment.newInstance();
                    transaction.add(R.id.fragment_group, mNavigationFragment);
                }
                transaction.show(mNavigationFragment);
                break;
            case Constants.TYPE_WX_ARTICLE:
                mTitle.setText(getString(R.string.wx_article));
                if (mWxArticleFragment == null) {
                    mWxArticleFragment = WxArticleFragment.newInstance();
                    transaction.add(R.id.fragment_group, mWxArticleFragment);
                }
                transaction.show(mWxArticleFragment);
                break;
            case Constants.TYPE_PROJECT:
                mTitle.setText(getString(R.string.project));
                if (mProjectFragment == null) {
                    mProjectFragment = ProjectFragment.newInstance();
                    transaction.add(R.id.fragment_group, mProjectFragment);
                }
                transaction.show(mProjectFragment);
                break;

            default:

                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        switch (mLastFgIndex) {
            case Constants.TYPE_HOME_PAGER:
                if (mHomePagerFragment != null) {
                    transaction.hide(mHomePagerFragment);
                }
                break;
            case Constants.TYPE_KNOWLEDGE:
                if (mKnowledgeFragment != null) {
                    transaction.hide(mKnowledgeFragment);
                }
                break;
            case Constants.TYPE_NAVIGATION:
                if (mNavigationFragment != null) {
                    transaction.hide(mNavigationFragment);
                }
                break;
            case Constants.TYPE_WX_ARTICLE:
                if (mWxArticleFragment != null) {
                    transaction.hide(mWxArticleFragment);
                }
                break;
            case Constants.TYPE_PROJECT:
                if (mProjectFragment != null) {
                    transaction.hide(mProjectFragment);
                }
                break;
            default:
                break;
        }
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    //TODO navigation item
                    case R.id.nav_item_my_collect:
                        ToastUtils.showToast(MainActivity.this,getString(R.string.collect));
                        break;
                    case R.id.nav_item_todo:
                        ToastUtils.showToast(MainActivity.this,getString(R.string.nav_todo));
                        break;
                    case R.id.nav_item_night_mode:
                        ToastUtils.showToast(MainActivity.this,getString(R.string.nav_night_mode));
                        break;
                    case R.id.nav_item_setting:
                        ToastUtils.showToast(MainActivity.this,getString(R.string.setting));
                        break;
                    case R.id.nav_item_about_us:
                        ToastUtils.showToast(MainActivity.this,getString(R.string.about_us));
                        break;
                    case R.id.nav_item_logout:
                        ToastUtils.showToast(MainActivity.this,getString(R.string.logout));
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        mUsTv = mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login);
        mUsTv.setText(getString(R.string.login));
        mUsTv.setOnClickListener(v -> ToastUtils.showToast(MainActivity.this,getString(R.string.login_in)));
        mNavigationView.getMenu().findItem(R.id.nav_item_logout).setVisible(false);
        MenuItem nightModeItem = mNavigationView.getMenu().findItem(R.id.nav_item_night_mode);
        if (true) {
            nightModeItem.setIcon(R.drawable.ic_day);
            nightModeItem.setTitle(R.string.nav_day_mode);
        } else {
            nightModeItem.setIcon(R.drawable.ic_night);
            nightModeItem.setTitle(R.string.nav_night_mode);
        }
    }

    private void initBottomNavigationView() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab_main_pager:
                    showFragment(Constants.TYPE_HOME_PAGER);
                    break;
                case R.id.tab_knowledge_hierarchy:
                    showFragment( Constants.TYPE_KNOWLEDGE);
                    break;
                case R.id.tab_navigation:
                    showFragment(Constants.TYPE_NAVIGATION);
                    break;
                case R.id.tab_wx_article:
                    showFragment(Constants.TYPE_WX_ARTICLE);
                    break;
                case R.id.tab_project:
                    showFragment(Constants.TYPE_PROJECT);
                    break;
                default:
                    break;
            }
            return true;
        });
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_usage:
                ToastUtils.showToast(MainActivity.this,getString(R.string.useful_sites));
                break;
            case R.id.action_search:
                ToastUtils.showToast(MainActivity.this,getString(R.string.search));
                break;
            default:
                break;
        }
        return true;
    }



    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }
}
