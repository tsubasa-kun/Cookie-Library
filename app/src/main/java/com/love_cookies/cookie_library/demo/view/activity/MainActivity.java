package com.love_cookies.cookie_library.demo.view.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.adapter.CommonRecyclerAdapter;
import com.love_cookies.cookie_library.adapter.CommonRecyclerViewHolder;
import com.love_cookies.cookie_library.adapter.OnRecyclerItemClickListener;
import com.love_cookies.cookie_library.adapter.RecyclerViewDivider;
import com.love_cookies.cookie_library.utils.CircularAnimUtils;
import com.love_cookies.cookie_library.utils.ToastUtils;
import com.love_cookies.cookie_library.demo.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by xiekun on 2016/7/1 0001.
 *
 * Demo主页
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.recycle_view)
    private RecyclerView recyclerView;

    @ViewInject(R.id.blur_view)
    private BlurView blurView;

    private CommonRecyclerAdapter recyclerAdapter;
    private List<String> mData;

    /**
     * 初始化控件
     * @param savedInstanceState
     */
    @Override
    public void initWidget(Bundle savedInstanceState) {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("Hi~Cookie!　" + i);
        }

        recyclerAdapter = new CommonRecyclerAdapter<String>(this, R.layout.item_recycle_view, mData) {
            @Override
            public void setData(CommonRecyclerViewHolder holder, String s) {
                holder.setText(R.id.text_tv, s);
                holder.setImageWithUrl(R.id.image_iv, "assets://gate7.png");
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new RecyclerViewDivider(this, RecyclerViewDivider.VERTICAL_LIST));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                ToastUtils.show(MainActivity.this, "点击了" + position);
                CircularAnimUtils.startActivity(MainActivity.this, SecondActivity.class, view, R.color.SORA);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                ToastUtils.show(MainActivity.this, "长点击了" + position);
                turn(ThirdActivity.class);
                return true;
            }
        });

        setupBlurView();
    }

    /**
     * 控件的点击事件
     * @param view
     */
    @Override
    public void widgetClick(View view) {

    }

    /**
     * 设置高斯模糊View
     */
    private void setupBlurView() {
        final float radius = 16f;
        final View decorView = getWindow().getDecorView();
        //Activity's root View. Can also be root View of your layout
        final ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        //set background, if your root layout doesn't have one
        final Drawable windowBackground = decorView.getBackground();

        blurView.setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(this)) //Preferable algorithm, needs RenderScript support mode enabled
                .blurRadius(radius);
    }
}
