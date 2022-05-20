package com.example.androidlearning.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import com.example.androidlearning.R;

public class ServiceActivity extends AppCompatActivity {

    private static final String TAG = "<ServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void operate(View v) {
        switch (v.getId()) {
            case R.id.start: {
                //启动服务 onCreate-->onStartCommand-->onDestroy
                //服务没有销毁的情况下，创建只有一次，启动可以有很多次
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
            }
            break;
            case R.id.stop: {
                //停止服务 onDestroy
                Intent intent = new Intent(this, MyService.class);
                stopService(intent);
            }
            break;
            case R.id.bind: {
                //绑定服务 onCreate-->onBind（同样的，onCreate在没有onDestroy的情况下只有一次）
                //服务不会在后台运行，退出程序时会自动销毁
                //绑定服务最大的作用是实现对Service执行的任务进行进度监控
                Intent intent = new Intent(this, MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);//后面的参数代表如果不存在则自动创建
            }
            break;
            case R.id.unbind: {
                //解绑服务 onUnbind(所有的activity都解绑后才能销毁)
                //如果一开始是通过启动服务创建了服务，必须停止服务才能销毁，如果一开始是通过绑定服务创建了服务，必须解绑服务才能销毁
                //服务已经存在，那么bindService只能使onBind方法被调用，而unbindService只能使onUnbind被调用
                unbindService(serviceConnection);
            }
            break;
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        //每次重新绑定时就会调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder mb = (MyService.MyBinder) service;
            int step = mb.getProcess();
            Log.e(TAG, "onServiceConnected: " + step);
        }

        //连接丢失时调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}