package com.love_cookies.cookie_library.demo.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.love_cookies.cookie_library.activity.BaseActivity;
import com.love_cookies.cookie_library.demo.R;

import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by xiekun on 2016/7/1 0001.
 *
 * Demo第二个页面
 */
@ContentView(R.layout.activity_second)
public class SecondActivity extends BaseActivity {

    @ViewInject(R.id.image_iv)
    private ImageView imageView;

    @Override
    public void initWidget(Bundle savedInstanceState) {
        ImageOptions imageOptions = new ImageOptions.Builder()
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(com.love_cookies.cookie_library.R.mipmap.default_img)
                .setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP)
                .setFailureDrawableId(com.love_cookies.cookie_library.R.mipmap.default_img)
                .build();
        x.image().bind(imageView, "assets://gate7.png", imageOptions);
    }

    @Override
    public void widgetClick(View view) {

    }
}
