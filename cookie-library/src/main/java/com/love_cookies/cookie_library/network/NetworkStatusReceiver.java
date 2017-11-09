package com.love_cookies.cookie_library.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.love_cookies.cookie_library.utils.LogUtils;
import com.love_cookies.cookie_library.utils.NetworkUtils;

import java.util.ArrayList;

/**
 * Created by xiekun on 2016/03/31.
 *
 * 网络状态监听广播
 */
public class NetworkStatusReceiver extends BroadcastReceiver {

    public final static String CUSTOM_NET_CHANGE_ACTION =
            "CUSTOM_NET_CHANGE_ACTION";

    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    private final static String TAG = NetworkStatusReceiver.class.getSimpleName();

    private static boolean isNetAvailable = false;

    private static NetworkUtils.NetworkType mNetworkType;

    private static ArrayList<NetworkChangeCallBack> mNetworkChangeCallBacks = new ArrayList<>();

    private static BroadcastReceiver mBroadcastReceiver;

    private static BroadcastReceiver getReceiver() {
        if (mBroadcastReceiver == null) {
            mBroadcastReceiver = new NetworkStatusReceiver();
        }
        return mBroadcastReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = NetworkStatusReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)
                || intent.getAction().equalsIgnoreCase(CUSTOM_NET_CHANGE_ACTION)) {
            if (!NetworkUtils.isNetworkAvailable(context)) {
                LogUtils.i(TAG, "<--- network disconnected --->");
                isNetAvailable = false;
            } else {
                LogUtils.i(TAG, "<--- network connected --->");
                isNetAvailable = true;
                mNetworkType = NetworkUtils.getAPNType(context);
            }
            notifyCallBack();
        }
    }

    /**
     * 刷新回调接口
     */
    private void notifyCallBack() {
        if (!mNetworkChangeCallBacks.isEmpty()) {
            int size = mNetworkChangeCallBacks.size();
            for (int i = 0; i < size; i++) {
                NetworkChangeCallBack callBack = mNetworkChangeCallBacks.get(i);
                if (callBack != null) {
                    if (isNetworkAvailable()) {
                        callBack.onNetConnected(mNetworkType);
                    } else {
                        callBack.onNetDisConnected();
                    }
                }
            }
        }
    }

    /**
     * 注册网络变化监听的广播
     *
     * @param mContext
     */
    public static void registerNetworkStateReceiver(Context mContext) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(CUSTOM_NET_CHANGE_ACTION);
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        mContext.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    /**
     * 注销网络变化监听的广播
     *
     * @param mContext
     */
    public static void unRegisterNetworkStateReceiver(Context mContext) {
        if (mBroadcastReceiver != null) {
            try {
                mContext.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {
                LogUtils.d(TAG, e.getMessage());
            }
        }
    }

    /**
     * 注册一个回调监听
     *
     * @param callBack
     */
    public static void registerNetChangeCallBack(NetworkChangeCallBack callBack) {
        if (mNetworkChangeCallBacks == null) {
            mNetworkChangeCallBacks = new ArrayList<>();
        }
        mNetworkChangeCallBacks.add(callBack);
    }

    /**
     * 注销一个回调监听
     *
     * @param callBack
     */
    public static void removeRegisterNetChangeCallBack(NetworkChangeCallBack callBack) {
        if (mNetworkChangeCallBacks != null) {
            if (mNetworkChangeCallBacks.contains(callBack)) {
                mNetworkChangeCallBacks.remove(callBack);
            }
        }
    }

    /**
     * @return 返回当前网络是否可以用
     */
    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    /**
     * @return 返回当前的网络类型
     */
    public static NetworkUtils.NetworkType getAPNType() {
        return mNetworkType;
    }
}
