package com.example.androidlearning.asynctask;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 1.download方法 url localPath listener
 * 2.listener:start,success,fail,progress
 * 3.用asyncTask封装的
 */
public class DownloadHelper {
    // TODO: 2/22/2022 没怎么认真写，大概掌握思路，别直接用
    public static void download(String url, String localPath, OnDownloadListener listener) {
        DownloadAsyncTask task = new DownloadAsyncTask(url, localPath, listener);
        task.execute();
    }

    public static class DownloadAsyncTask extends AsyncTask<String, Integer, Boolean> {

        private static final String FILE_NAME = "qing.apk";
        private static final int INIT_PROGRESS = 1;

        String mUrl;
        String mFilePath;
        OnDownloadListener mListener;

        public DownloadAsyncTask(String url, String filePath, OnDownloadListener onDownloadListener) {
            mUrl = url;
            mFilePath = filePath;
            mListener = onDownloadListener;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mListener != null)
                mListener.onStart();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            //抛出进度
            try {
                //构造URL
                java.net.URL url = new URL(mUrl);
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
                    if (!result) {
                        if (mListener != null) {
                            mListener.onFail(-1, new File(mFilePath), "文件删除失败");
                        }
                        return false;
                    }
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
                inputStream.close();
                outputStream.close();
            } catch (MalformedURLException e) {
                if (mListener != null) {
                    mListener.onFail(-2, new File(mFilePath), e.getMessage());
                }
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                if (mListener != null) {
                    mListener.onFail(-2, new File(mFilePath), e.getMessage());
                }
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (mListener != null)
                if (result) {
                    mListener.onSuccess(0, new File(mFilePath));
                } else {
                    mListener.onFail(-1, new File(mFilePath), "下载失败");
                }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values != null && values.length > 0) {
                if (mListener != null) {
                    mListener.onProgress(values[0]);
                }
            }
        }
    }

    public interface OnDownloadListener {
        void onStart();

        void onSuccess(int code, File file);

        void onFail(int code, File file, String msg);

        void onProgress(int progress);

        //onStart on fail就变成了可选择项
        abstract class SimpleDownloadListener implements OnDownloadListener {
            @Override
            public void onSuccess(int code, File file) {

            }

            @Override
            public void onFail(int code, File file, String msg) {

            }
        }
    }
}
