package com.love_cookies.cookie_library.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by xiekun on 2016/11/11 0011.
 *
 * 获取手机型号，版本号等信息
 *
 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
 */
public class PhoneUtils {

    /**
     * 获取手机系统版本号
     * @return
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     * @return
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     * @return
     */
    public static String getSystemBrand() {
        return Build.BRAND;
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
