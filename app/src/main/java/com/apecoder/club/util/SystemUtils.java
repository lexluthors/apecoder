package com.apecoder.club.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.List;
import java.util.UUID;

/**
 * Created by mwqi on 2014/6/19.
 */
public class SystemUtils
{

	/** 获取android系统版本号 */
	public static String getOSVersion()
	{
		String release = Build.VERSION.RELEASE; // android系统版本号
		release = "android" + release;
		return release;
	}

	/** 获得android系统sdk版本号 */
	@SuppressWarnings("deprecation")
	public static String getOSVersionSDK()
	{
		return Build.VERSION.SDK;
	}

	/** 获得android系统sdk版本号 */
	public static int getOSVersionSDKINT()
	{
		return Build.VERSION.SDK_INT;
	}

	/** 获取手机型号 */
	public static String getDeviceModel()
	{
		return Build.MODEL;
	}

	/**
	 * 获得设备名称
	 */
	public static String getDeviceName()
	{
		return Build.MANUFACTURER;
	}

	/** 获取设备的IMEI */
	public static String getIMEI()
	{
		Context context = UIUtils.getContext();
		if (null == context)
		{
			return null;
		}
		String imei = null;
		try
		{
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			imei = tm.getDeviceId();
		}
		catch (Exception e)
		{
			LogUtils.logd(e.getMessage());
		}
		return imei;
	}

	/**
	 * 得到设备唯一吗
	 */
	public static String getDeviceId()
	{
		String serial = null;
		String m_szDevIDShort = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10
				+ Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10
				+ Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 + Build.TYPE.length() % 10 + Build.USER.length() % 10; // 13
																																// 位
		try
		{
			serial = Build.class.getField("SERIAL").get(null).toString();
			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
		}
		catch (Exception exception)
		{
			serial = "serial";
			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
		}
	}

	/**
	 * 判断某个界面是否在前台
	 * @param context
	 *   上下文
	 * @param className
	 *    某个界面名称
	 */
	public static boolean isForeground(Context context, String className) {
		if (context == null || TextUtils.isEmpty(className)) {
			return false;
		}
		//ActivityManager的功能是与系统中所有运行着的Activity交互提供了接口
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
		if (list != null && list.size() > 0) {
			ComponentName cpn = list.get(0).topActivity;
			if (className.equals(cpn.getClassName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断程序是否在前台运行
	 * @param context
	 * @return true在前台，false在后台
	 */
	public static boolean isAppForeground(Context context) {
		boolean isForground = false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
			for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
				//前台程序
				if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					for (String pkgName : processInfo.pkgList) {
						if (pkgName.equals(context.getPackageName())) {
							isForground = true;
						}
					}
				}
			}
		} else {
			//@deprecated As of {@link android.os.Build.VERSION_CODES#LOLLIPOP}, 从Android5.0开始不能使用getRunningTask函数
			List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
			ComponentName componentInfo = taskInfo.get(0).topActivity;
			if (componentInfo.getPackageName().equals(context.getPackageName())) {
				isForground = true;
			}
		}

		return isForground;
	}

	public static Intent getTopIntent(Context context) {
		//得到栈顶的activity，意图
		// 获取ActivityManager
		ActivityManager mAm = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		// 获得当前运行的task
		List<ActivityManager.RunningTaskInfo> taskList = mAm.getRunningTasks(100);
		for (ActivityManager.RunningTaskInfo rti : taskList) {
			// 找到当前应用的task，并启动task的栈顶activity，达到程序切换到前台
			if (rti.topActivity.getPackageName().equals(context.getPackageName())) {
				Intent resultIntent = null;
				try {
					resultIntent = new Intent(context, Class.forName(rti.topActivity.getClassName()));
					resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//					context.startActivity(resultIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				return resultIntent;
			}
		}
		// 若没有找到运行的task，用户结束了task或被系统释放，则重新启动mainactivity
		Intent resultIntent = context.getPackageManager().getLaunchIntentForPackage(context.getApplicationInfo().packageName);
		resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//		context.startActivity(resultIntent);
		return resultIntent;
	}
}
