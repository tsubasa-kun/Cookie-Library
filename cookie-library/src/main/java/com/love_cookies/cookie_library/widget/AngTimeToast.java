package com.love_cookies.cookie_library.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xiekun on 2017/12/26.
 * <p>
 * 自定义时长的Toast（并不全机型兼容）
 */

public class AngTimeToast {
    private static final int LENGTH_SHORT_TIME = 2000;
    private static Context mContext = null;
    private static Toast mToast = null;
    private static TextView mTextView = null;
    private static int mDuration = 0;
    private static CharSequence mText = null;
    private Handler mHandler = new Handler();

    private AngTimeToast(Context context) {
        mContext = context;
    }

    public static AngTimeToast makeText(Context context, CharSequence text, int duration) {
        AngTimeToast instance = new AngTimeToast(context);
        mContext = context;
        mDuration = duration;
        mText = text;
        return instance;
    }

    private static void getToast(Context context, CharSequence text) {
        mToast = Toast.makeText(context, null, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.TOP, 0, 0);
        LinearLayout toastView = (LinearLayout) mToast.getView();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mTextView = new TextView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTextView.setLayoutParams(lp);
        mTextView.setTextSize(14);
        mTextView.setText(text);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setPadding(20, 20, 20, 20);
        mTextView.setBackgroundColor(Color.argb(100, 58, 208, 139));
        toastView.removeAllViews();
        toastView.setBackgroundColor(Color.TRANSPARENT);
        toastView.addView(mTextView);
    }

    public int getDuration() {
        return mDuration;
    }

    public void show() {
        mHandler.post(showRunnable);
    }

    public void hide() {
        mDuration = 0;
        if (mToast != null) {
            mToast.cancel();
        }
    }

    private Runnable showRunnable = new Runnable() {
        @Override
        public void run() {
            if (mToast != null) {
                mTextView.setText(mText);
            } else {
                getToast(mContext, mText);
            }
            if (mDuration != 0) {
                mToast.show();
            } else {
                hide();
                return;
            }

            if (mDuration > LENGTH_SHORT_TIME) {
                mHandler.postDelayed(showRunnable, LENGTH_SHORT_TIME);
                mDuration -= LENGTH_SHORT_TIME;
            } else {
                mHandler.postDelayed(showRunnable, mDuration);
                mDuration = 0;
            }
        }
    };
}
