package com.example.androidlearning.animation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.widget.CheckBox;

import com.example.androidlearning.R;

public class RevealAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "<RevealAnimation";
    private View mView;
    private CheckBox mCbPlayAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal_animation);

        initView();
    }

    private void initView() {
        mView = findViewById(R.id.v_main);
        mCbPlayAnimation = findViewById(R.id.cb_play_animation);
        findViewById(R.id.btn_switch).setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        final boolean isPlayAnimationChecked = mCbPlayAnimation.isChecked();
        switch (view.getId()) {
            case R.id.btn_switch:
                handleChangeVisibility(isPlayAnimationChecked);
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void handleChangeVisibility(boolean isPlayAnimationChecked) {
        Log.d(TAG, "handleChangeVisibility() called with: playAnimation = [" + isPlayAnimationChecked + "]");
        Log.d(TAG, "handleChangeVisibility: " + mView.isShown());
        if (isPlayAnimationChecked) {
            if (mView.isShown()) {
                revealExit();
            } else {
                revealEnter();
            }
        } else {
            if (mView.isShown()) {
                mView.setVisibility(View.INVISIBLE);
            } else {
                mView.setVisibility(View.VISIBLE);
            }
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealExit() {

        int w = mView.getWidth();
        int h = mView.getHeight();

        //中心點坐標
        int cx = w;
        int cy = h;

        //半徑
        int r = (int) Math.hypot(w,h);

        Animator animator = ViewAnimationUtils.createCircularReveal(mView, cx, cy, r, 0);
        animator.setDuration(5000);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mView.setVisibility(View.INVISIBLE);
            }
        });
        animator.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void revealEnter() {

        int w = mView.getWidth();
        int h = mView.getHeight();

        //中心點坐標
        int cx = w;
        int cy = h;

        //半徑
        int r = (int) Math.hypot(w,h);

        Animator animator = ViewAnimationUtils.createCircularReveal(mView, cx, cy, 0, r);
        animator.setDuration(5000);
        mView.setVisibility(View.VISIBLE);
        animator.start();
    }
}