package com.apecoder.club.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.apecoder.apecoder.R;
import com.apecoder.club.widget.DialogCall;

import java.util.List;


public class DialogUtil {
    /**
     * 底部弹出式
     *
     * @param context
     * @param view
     * @return
     */

    public static Dialog getMenuDialog(Activity context, View view) {

        final Dialog dialog = new Dialog(context, R.style.MenuDialogStyle);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        int screenW = DisplayUtil.getScreenWidth(context);
        lp.width = screenW;
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.MenuDialogAnimation); // 添加动画
        return dialog;
    }

    /**
     * 自定义居中进度条
     *
     * @param context
     * @param context
     * @param text       文本内容
     * @param cancelable 是否可以返回取消
     * @return
     */
    public static Dialog getCenterProgressDialog(Activity context, CharSequence text, boolean cancelable) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(cancelable);//true，可以返回取消
        dialog.setContentView(R.layout.custom_progress_dialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        TextView titleTxtv = (TextView) dialog.findViewById(R.id.dialogText);
        titleTxtv.setText(text);
        lp.width = DisplayUtil.dip2px(110);
        lp.height = DisplayUtil.dip2px(110);
        return dialog;
    }

    /**
     * 默认自定义居中进度条
     *
     * @param context
     * @param context
     * @return
     */
    public static Dialog getCenterProgressDialog(Activity context) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_progress_dialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = DisplayUtil.dip2px(110);
        lp.height = DisplayUtil.dip2px(110);
        return dialog;
    }

    /**
     * 自定义居中进度条
     *
     * @param context
     * @param context
     * @param resid     文本内容id
     * @param isShowTxt 是否显示文本
     * @return
     */
    public static Dialog getCenterProgressDialog(Activity context, int resid, boolean isShowTxt) {

        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_progress_dialog);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        TextView titleTxtv = (TextView) dialog.findViewById(R.id.dialogText);
        titleTxtv.setText(resid);
        if (isShowTxt) {
            titleTxtv.setVisibility(View.VISIBLE);
        } else {
            titleTxtv.setVisibility(View.GONE);
        }
        lp.width = DisplayUtil.dip2px(110);
        lp.height = DisplayUtil.dip2px(110);
        return dialog;
    }

    /**
     * 自定义 other样式。透明的背景
     *
     * @param context
     * @param view
     * @return
     */
    public static Dialog getCenterDialog(Activity context, View view) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int screenW = DisplayUtil.getScreenWidth(context);
        lp.width = screenW;
        return dialog;
    }

    //白色的背景
    public static Dialog getWhiteCenterDialog(Activity context, View view) {
        final Dialog dialog = new Dialog(context, R.style.WhiteDialogStyle);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int screenW = DisplayUtil.getScreenWidth(context);
        lp.width = screenW;
        return dialog;
    }

    /**
     * 自定义 other样式
     *
     * @param context
     * @param view
     * @return
     */
    public static Dialog getCenterDialog(Activity context, View view, boolean cancel) {
        final Dialog dialog = new Dialog(context, R.style.DialogStyle);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(cancel);
        dialog.setCancelable(cancel);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        int screenW = DisplayUtil.getScreenWidth(context)-DisplayUtil.dip2px(60);
        lp.width = screenW;
        return dialog;
    }


    public static void showTipStr(Context context, String title, String content) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText("确定").positiveColor(ContextCompat.getColor(context, R.color.dialog_button))
                .show();
    }

    public static void showDialog(Context context, String title, String content, final DialogCall callBack) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText("确定").negativeText("取消").negativeColor(ContextCompat.getColor(context, R.color.color_999)).positiveColor(ContextCompat.getColor(context, R.color.dialog_button)).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                callBack.onPositive();
            }
        }).onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                callBack.onNegative();
            }
        }).show();
    }

    public static void showTipInt(Context context, int title, int content) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText("确定").positiveColor(ContextCompat.getColor(context, R.color.dialog_button))
                .show();
    }

    public static void showTipInt(Context context, String title, String content) {
        new MaterialDialog.Builder(context)
                .title(title)
                .content(content)
                .positiveText("确定").positiveColor(ContextCompat.getColor(context, R.color.dialog_button))
                .show();
    }

    /**
     * 选择列表对话框
     */
    public static void showListDialog(final Context context, @ArrayRes int itemsRes, MaterialDialog.ListCallback listener) {
        new MaterialDialog.Builder(context).title(R.string.operation).items(itemsRes).itemsCallback(listener).show();
    }

    public static Dialog getListDialog(final Context context, String title, List<String> itemsRes, MaterialDialog.ListCallback listener) {
       return new MaterialDialog.Builder(context).title(title).items(itemsRes).itemsCallback(listener).build();
    }
}
