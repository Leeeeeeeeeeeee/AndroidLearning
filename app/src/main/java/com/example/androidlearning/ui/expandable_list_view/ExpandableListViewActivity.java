package com.example.androidlearning.ui.expandable_list_view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.androidlearning.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandableListViewActivity extends AppCompatActivity {

    private static final String TAG = "<Test>";
    private ExpandableListView expandableListView;
    private BaseExpandableListAdapter mAdapter;
    private List<Chapter> mDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_list_view);

        initView();

        initEvents();
    }

    private void initView() {
        expandableListView = findViewById(R.id.expandable_list_view);
        mDatas.clear();
        mDatas.addAll(ChapterLab.generateMockDatas());
        mAdapter = new ChapterAdapter(this, mDatas);
        expandableListView.setAdapter(mAdapter);
    }

    private void initEvents() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(TAG, "onChildClick() called with: parent = [" + parent + "], v = [" + v + "], groupPosition = [" + groupPosition + "], childPosition = [" + childPosition + "], id = [" + id + "]");
                return false;
            }
        });
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.d(TAG, "onGroupClick() called with: parent = [" + parent + "], v = [" + v + "], groupPosition = [" + groupPosition + "], id = [" + id + "]");
                return false;
            }
        });
    }
}