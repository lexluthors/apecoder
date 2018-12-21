/**
 * 
 */
package com.apecoder.club.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 当前应用的版本信息
 * 
 * @author houjun Create on 2015-9-8 上午9:52:04
 */
public class VersionUtils
{

	/**
	 * 获取应用程序的版本号
	 */
	public static String getVersionName(Context context)
	{
		try
		{
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version;
		}
		catch (Exception exception)
		{
		}
		return "";
	}
}
