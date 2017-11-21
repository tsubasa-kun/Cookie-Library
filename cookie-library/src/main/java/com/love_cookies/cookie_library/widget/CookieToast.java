package com.love_cookies.cookie_library.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.love_cookies.cookie_library.R;
import com.love_cookies.cookie_library.utils.PackageUtils;

/**
 * Created by xiekun on 2017/11/17.
 *
 * 带图标的Toast
 */

public class CookieToast {

    private Toast toast;

    /**
     * 自定义Toast（带图标）
     * @param context
     * @param text
     * @param imgRes
     * @param duration
     */
    public CookieToast(Context context, int imgRes, String text, int duration) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_cookie_toast, null, true);
        ImageView icon = (ImageView)view.findViewById(R.id.toast_icon);
        TextView message = (TextView)view.findViewById(R.id.toast_text);
        icon.setImageResource(imgRes);
        message.setText(text);
        toast = new Toast(context);
        toast.setDuration(duration);
        toast.setGravity(Gravity.CENTER, 0, 0);//居于屏幕中央
        toast.setView(view);
    }

    /**
     * 自定义Toast（不带图标）
     * @param context
     * @param text
     * @param duration
     */
    public CookieToast(Context context, String text, int duration) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_cookie_toast, null, true);
        ImageView icon = (ImageView)view.findViewById(R.id.toast_icon);
        TextView message = (TextView)view.findViewById(R.id.toast_text);
        icon.setVisibility(View.GONE);
        message.setText(text);
        toast = new Toast(context);
        toast.setDuration(duration);
        toast.setGravity(Gravity.CENTER, 0, 0);//居于屏幕中央
        toast.setView(view);
    }

    /**
     * 创建Toast
     * @param context
     * @param imgRes
     * @param textRes
     * @return
     */
    public static CookieToast makeToast(Context context, int imgRes, int textRes) {
        return new CookieToast(context, imgRes, context.getResources().getString(textRes), Toast.LENGTH_SHORT);
    }

    /**
     * 创建Toast
     * @param context
     * @param imgRes
     * @param text
     * @return
     */
    public static CookieToast makeToast(Context context, int imgRes, String text) {
        return new CookieToast(context, imgRes, text, Toast.LENGTH_SHORT);
    }

    /**
     * 创建Toast
     * @param context
     * @param imgName
     * @param text
     * @return
     */
    public static CookieToast makeToast(Context context, String imgName, String text) {
        int imgRes = context.getResources().getIdentifier(imgName, "mipmap", PackageUtils.getAppPackageName(context));
        return new CookieToast(context, imgRes, text, Toast.LENGTH_SHORT);
    }

    /**
     * 创建Toast
     * @param context
     * @param imgName
     * @param textRes
     * @return
     */
    public static CookieToast makeToast(Context context, String imgName, int textRes) {
        int imgRes = context.getResources().getIdentifier(imgName, "mipmap", PackageUtils.getAppPackageName(context));
        return new CookieToast(context, imgRes, context.getResources().getString(textRes), Toast.LENGTH_SHORT);
    }

    /**
     * 创建Toast
     * @param context
     * @param text
     * @return
     */
    public static CookieToast makeToast(Context context, String text) {
        return new CookieToast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * 创建Toast
     * @param context
     * @param textRes
     * @return
     */
    public static CookieToast makeToast(Context context, int textRes) {
        return new CookieToast(context, context.getResources().getString(textRes), Toast.LENGTH_SHORT);
    }

    /**
     * 展示
     */
    public void show() {
        if (toast != null) {
            toast.show();
        }
    }
}
