package com.example.androidlearning.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Transition;
import android.view.View;

import com.example.androidlearning.R;

public class Activity2TransitionAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_transition_animation);
        int colorId = getIntent().getExtras().getInt("colorId");
        View view = findViewById(R.id.view);
        view.setBackgroundColor(colorId);
        //也可以用代码设置transitionName
        view.setTransitionName("img");
        Transition transition = new Explode();
        transition.excludeTarget(android.R.id.statusBarBackground, true);
//        int actionBarId = getResources().getIdentifier("action_bar_container", "id", "android");
//        transition.excludeTarget(actionBarId, true);
        View decor = getWindow().getDecorView();
        int actionBarId = R.id.action_bar_container;
        transition.excludeTarget(decor.findViewById(actionBarId), true);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }
}