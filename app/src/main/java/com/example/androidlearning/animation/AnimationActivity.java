package com.example.androidlearning.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidlearning.R;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        findViewById(R.id.btn_frame_animation).setOnClickListener(this);
        findViewById(R.id.btn_view_animation).setOnClickListener(this);
        findViewById(R.id.btn_property_animation).setOnClickListener(this);
        findViewById(R.id.btn_reveal_animation).setOnClickListener(this);
        findViewById(R.id.btn_view_transition_animation).setOnClickListener(this);
        findViewById(R.id.btn_activity_transition_animation).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_frame_animation:
                break;
            case R.id.btn_view_animation:
                break;
            case R.id.btn_property_animation: {
                Intent intent = new Intent(AnimationActivity.this, ObjectAnimationActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_reveal_animation: {
                Intent intent = new Intent(AnimationActivity.this, RevealAnimationActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_view_transition_animation: {
                Intent intent = new Intent(AnimationActivity.this, ViewTransitionAnimationActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_activity_transition_animation: {
                Intent intent = new Intent(AnimationActivity.this, ActivityTransitionAnimationActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}