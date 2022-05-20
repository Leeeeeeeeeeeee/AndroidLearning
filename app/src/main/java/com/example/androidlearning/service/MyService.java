package com.example.androidlearning.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "<MyService";
    private int i;

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate() called");
        //耗时操作放在这里，不要放在onBind，因为Activity销毁后就会终止过程
        super.onCreate();

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    for (i = 1; i <= 100; i++) {
                        sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() called with: intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }


    //对于onBind方法而言，要求返回IBinder对象
    //实际上，我们会自己定义一个内部类，集成Binder类
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() called with: intent = [" + intent + "]");
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() called with: intent = [" + intent + "]");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() called");
        super.onDestroy();
    }

    class MyBinder extends Binder {
        //实现进度监控
        public int getProcess(){
            return i;
        }
    }
}