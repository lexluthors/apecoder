package com.apecoder.club.http;


import com.apecoder.club.base.BaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Description:
 * Data：2018/11/5-14:37
 * Author: Allen
 */
public class HttpUtils {

    public static <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 监听回调只需要重写一个onSuccees方法，失败方法在BaseObserver中已经统一处理了，会有吐司提示，如果不想提示就重写失败方法即可。
     * onComplete方法，无论成功和失败都会被调用。这里可以关闭dialog使用。
     * @param observable 被观察者，这里是实例化的接口，如：ApiManager.mProfileApi.login(new HashMap<>())
     * @param baseObserver 观察者，这里是监听回调
     * @param <T> basebean中的泛型bean，指data
     */
    public static <T> void APIFunction(Observable<BaseBean<T>> observable, BaseObserver<T> baseObserver) {
        observable.compose(setThread()).subscribe(baseObserver);
    }

}
