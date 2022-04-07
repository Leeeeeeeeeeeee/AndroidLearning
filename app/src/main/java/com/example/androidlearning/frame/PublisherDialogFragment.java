package com.example.androidlearning.frame;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.androidlearning.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PublisherDialogFragment extends DialogFragment {
    private static final String TAG = "<PublisherDialogFragment>";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Publisher");
        final String[] items = {"Listener:Success", "Listener:Failure", "Broadcast:Success", "Broadcast:Failure", "EventBus:Success", "EventBus:Failure", "EventBus:Positing", "EventBus:Main", "EventBus:MainOrdered", "EventBus:Background", "EventBus:Async", "EventBus:Sticky"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: {
                        //Success
                        if (onEventListener != null)
                            onEventListener.onSuccess();
                    }
                    break;
                    case 1: {
                        //Failure
                        if (onEventListener != null)
                            onEventListener.onFailure();
                    }
                    break;
                    case 2: {
                        final Intent intent = new Intent();
                        intent.setAction(EventBusActivity.HANDLE_EVENT_ACTION);
                        intent.putExtra(EventBusActivity.STATUS_KEY, true);
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                    }
                    break;
                    case 3: {
                        final Intent intent = new Intent();
                        intent.setAction(EventBusActivity.HANDLE_EVENT_ACTION);
                        intent.putExtra(EventBusActivity.STATUS_KEY, false);
                        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                    }
                    break;
                    case 4: {
                        EventBus.getDefault().post(new SuccessEvent());
                    }
                    break;
                    case 5: {
                        EventBus.getDefault().post(new FailureEvent());
                    }
                    break;
                    case 6: {
                        if (Math.random() > .5) {
                            new Thread("posting") {
                                @Override
                                public void run() {
                                    super.run();
                                    EventBus.getDefault().post(new PostingEvent(Thread.currentThread().toString()));
                                }
                            }.start();
                        } else {
                            EventBus.getDefault().post(new PostingEvent(Thread.currentThread().toString()));
                        }
                    }
                    break;
                    case 7: {
                        if (Math.random() > .5) {
                            new Thread("working-thread") {
                                @Override
                                public void run() {
                                    super.run();
                                    Log.d(TAG, "onClick: before @" + SystemClock.uptimeMillis());
                                    EventBus.getDefault().post(new MainEvent(Thread.currentThread().toString()));
                                    Log.d(TAG, "onClick: after @" + SystemClock.uptimeMillis());
                                }
                            }.start();
                        } else {
                            Log.d(TAG, "onClick: before @" + SystemClock.uptimeMillis());
                            EventBus.getDefault().post(new MainEvent(Thread.currentThread().toString()));
                            Log.d(TAG, "onClick: after @" + SystemClock.uptimeMillis());
                        }
                    }
                    break;
                    case 8: {
                        Log.d(TAG, "onClick: before @" + SystemClock.uptimeMillis());
                        EventBus.getDefault().post(new MainOrderedEvent(Thread.currentThread().toString()));
                        Log.d(TAG, "onClick: after @" + SystemClock.uptimeMillis());

                    }
                    break;
                    case 9: {
                        if (Math.random() > .5) {
                            final ExecutorService pool = Executors.newFixedThreadPool(1);
                            pool.submit(() -> EventBus.getDefault().post(new BackgroundEvent(Thread.currentThread().toString())));
                            pool.shutdown();
                        } else {
                            EventBus.getDefault().post(new BackgroundEvent(Thread.currentThread().toString()));
                        }
                    }
                    break;
                    case 10: {
                        if (Math.random() > .5) {
                            final ExecutorService pool = Executors.newFixedThreadPool(1);
                            pool.submit(() -> EventBus.getDefault().post(new AsyncEvent(Thread.currentThread().toString())));
                            pool.shutdown();
                        } else {
                            EventBus.getDefault().post(new AsyncEvent(Thread.currentThread().toString()));
                        }
                    }
                    break;
                    case 11: {
                        EventBus.getDefault().postSticky(new StickyMessageEvent("sticky-message-content"));

                    }
                }
            }
        });
        return builder.create();
    }

    private OnEventListener onEventListener;

    public interface OnEventListener {
        void onSuccess();

        void onFailure();
    }

    public void setOnEventListener(OnEventListener onEventListener) {
        this.onEventListener = onEventListener;
    }
}
