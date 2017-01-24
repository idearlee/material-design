package com.study.idear.mystudy;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawer;
    private Toolbar mToolBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private TabLayout mTab;
    private ViewPager mViewPager;
    private IViewPagerAdapter mViewPageAdapter;
    private FloatingActionButton mFloatButton;

    private CollapsingToolbarLayout mCToolBar;

    private String[] mTabTitles = {"影视", "图片", "音乐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initToolBar();
        initDrawer();
        initTab();
        initContent();
        initFloatActionButton();
    }


    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.main_toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.main_drawer);
        mFloatButton = (FloatingActionButton) findViewById(R.id.btnFloatingAction);
        mTab = (TabLayout) findViewById(R.id.main_tab);
        mViewPager = (ViewPager) findViewById(R.id.main_content);
        mCToolBar = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
    }


    private void initToolBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCToolBar.setExpandedTitleColor(Color.GREEN);
        mCToolBar.setCollapsedTitleTextColor(Color.RED);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer,
                mToolBar, R.string.app_name,
                R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawer.setDrawerListener(mDrawerToggle);
    }

    private void initFloatActionButton() {
        mFloatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(mFloatButton, "Hello SnackBar!", Snackbar.LENGTH_SHORT)
                        .setAction("PrintLog", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Logger.d(TAG, "hello d");
                                Logger.e(TAG, "hello e");
                                Logger.w(TAG, "hello w");
                                Logger.v(TAG, "hello v");
                                Logger.wtf(TAG, "hello wtf");
                            }
                        })
                        .show();
            }
        });
    }

    private void initTab() {
        mTab.addTab(mTab.newTab().setText(mTabTitles[0]), 0, false);
        mTab.addTab(mTab.newTab().setText(mTabTitles[1]), 1, true);
        mTab.addTab(mTab.newTab().setText(mTabTitles[2]), 2, false);
        mTab.setupWithViewPager(mViewPager);
    }

    private void initContent() {

        mViewPageAdapter = new IViewPagerAdapter(getSupportFragmentManager());
        mViewPageAdapter.addFragment(VideoFragment.getmInstance(), mTabTitles[0]);
        mViewPageAdapter.addFragment(PhotoFragment.getmInstance(), mTabTitles[1]);
        mViewPageAdapter.addFragment(AudioFragment.getmInstance(), mTabTitles[2]);
        mViewPager.setAdapter(mViewPageAdapter);
    }
}
