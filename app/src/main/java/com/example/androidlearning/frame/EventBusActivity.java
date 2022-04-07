package com.example.androidlearning.frame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.androidlearning.databinding.ActivityEventBusBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.androidlearning.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

public class EventBusActivity extends AppCompatActivity {

    private static final String TAG = "<EventBusActivity>";
    public static final String HANDLE_EVENT_ACTION = "handle_event_action";
    public static final String STATUS_KEY = "status";
    private ActivityEventBusBinding binding;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (HANDLE_EVENT_ACTION.equals(action)) {
                final boolean status = intent.getBooleanExtra(STATUS_KEY, true);
                if (status) {
                    setImageSrc(R.drawable.ic_mood_good);
                } else {
                    setImageSrc(R.drawable.ic_mood_bad);
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        final IntentFilter filter = new IntentFilter(HANDLE_EVENT_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, filter);
        EventBus.getDefault().register(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Subscriber");


        binding = ActivityEventBusBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(view -> {
            final PublisherDialogFragment fragment = new PublisherDialogFragment();
            fragment.show(getSupportFragmentManager(), "publisher");
            //解决方案1 监听器
            fragment.setOnEventListener(new PublisherDialogFragment.OnEventListener() {
                @Override
                public void onSuccess() {
                    setImageSrc(R.drawable.ic_mood_good);
                }

                @Override
                public void onFailure() {
                    setImageSrc(R.drawable.ic_mood_bad);
                }
            });
            //解决方案2 广播

        });
    }

    private void setPublisherThreadInfo(String threadInfo) {
        setTextView(R.id.tv_publisher_thread, threadInfo);
    }

    private void setSubscriberThreadInfo(String threadInfo) {
        setTextView(R.id.tv_subscriber_thread, threadInfo);
    }

    /**
     * should run in ui thread
     *
     * @param resId
     * @param info
     */
    private void setTextView(int resId, String info) {
        final TextView mTv = findViewById(resId);
        mTv.setText(info);
        mTv.setAlpha(.5f);
        mTv.animate().alpha(1).start();
    }


    private void setImageSrc(int resId) {

        ImageView mIvEmotion = (ImageView) findViewById(R.id.iv_emotion);

        mIvEmotion.setImageResource(resId);
    }


    @Override
    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_event_bus);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
        return super.onSupportNavigateUp();
    }

    @Subscribe
    public void onSuccessEvent(SuccessEvent event) {
        setImageSrc(R.drawable.ic_mood_good);
    }

    @Subscribe
    public void onFailureEvent(FailureEvent event) {
        setImageSrc(R.drawable.ic_mood_bad);
    }

    /**
     * POSTING
     * 发布事件的代码和运行处理的代码运行在同一线程上，是默认的线程模式
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPositingEvent(PostingEvent event) {
        final String threadInfo = Thread.currentThread().toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setPublisherThreadInfo(event.threadInfo);
                setSubscriberThreadInfo(threadInfo);
            }
        });
    }

    /**
     * MAIN
     * 如果发布事件的代码运行于主线程，EventBus直接呼叫订阅回调函数
     * 如果事件发布于非UI线程，EventBus把事件放在队列里等待处理
     * 订阅回调函数一定运行在主线程上
     * 缺点：如果订阅回调函数执行事件过长，发布方的后续会被阻塞
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainEvent(MainEvent event) {
        Log.d(TAG, "onMainEvent: enter @" + SystemClock.uptimeMillis());
        setPublisherThreadInfo(event.threadInfo);
        setSubscriberThreadInfo(Thread.currentThread().toString());
        Log.d(TAG, "onMainEvent: exit @" + SystemClock.uptimeMillis());
    }

    /**
     * MAIN-ORDER
     * 优点；发布方的后续不会被阻塞
     */
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void onMainOrderedEvent(MainOrderedEvent event) {
        Log.d(TAG, "onMainOrderedEvent: enter @" + SystemClock.uptimeMillis());
        setPublisherThreadInfo(event.threadInfo);
        setSubscriberThreadInfo(Thread.currentThread().toString());
        Log.d(TAG, "onMainOrderedEvent: exit @" + SystemClock.uptimeMillis());
    }

    /**
     * BACKGROUND
     * 订阅回调运行在后台线程
     * 如果发布方运行在后台线程，订阅方运行于同一线程
     * 如果发布方运行在主线程，订阅方新的
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundEvent(BackgroundEvent event) {
        final String threadInfo = Thread.currentThread().toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setPublisherThreadInfo(event.threadInfo);
                setSubscriberThreadInfo(threadInfo);
            }
        });
    }

    /**
     * ASYNC
     * 事件处理函数和BACKGROUND一样都运行在非UI线程上
     * ASYNC一定会运行在一个新开的线程中
     * 可以放心大胆的进行耗时操作，比如网络访问
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncEvent(AsyncEvent event) {
        final String threadInfo = Thread.currentThread().toString();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setPublisherThreadInfo(event.threadInfo);
                setSubscriberThreadInfo(threadInfo);
            }
        });
    }

    /**
     * 粘性事件
     * 可以先发布再订阅的事件
     */
    @Subscribe(sticky = true)
    public void onStickyMessageEvent(StickyMessageEvent event) {//这个方法必须是public 的
        setTitle(event.message);
    }

}