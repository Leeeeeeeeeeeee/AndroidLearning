package com.example.androidlearning.frame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.androidlearning.R;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OKHttpActivity extends AppCompatActivity {

    private static final String TAG = "<OkHttp>";
    private OkHttpClient mClient;
    private TextView mTvMsgReceive;

    private static final String GET_URL = "https://raw.githubusercontent.com/square/okhttp/master/README.md";
    private static final String POST_URL = "https://api.github.com/markdown/raw";

    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        mClient = new OkHttpClient();
        mTvMsgReceive = findViewById(R.id.tv_msg_receive);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_get:
                get();
                break;
            case R.id.menu_response:
                response();
                break;
            case R.id.menu_post:
                post();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void post() {
        Request.Builder builder = new Request.Builder();
        builder.url(POST_URL);
        builder.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, "Hello world github/linguist#1**cool**， and #1!"));
        Request request = builder.build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String content = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTvMsgReceive.setText(content);
                        }
                    });
                }
            }
        });

    }

    private void response() {
        Request.Builder builder = new Request.Builder();
        builder.url(GET_URL);
        Request request = builder.build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], e = [" + e + "]");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                int code = response.code();
                Headers headers = response.headers();
                String content = response.body().string();
                final StringBuilder buf = new StringBuilder();
                buf.append("code: " + code);
                buf.append("\nHeaders: " + headers);
                buf.append("body: " + content);
                runOnUiThread(() -> mTvMsgReceive.setText((buf.toString())));
            }
        });
    }

    private void get() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            Request.Builder builder = new Request.Builder();
            builder.url(GET_URL);
            Request request = builder.build();
            Log.d(TAG, "run: " + request);
            Call call = mClient.newCall(request);
            try {
                Response response = call.execute();
                if (response.isSuccessful()) {
                    final String string = response.body().string();
                    runOnUiThread(() -> mTvMsgReceive.setText(string));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }

}