package com.love_cookies.cookie_library.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

/**
 * Created by xiekun on 2016/11/11 0011.
 *
 * 包工具类，获取包名版本名等
 */
public class PackageUtils {

    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本代码
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取包名
     * @param context
     * @return
     */
    public static String getAppPackageName(Context context) {
        try {
            String packageName = context.getPackageName();
            return packageName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取手机IMEI
     * 需要 android.permission.READ_PHONE_STATE 权限
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String IMEI = telephonyManager.getDeviceId().toLowerCase();
            return IMEI;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
