package com.apecoder.club.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.apecoder.apecoder.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Util;

import java.io.File;

/**
 * Created by Tony on 2018/1/16.
 */

public class GlideUtil {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }
    public static void displayBitmap(Context context, ImageView imageView, Bitmap bitmap) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(bitmap)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displayAlbum(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displayFit(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
//                .placeholder(R.drawable.ic_image_loading)
                .dontAnimate()
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displayFitCenter(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
//                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideRoundTransform(context)).into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, int resId) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideRoundTransform(context)).into(imageView);
    }

    public static void displayCircle(Context context, ImageView imageView, String url) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideCircleTransform(context)).into(imageView);
    }

    public static void displayCircle(Context context, ImageView imageView, String url, loadDownListener listener) {
        if (imageView == null || null == context) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model,
                                       Target<GlideDrawable> target,
                                       boolean isFirstResource) {
                listener.loadFail();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model,
                                           Target<GlideDrawable> target,
                                           boolean isFromMemoryCache,
                                           boolean isFirstResource) {
                listener.loadSuc();
                return false;
            }
        })
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.toux2)
                .centerCrop().transform(new GlideCircleTransform(context)).into(imageView);

    }

    /**
     * 暂停所有的加载图片任务
     */
    public static void pauseRequestsRecursive(Activity activity) {
        Glide.with(activity).pauseRequestsRecursive();
    }


    /**
     * 恢复所有的加载图片任务
     */
    public static void resumeRequestsRecursive(Activity activity) {
        Glide.with(activity).resumeRequestsRecursive();
    }

    /**
     * 在触发内存警告使用调用
     */
    public static void onLowMemory(Context context) {
        Glide.with(context).onLowMemory();
    }

    /**
     * 根据不同的内存警告级别进行不同的处理
     */
    public static void onTrimMemory(Context context, int level) {
        Glide.with(context).onTrimMemory(level);
    }

    /**
     * 相应的销毁方法
     */
    public static void onDestory(Context context) {
        if (Util.isOnMainThread()) {
            Glide.with(context).onDestroy();
        }
    }

    public interface loadDownListener {
        void loadSuc();

        void loadFail();
    }
}
