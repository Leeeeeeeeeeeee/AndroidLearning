package com.example.androidlearning.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidlearning.MainActivity;
import com.example.androidlearning.R;
import com.example.androidlearning.util.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "<Test>";

    private static final int INTER_PROCESS = 1001;
    private static final int DOWNLOAD_PROGRESS_UPDATE = 1002;
    private static final int DOWNLOAD_PROGRESS_FAIL = 1003;
    private static final int COUNTDOWN_TIME_CODE = 1004;
    private static final int DELAY_MILLIS = 1000;
    private static final int MAX_COUNT = 10;


    private ProgressBar mPbDownload;
    TextView mTvTimer;

    MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
//        mHandler = new Handler();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(HandlerActivity.this,ButtonActivity.class);
//                startActivity(intent);
//            }
//        }, 3000);
        mHandler = new MyHandler(this);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn_interprocess_communication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interProcess();
            }
        });
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        download();
                    }
                }.start();
            }
        });
        mPbDownload = findViewById(R.id.pb_download);
        mTvTimer = findViewById(R.id.tv_timer);
        findViewById(R.id.btn_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer(MAX_COUNT - 1);
            }
        });
    }

    private void timer(int value) {
        Message message = Message.obtain();
        message.what = COUNTDOWN_TIME_CODE;
        message.arg1 = value;
        mHandler.sendMessageDelayed(message, DELAY_MILLIS);
    }

    /**
     *
     */
    private void download() {
        try {
            URL url = new URL("http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk");

            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            long contentLength = urlConnection.getContentLength();

            if (contentLength == 0) {
                Log.d(TAG, "contentLength:" + contentLength);
                Message message = new Message();
                message.what = DOWNLOAD_PROGRESS_FAIL;
                mHandler.sendMessage(message);
                return;
            }
            //别用这条，会报错，因为设备的默认存储路径可能不是实际存储路径
            //https://stackoverflow.com/questions/30216127/environment-getexternalstoragedirectory-returns-wrong-path
//            String downloadFolderName = Environment.getExternalStorageState() + File.separator + "download" + File.separator;
            String downloadFolderName = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + "download" + File.separator;

            File file = new File(downloadFolderName);
            if (!file.exists()) {
                file.mkdir();
            }

            String fileName = downloadFolderName + "qing.apk";

            File apkFile = new File(fileName);

            if (apkFile.exists()) {
                apkFile.delete();
            }

            int downloadSize = 0;
            byte[] bytes = new byte[1024];
            int length = 0;

            OutputStream outputStream = new FileOutputStream(fileName);
            while ((length = inputStream.read()) != -1) {
                outputStream.write(bytes, 0, length);
                downloadSize += length;
                Message message = Message.obtain();
                message.obj = downloadSize * 100 / contentLength;
                message.what = DOWNLOAD_PROGRESS_UPDATE;
                mHandler.sendMessage(message);
            }

        } catch (IOException e) {
            Message message = new Message();
            message.what = DOWNLOAD_PROGRESS_FAIL;
            mHandler.sendMessage(message);
            e.printStackTrace();
        }
    }

    private void interProcess() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Message message = new Message();
                message.what = INTER_PROCESS;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    public static class MyHandler extends Handler {
        final WeakReference<HandlerActivity> mWeakReference;

        public MyHandler(HandlerActivity activity) {
            this.mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            HandlerActivity activity = mWeakReference.get();
            switch (msg.what) {
                case INTER_PROCESS:
                    ToastUtil.showMsg(activity, "线程通信成功");
                    break;
                case DOWNLOAD_PROGRESS_UPDATE:
                    activity.mPbDownload.setProgress((Integer) msg.obj);
                    break;
                case DOWNLOAD_PROGRESS_FAIL:
                    ToastUtil.showMsg(activity, "下载失败！");
                    break;
                case COUNTDOWN_TIME_CODE:
                    int value = msg.arg1;
                    activity.mTvTimer.setText(String.valueOf(value--));
                    if (value >= 0)
                        activity.timer(value);
                    break;
            }
        }
    }

}