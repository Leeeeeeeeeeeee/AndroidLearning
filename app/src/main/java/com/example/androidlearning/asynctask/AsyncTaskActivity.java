package com.example.androidlearning.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidlearning.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class AsyncTaskActivity extends AppCompatActivity {

    private static final String TAG = "AsyncTask";
    private static final String FILE_NAME = "qing.apk";
    private static final int INIT_PROGRESS = 1;
    private static final String URL = "http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssiatant_1.apk";
    private ProgressBar mPb;
    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        //初始化视图
        initView();
        //设置监听器
        setListeners();
        //初始化UI数据
        setData();
    }

    private void initView() {
        mPb = findViewById(R.id.pb_download);
        mBtnStart = findViewById(R.id.btn_download);
        mTvMsg = findViewById(R.id.tv_msg);
        mBtnCancel = findViewById(R.id.btn_download_cancel);
    }

    private void setListeners() {
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadAsyncTask().execute(URL);
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setData() {
        mPb.setProgress(INIT_PROGRESS);
        mTvMsg.setText("准备下载");
    }

    /**
     * String 入参
     * Integer 进度
     * Boolean 返回值
     */
    public class DownloadAsyncTask extends AsyncTask<String, Integer, Boolean> {

        private String mFilePath;

        /**
         * 在异步任务之前，在主线程中
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //可操作UI
            mTvMsg.setText("下载中");
        }

        /**
         * 在另外一个线程中处理事件
         *
         * @param params 入参
         * @return 结果
         */
        @Override
        protected Boolean doInBackground(String... params) {
            String urlStr = params[0];
            //抛出进度
            try {
                //构造URL
                java.net.URL url = new URL(urlStr);
                Log.d(TAG, "doInBackground:" + urlStr);
                //构造连接并打开
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                //获得了下载内容的总长度
                int contentLength = urlConnection.getContentLength();
                //下载地址准备
                mFilePath = Environment.getExternalStorageState() + File.separator + FILE_NAME;
                //对下载地址进行处理
                File apkFile = new File(mFilePath);
                if (apkFile.exists()) {
                    boolean result = apkFile.delete();
                    if (!result)
                        return false;
                }
                //已下载的大小
                int downloadSize = 0;
                //byte数组
                byte[] bytes = new byte[1024];
                int length;
                //创建输出管道
                OutputStream outputStream = new FileOutputStream(mFilePath);
                while ((length = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, length);
                    downloadSize += length;
                    //更新进度条
                    publishProgress(downloadSize * 100 / contentLength);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        /**
         * 主线程中，执行结果处理
         *
         * @param result doInBackground的操作
         */
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            mTvMsg.setText(result ? "下载完成：" + mFilePath : "下载失败");
        }

        /**
         * 进度参数改变时
         *
         * @param values 进度参数
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //收到进度，也是在UI线程中处理
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}