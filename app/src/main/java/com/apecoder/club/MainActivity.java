package com.apecoder.club;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.apecoder.apecoder.R;
import com.apecoder.club.app.App;
import com.apecoder.club.app.SharedPreferencesConfig;
import com.apecoder.club.base.BaseActivity;
import com.apecoder.club.base.BaseBean;
import com.apecoder.club.bean.ArticleEntity;
import com.apecoder.club.bean.ArticleJerry;
import com.apecoder.club.http.ApiManager;
import com.apecoder.club.http.BaseObserver;
import com.apecoder.club.http.HttpUtils;
import com.apecoder.club.http.ParamsManager;
import com.apecoder.club.ui.account.LoginActivity;
import com.apecoder.club.ui.home.fragment.CategoryFragment;
import com.apecoder.club.util.SharedPreferencesUtil;
import com.apecoder.club.widget.ClearEditText;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,TabLayout.BaseOnTabSelectedListener{

    private SlidingTabLayout mLayoutTab;
    private ViewPager mViewpage;
    private ClearEditText mEdit;
    //所有 -1， 0 安卓，1 ios, 2 前端，3 java, 4 PHP, 5 python, 6 大数据, 7 人工智能
    protected final String[] sTitle = new String[]{"全部","Android", "iOS", "前端", "Java", "PHP", "python", "大数据", "人工智能"};
    protected final int[] mCategorys = new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7};
    private List<Fragment> mTabContents;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mLayoutTab = (SlidingTabLayout) findViewById(R.id.tab_layout);
        mViewpage = (ViewPager) findViewById(R.id.viewpage);
        mEdit = (ClearEditText) findViewById(R.id.edit);
    }

    @Override
    public void initData() {
        mTabContents = new ArrayList<>();

        for (int i=0;i<sTitle.length;i++){
            Bundle bundle = new Bundle();
            bundle.putInt("category", mCategorys[i]);
            CategoryFragment categoryFragment = new CategoryFragment();
            categoryFragment.setArguments(bundle);
            mTabContents.add(categoryFragment);
//            mLayoutTab.addTab(mLayoutTab.newTab().setText(sTitle[i]));
        }

        mViewpage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mLayoutTab.setViewPager(mViewpage);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            getJsonArray();
            page++;
            return false;
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            new MaterialDialog.Builder(MainActivity.this)
                    .title("安全退出")
                    .content("确定退出应用吗？")
                    .positiveText("确定").positiveColor(ContextCompat.getColor(MainActivity.this, R.color.dialog_button))
                    .negativeText("取消")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            //执行清楚缓存，账号密码
                            SharedPreferencesUtil.remove(MainActivity.this, SharedPreferencesConfig.PERSONAL_DATA);
                            App.setAccount(null);
                            startActivity(LoginActivity.class);
                            finish();
                        }
                    })
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mTabContents.get(position);
        }

        @Override
        public int getCount() {
            return mTabContents.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return sTitle[position];
        }
    }

    int page =0;
    private void getJsonArray(){
        RetrofitUrlManager.getInstance().putDomain("douban", "https://502tech.com/geekdaily/");
        Map<String,Object> map = ParamsManager.getLeiziParams(page,100);
        HttpUtils.APIFunction(ApiManager.mProfileApi.getLeiZiArticleList(map), new BaseObserver<List<ArticleJerry>>() {
            @Override
            protected void onSuccees(BaseBean<List<ArticleJerry>> t) throws Exception {
                if(t.getCode()==0){
                    jsonData(t);
                }
            }
        });
    }

    private void jsonData(BaseBean<List<ArticleJerry>> t){
        List<ArticleJerry> articleJerries =  t.getData();
        List<ArticleEntity> articleEntities =  new ArrayList<>();
        int size = articleJerries.size();
        for (int i=0;i<size;i++){
            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setTitle(articleJerries.get(i).getTitle());
            articleEntity.setDes(articleJerries.get(i).getDes());
            articleEntity.setLink(articleJerries.get(i).getLink());
            articleEntity.setCoverImage(articleJerries.get(i).getImg_url());
            articleEntity.setContributorId(12L);
            articleEntity.setAuditSatus(1);
            if(articleJerries.get(i).getCategory().equals("Android")){
                articleEntity.setCategory(0);
            }
            articleEntities.add(articleEntity);
        }
        postJsonArray(new Gson().toJson(articleEntities));
    }

    private void postJsonArray(String josnArray){
        Map<String,Object> map = ParamsManager.getJsonArrayParams(josnArray);
        HttpUtils.APIFunction(ApiManager.mProfileApi.batchSaveArticle(map), new BaseObserver<Object>() {
            @Override
            protected void onSuccees(BaseBean<Object> t) throws Exception {
                if(t.getCode()==1){
                    showLongToast(t.getMsg());
                }
            }
        });
    }
}
