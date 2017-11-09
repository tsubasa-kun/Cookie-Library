package com.love_cookies.cookie_library.network;

import com.love_cookies.cookie_library.utils.NetworkUtils;

/**
 * Created by xiekun on 2016/03/31.
 *
 * 网络状况发生改变的时候的回调接口
 */
public interface NetworkChangeCallBack {
    void onNetConnected(NetworkUtils.NetworkType type);//网络连接

    void onNetDisConnected();//网络断开
}
