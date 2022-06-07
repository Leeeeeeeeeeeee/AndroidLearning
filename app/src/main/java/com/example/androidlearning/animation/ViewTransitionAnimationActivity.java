package com.example.androidlearning.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.androidlearning.R;

public class ViewTransitionAnimationActivity extends AppCompatActivity {

    private static final String TAG = "<ViewTransition";
    private Scene mOverViewScene;
    private Scene mInfoScene;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transition_animation);

        ViewGroup sceneRoot = (ViewGroup) findViewById(R.id.fl_scene);

        mOverViewScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_overview, getBaseContext());
        mInfoScene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_info, getBaseContext());

        TransitionManager.go(mOverViewScene);

        //点击响应找不到函数，找不到原因,迫不得已出此下策,但会导致重新回到overview页面时点击失效
        sceneRoot.findViewById(R.id.btn_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //可以自定义转场类型
                Transition transition = TransitionInflater.from(getBaseContext()).inflateTransition(R.transition.transition);
                TransitionManager.go(mInfoScene, transition);
                sceneRoot.findViewById(R.id.btn_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TransitionManager.go(mOverViewScene);
                    }
                });
            }
        });

//        LinearLayout mLlOverView = (LinearLayout) getLayoutInflater().inflate(R.layout.scene_overview, null);
//        ImageButton mIbtnInfo = mLlOverView.findViewById(R.id.btn_info);
//        mIbtnInfo.setOnClickListener(this);
//        LinearLayout mLlInfo = (LinearLayout) getLayoutInflater().inflate(R.layout.scene_info, null);
//        ImageButton mIbtnClose = mLlInfo.findViewById(R.id.btn_close);
//        mIbtnClose.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View view) {
//        Log.e(TAG, "onClick: ");
//        switch (view.getId()) {
//            case R.id.btn_info:
//                Log.e(TAG, "onClick: btn_info");
//                TransitionManager.go(mInfoScene);
//                break;
//            case R.id.btn_close:
//                Log.e(TAG, "onClick: btn_close");
//                TransitionManager.go(mOverViewScene);
//                break;
//        }
//    }

    public void sceneOnClick(View view) {
        Log.e(TAG, "onClick: ");
        switch (view.getId()) {
            case R.id.btn_info:
                Log.e(TAG, "onClick: btn_info");
                break;
            case R.id.btn_close:
                Log.e(TAG, "onClick: btn_close");
                break;
        }
    }
}