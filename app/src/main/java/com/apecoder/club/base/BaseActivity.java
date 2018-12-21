package com.apecoder.club.base;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import com.apecoder.apecoder.R;
import com.apecoder.club.app.AppManager;
import com.apecoder.club.util.DialogUtil;
import com.apecoder.club.util.GlobalStatusBarUtil;
import com.apecoder.club.util.ToastUitl;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    private boolean isConfigChange = false;
    //    private Unbinder mUnBinder;
    protected Dialog mDialog;

    protected boolean isUseKnife = false;
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConfigChange = false;
        AppManager.getAppManager().addActivity(this);
        isButterKnife();
        doBeforeSetcontentView();
        //移除默认背景    getResources().getDrawable(R.color.transparent)不为空无效
//        getWindow().setBackgroundDrawable(getResources().getDrawable(R.color.transparent));
        setContentView(getLayoutId());
        GlobalStatusBarUtil.setFitsSystemWindows(this, true);
        if (isUseKnife) {
            ButterKnife.bind(this);
            initToolBar();
        }
        mContext = this;
        this.initView();
        this.initData();
    }

    protected void isButterKnife() {

    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        //设置昼夜主题
//        initTheme();
        // 把actvity放到application栈中管理
//        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        GlobalStatusBarUtil.setStatusColor(this, getStatusBarColorId());
        GlobalStatusBarUtil.setStatusBarDarkFont(this, false);

    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //初始化view
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    //  activity 结束时调用
    public void onDestoryTag() {

    }

    private void initToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setNavigationOnClickListener(v -> finish());
        }
    }


    /**
     * 设置主题
     */
    private void initTheme() {
//        ChangeModeController.setTheme(this, R.style.DayTheme, R.style.NightTheme);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
//        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
//        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
//        StatusBarCompat.translucentStatusBar(this);
//        StatusBarUtil.setTransparent(this);
        //透明状态栏，不隐藏状态栏图标
//        StatusBarCompat.translucentStatusBar(this);
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
//        LoadingDialog.showDialogForLoading(this);
        if (null == mDialog) {
            mDialog = DialogUtil.getCenterProgressDialog(this);
        }
        mDialog.show();
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
//        LoadingDialog.showDialogForLoading(this, msg, true);
        if (null == mDialog) {
            mDialog = DialogUtil.getCenterProgressDialog(this, msg, true);
        }
        mDialog.show();
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        if (null != mDialog) {
            mDialog.cancel();
        }
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(), R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error, R.drawable.ic_wifi_off);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //debug版本不统计crash
//        if(!BuildConfig.LOG_DEBUG) {
//            //友盟统计
//            MobclickAgent.onResume(this);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //debug版本不统计crash
//        if(!BuildConfig.LOG_DEBUG) {
//            //友盟统计
//            MobclickAgent.onPause(this);
//        }
    }

    //转屏幕
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    @Override
    protected void onDestroy() {
        if (!isConfigChange) {
            AppManager.getAppManager().finishActivity(this);
        }
//        if (null != mUnBinder) {
//            mUnBinder.unbind();
//        }
        //默认activity销毁关闭当前页面的所有请求
        dispose();
        onDestoryTag();
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
        }
        super.onDestroy();
    }

    private CompositeDisposable compositeDisposable;

    public void addDisposableTag(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    public void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }

    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 可以重写状态栏的颜色
     *
     * @return
     */
    protected int getStatusBarColorId() {
        return ContextCompat.getColor(this, R.color.colorPrimary);
    }
}
