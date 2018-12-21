package com.apecoder.club.http;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.apecoder.club.base.BaseBean;
import com.apecoder.club.util.ToastUitl;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author yemao
 * @date 2017/4/9
 * @description 写自己的代码, 让别人说去吧!
 */

public abstract class BaseObserver<T> implements Observer<BaseBean<T>> {
    protected Context mContext;

    public BaseObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BaseObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(BaseBean<T> tBaseEntity) {
        onRequestEnd();
        try {
            onSuccees(tBaseEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        if (tBaseEntity.getStatus()==1) {
//            try {
//                onSuccees(tBaseEntity);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            try {
//                onCodeError(tBaseEntity);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void onError(Throwable e) {
//        Log.w(TAG, "onError: ", );这里可以打印错误信息
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(e, true);
            } else {
                onFailure(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /** 
     * description: 无论成功失败都会调用，表示整个流程完结
     * author: liujie
     * date: 2018/1/17 17:14 
     */
    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BaseBean<T> t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected void onCodeError(BaseBean<T> t) throws Exception {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected  void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
        onComplete();
        ToastUitl.showShort(e.getMessage());
    };

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
//        ProgressDialog.show(mContext, false, "请稍后");
    }

    public void closeProgressDialog() {
//        ProgressDialog.cancle();
    }


}
