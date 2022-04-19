package com.example.androidlearning.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;

import com.example.androidlearning.R;

public class ObjectAnimationActivity extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animation);
        tvTest = findViewById(R.id.tv);

//        tvTest.animate().translationYBy(500).setDuration(2000).start();
//        tvTest.animate().alpha(0).setDuration(2000).start();

//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100);
////        valueAnimator.setDuration(2000);
////        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
////            @Override
////            public void onAnimationUpdate(ValueAnimator animation){
////                //valueAnimator 实际的值
////                Log.d("aaaa",animation.getAnimatedValue()+"");
////                //动画的进度0-1
////                Log.d("aaaa",animation.getAnimatedFraction()+"");
////            }
////        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tvTest,"translationY",0,500);
        objectAnimator.setDuration(2000);
        objectAnimator.start();


    }

}