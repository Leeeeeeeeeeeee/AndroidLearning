package com.example.androidlearning.ui.imageview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidlearning.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView mIv2, mIv3;
    private static final String IMG_URL = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        //原生加载网络图片
        mIv2 = findViewById(R.id.iv_2);
        //注意事项：最好加载前显示加载图片
        //注意事项：如果图片比较大，内存会溢出OOM
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(IMG_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    int code = httpURLConnection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mIv2.setImageBitmap(bitmap);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ImageViewActivity.this, "请求异常：" + code, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        //利用glide
        //注意事项：能够避免OOM问题
        mIv3 = findViewById(R.id.iv_3);
        //1.直接使用
        Glide.with(this).load(IMG_URL).into(mIv3);
        //2.配置Glide使用
//        RequestOptions requestOptions= new RequestOptions()
//                .placeholder(R.mipmap.loading)
//                .error(R.mipmap.loader_error)
//                .circleCrop();
//        Glide.with(this).load(IMG_URL).apply(requestOptions).into(mIv3);
        //3.通过GlideApp加载
//        GlideApp.with(this).load(IMG_URL).injectOptions().into(mIv3);
    }
}