package com.example.androidlearning.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;

import com.example.androidlearning.R;

public class ActivityTransitionAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_animation);

        findViewById(R.id.v_red).setOnClickListener(this);
        findViewById(R.id.v_green).setOnClickListener(this);
        findViewById(R.id.v_blue).setOnClickListener(this);
        findViewById(R.id.v_yellow).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int colorId = -1;
        switch (view.getId()) {
            case R.id.v_red:
                colorId = Color.rgb(255, 0, 0);
                break;
            case R.id.v_green:
                colorId = Color.rgb(0, 255, 0);
                break;
            case R.id.v_blue:
                colorId = Color.rgb(0, 0, 255);
                break;
            case R.id.v_yellow:
                colorId = Color.rgb(255, 255, 0);
                break;
        }
        Intent intent = new Intent(this, Activity2TransitionAnimationActivity.class);
        intent.putExtra("colorId", colorId);
        Transition transition = new Explode();
        transition.excludeTarget(android.R.id.statusBarBackground, true);
//        int actionBarId = getResources().getIdentifier("action_bar_container", "id", "android");
//        transition.excludeTarget(actionBarId, true);
        View decor = getWindow().getDecorView();
        int actionBarId = R.id.action_bar_container;
        transition.excludeTarget(decor.findViewById(actionBarId), true);

        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
        //从第二个activity跳转回来
        getWindow().setReenterTransition(transition);
        //设置共享元素的transition效果
        getWindow().setSharedElementEnterTransition(transition);
        Pair<View, String> sharedElement = Pair.create(view, "img");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,sharedElement);
        startActivity(intent, options.toBundle());
    }
}