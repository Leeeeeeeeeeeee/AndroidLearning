package com.example.androidlearning.ui.expandable_list_view;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChapterBiz {
    private static final String TAG = "<ChapterBiz>";
    public static final String URL = "www.imooc.com/api/expandablelistview";

    public void loadDatas(Context context, CallBack callBack, boolean useCache) {
        AsyncTask<Boolean, Void, List<Chapter>> asyncTask = new AsyncTask<Boolean, Void, List<Chapter>>() {

            private Exception ex;

            @Override
            protected List<Chapter> doInBackground(Boolean... booleans) {
                boolean isUseCache = booleans[0];
                List<Chapter> chapterList = new ArrayList<>();
                try {
                    if (isUseCache) {

                    } else {

                    }
                    //loadFromNet
                    if (chapterList.isEmpty()) {
                        List<Chapter> chapterListFromNet = loadFromNet(context);
                        chapterList.addAll(chapterListFromNet);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.ex = e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Chapter> chapters) {
                super.onPostExecute(chapters);
                if (null != ex) {
                    callBack.onFailed(ex);
                    return;
                }
                callBack.onSuccess(chapters);
            }
        };
        asyncTask.execute(useCache);
    }

    private List<Chapter> loadFromNet(Context context) {
        List<Chapter> chapterList = new ArrayList<>();
        String content = HttpUtils.doGet(URL);
        Log.d(TAG, "loadFromNet: " + content);
        if (null != content) {
            chapterList = parseContent(content);
        }
        return chapterList;
    }

    private List<Chapter> parseContent(String content) {
        List<Chapter> chapterList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chapterList;
    }

    public static interface CallBack {
        void onSuccess(List<Chapter> chapterList);

        void onFailed(Exception ex);
    }
}
