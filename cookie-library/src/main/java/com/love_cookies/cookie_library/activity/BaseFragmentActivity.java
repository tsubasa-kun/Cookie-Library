package com.love_cookies.cookie_library.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.love_cookies.cookie_library.application.ActivityCollections;
import com.love_cookies.cookie_library.network.NetworkChangeCallBack;
import com.love_cookies.cookie_library.network.NetworkStatusReceiver;
import com.love_cookies.cookie_library.utils.NetworkUtils;

import org.xutils.x;

/**
 * Created by xiekun on 2016/3/27.
 *
 * FragmentActivity基类
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    private NetworkChangeCallBack networkChangeCallBack;

    /**
     * 初始化控件和事件
     * @param savedInstanceState
     */
    public abstract void initWidget(Bundle savedInstanceState);

    /**
     * 控件的点击事件
     * @param view
     */
    public abstract void widgetClick(View view);

    /**
     * 网络连接了
     * @param type
     */
    public abstract void netConnected(NetworkUtils.NetworkType type);

    /**
     * 网络断开了
     */
    public abstract void netDisConnected();

    @Override
    public void onClick(View v) {
        widgetClick(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 竖屏锁定
        x.view().inject(this);
        ActivityCollections.getInstance().addActivity(this);//入栈
        initWidget(savedInstanceState);
        networkChangeCallBack = new NetworkChangeCallBack() {
            @Override
            public void onNetConnected(NetworkUtils.NetworkType type) {
                netConnected(type);
            }

            @Override
            public void onNetDisConnected() {
                netDisConnected();
            }
        };
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        NetworkStatusReceiver.registerNetChangeCallBack(networkChangeCallBack);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetworkStatusReceiver.removeRegisterNetChangeCallBack(networkChangeCallBack);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollections.getInstance().finishActivity(this);//出栈
        NetworkStatusReceiver.removeRegisterNetChangeCallBack(networkChangeCallBack);
    }

    /**
     * 跳转另一个活动
     *
     * @param clazz
     */
    protected void turn(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


    /**
     * 跳转另一个活动并传递参数
     *
     * @param clazz
     * @param bundle
     */
    protected void turn(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转另一个活动并结束当前
     *
     * @param clazz
     */
    protected void turnThenFinish(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        finish();
    }

    /**
     * 跳转另一个活动并结束，并传递参数
     *
     * @param clazz
     * @param bundle
     */
    protected void turnThenFinish(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        finish();
    }

    /**
     * 开始一个活动，并等待返回结果
     *
     * @param clazz
     * @param requestCode
     */
    protected void turnForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 开始一个活动，并等待返回结果，并传递参数
     *
     * @param clazz
     * @param requestCode
     * @param bundle
     */
    protected void turnForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
