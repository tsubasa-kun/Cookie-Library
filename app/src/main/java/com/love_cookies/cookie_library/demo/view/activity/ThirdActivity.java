package com.love_cookies.cookie_library.demo.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.demo.R;
import com.love_cookies.cookie_library.widget.CookieTitleBar;
import com.love_cookies.cookie_library.widget.LoadAndRefreshView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by xiekun on 2017/11/06 0006.
 *
 * Demo第三个页面
 */
@ContentView(R.layout.activity_third)
public class ThirdActivity extends BaseActivity implements LoadAndRefreshView.OnFooterRefreshListener, LoadAndRefreshView.OnHeaderRefreshListener {

    @ViewInject(R.id.title_bar)
    private CookieTitleBar titleBar;
    @ViewInject(R.id.load_and_refresh_view)
    private LoadAndRefreshView loadAndRefreshView;
    @ViewInject(R.id.web_view)
    private WebView webView;

    private ImageView mCollectView;
    private boolean mIsSelected;


    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        initTitle();
        loadAndRefreshView.setOnHeaderRefreshListener(this);
        loadAndRefreshView.setOnFooterRefreshListener(this);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.baidu.com");
    }

    /**
     * 初始化TitleBar
     */
    public void initTitle() {
        titleBar.setImmersive(getWindow());//沉浸式状态栏
        titleBar.setBackgroundColor(Color.BLACK);
        titleBar.setLeftImageResource(R.mipmap.ic_launcher);
        titleBar.setLeftText("返回");
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        titleBar.setTitle("主标题\n副标题");
        titleBar.setTitleColor(Color.WHITE);
        titleBar.setSubTitleColor(Color.WHITE);
        titleBar.setDividerColor(Color.GRAY);

        titleBar.setActionTextColor(Color.WHITE);
        mCollectView = (ImageView) titleBar.addAction(new CookieTitleBar.ImageAction(R.mipmap.ic_launcher) {
            @Override
            public void performAction(View view) {
                Toast.makeText(ThirdActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                mCollectView.setImageResource(R.mipmap.ic_launcher);
                titleBar.setTitle(mIsSelected ? "文章详情\n朋友圈" : "帖子详情");
                mIsSelected = !mIsSelected;
            }
        });

        titleBar.addAction(new CookieTitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                Toast.makeText(ThirdActivity.this, "点击了发布", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 控件的点击事件
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }

    /**
     * 上拉加载
     * @param view
     */
    @Override
    public void onFooterRefresh(LoadAndRefreshView view) {
        onComplete();
    }

    /**
     * 下拉刷新
     * @param view
     */
    @Override
    public void onHeaderRefresh(LoadAndRefreshView view) {
        onComplete();
    }

    /**
     * 下拉刷新&上拉加载完成
     */
    public void onComplete() {
        //故意延迟3s
        final int duration = 3000;
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        loadAndRefreshView.onHeaderRefreshComplete();
                        loadAndRefreshView.onFooterRefreshComplete();
                        break;
                }
            }
        }.sendEmptyMessageDelayed(0, duration);
    }
}
