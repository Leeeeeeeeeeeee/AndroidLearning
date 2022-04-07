package com.example.androidlearning.network;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.androidlearning.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NetworkOperationActivity extends AppCompatActivity {

    private static final String TAG = "<Test>";

    private TextView mTvGet, mTvPost, mTvGson;
    private EditText mEtUsername, mEtPassword;
    private ListView mLvJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_operation);

        initView();

    }

    private void initView() {
        //GET
        Button mBtnGet = findViewById(R.id.btn_get);
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread() {
                    @Override
                    public void run() {
                        //1.实例化一个URL对象
                        URL url = null;
                        try {
                            url = new URL("http://www.imooc.com/api/teacher?type=3&cid=1&type=3");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        final String msg = get(url);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvGet.setText(msg);
                            }
                        });
                    }
                }.start();
            }
        });
        mTvGet = findViewById(R.id.tv_get);
        //POST
        Button mBtnPost = findViewById(R.id.btn_post);
        mBtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mEtUsername.getText().toString();
                String password = mEtPassword.getText().toString();
                post(username, password);
            }
        });
        mTvPost = findViewById(R.id.tv_post);
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        //JSON
        Button mBtnJson = findViewById(R.id.btn_json);
        mBtnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL("http://www.imooc.com/api/teacher?type=2&cid=1");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        String jsonStr = get(url);
                        List<Map<String, String>> list = parseByJSON(jsonStr);
                        String[] from = {"id", "name"};
                        int[] to = {R.id.tv_id, R.id.tv_name};
                        final SimpleAdapter simpleAdapter = new SimpleAdapter(NetworkOperationActivity.this, list, R.layout.item_list_json, from, to);
                        //在UI线程中进行操作
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mLvJson.setAdapter(simpleAdapter);
                                simpleAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }.start();
            }
        });
        mLvJson = findViewById(R.id.iv_json);
        //GSON
        Button mBtnGson = findViewById(R.id.btn_gson);
        mBtnGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL("http://www.imooc.com/api/teacher?type=3&cid=1");
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        String jsonStr = get(url);
                        final Test t = parseByGSON(jsonStr);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvGson.setText(t.getData().toString());
                            }
                        });
                    }
                }.start();
            }
        });
        mTvGson = findViewById(R.id.tv_gson);
    }

    private String get(final URL url) {
        try {
            //HttpURLConnection

            //2.获得HttpURLConnection实例
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//得到的是URLConnection类，要强转成子类HttpURLConnection

            //3.设置和请求相关的属性
            //①请求方式
            conn.setRequestMethod("GET");
            //②请求超时时长
            conn.setConnectTimeout(6000);//6秒

            //4.获取响应码
            //5.判断响应码并获取响应数据
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //获取响应的正文
                InputStream in = conn.getInputStream();
                byte b[] = new byte[1024];
                int len = 0;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                //在循环中读取输入流
                //in.read(b); 该方法返回int类型的数据，代表的是实际读到的数据长度
                while ((len = in.read(b)) > -1) {
                    //将字节数组里面的内容存/写入缓存流
                    //参数1∶待写入的数组
                    //参数2:起点
                    //参数3:长度
                    baos.write(b, 0, len);
                }
//                        String msg = new String(baos.toByteArray());
                String msg = baos.toString();
                return msg;
//                        Log.d("<Test>", msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void post(final String username, final String password) {
        new Thread() {
            @Override
            public void run() {
                try {
                    //HttpURLConnection
                    //1.实例化一个URL对象
                    URL url = new URL("http://www.imooc.com/api/okhttp/postmethod");

                    //2.获得HttpURLConnection实例
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();//得到的是URLConnection类，要强转成子类HttpURLConnection

                    //3.设置和请求相关的属性
                    //①请求方式
                    conn.setRequestMethod("POST");
                    //②请求超时时长
                    conn.setConnectTimeout(6000);//6秒
                    //③设置允许输出
                    conn.setDoOutput(true);//指从客户端到服务端的输出
                    //④设置提交数据的类型
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    //4.获取输出流(请求正文)
                    OutputStream out = conn.getOutputStream();
                    out.write(("username=" + username + "&password=" + password).getBytes());
                    //5.获取响应码
                    //6.判断响应码并获取响应数据
                    if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        //获取响应的正文
                        InputStream in = conn.getInputStream();
                        byte b[] = new byte[1024];
                        int len = 0;
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        //在循环中读取输入流
                        //in.read(b); 该方法返回int类型的数据，代表的是实际读到的数据长度
                        while ((len = in.read(b)) > -1) {
                            //将字节数组里面的内容存/写入缓存流
                            //参数1∶待写入的数组
                            //参数2:起点
                            //参数3:长度
                            baos.write(b, 0, len);
                        }
//                        String msg = new String(baos.toByteArray());
                        String msg = baos.toString();
                        mTvPost.setText(msg);
//                        Log.d("<Test>", msg);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private List<Map<String, String>> parseByJSON(String jsonStr) {
        try {
            JSONObject jo = new JSONObject(jsonStr);
            int status = jo.getInt("status");
            Log.d(TAG, "status:" + status);
            JSONArray ary = jo.getJSONArray("data");
            List<Map<String, String>> list = new ArrayList<>();
            for (int i = 0; i < ary.length(); i++) {
                JSONObject obj = ary.getJSONObject(i);
                String name = obj.getString("name");
                String id = obj.getString("id");
                Map<String, String> map = new HashMap<>();
                map.put("name", name);
                map.put("id", id);
                list.add(map);
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Test parseByGSON(String jsonStr) {
        //实例化一个GSON对象（工具对象）
        Gson gson = new Gson();
        Test t = gson.fromJson(jsonStr, Test.class);
        return t;
    }
}