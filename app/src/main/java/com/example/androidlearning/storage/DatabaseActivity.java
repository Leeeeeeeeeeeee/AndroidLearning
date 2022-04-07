
package com.example.androidlearning.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.androidlearning.R;

public class DatabaseActivity extends AppCompatActivity {

    private static final String TAG = "<DatabaseActivity>";
    private EditText mEtName, mEtAge, mEtNumber;
    private RadioButton mRbMan, mRbWoman;

    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        initView();

        initDb();
    }

    private void initView() {
        mEtName = findViewById(R.id.et_name);
        mEtAge = findViewById(R.id.et_age);
        mEtNumber = findViewById(R.id.et_number);
        mRbMan = findViewById(R.id.rb_man);
        mRbWoman = findViewById(R.id.rb_woman);
    }

    private void initDb() {
        databaseHelper = new MyDatabaseHelper(this);
    }

    public void operate(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                insert();
                break;
            case R.id.btn_search:
                search();
                break;
            case R.id.btn_delete:
                delete();
                break;
            case R.id.btn_alter:
                alter();
        }
    }

    private void insert() {
        String name = mEtName.getText().toString();
        if (name == null || name.equals("")) {
            Log.d(TAG, "name is empty");
            Toast.makeText(this, "姓名不能为空！", Toast.LENGTH_LONG).show();
            return;
        }
        String age = mEtAge.getText().toString();
        Boolean isMan;
        if (mRbWoman.isChecked() || mRbMan.isChecked()) {
            isMan = mRbMan.isChecked();
            try {
                int number = Integer.valueOf(mEtNumber.getText().toString());
                databaseHelper.insert(name, age, isMan, number);
            } catch (Exception e) {
                databaseHelper.insert(name, age, isMan, null);
            }
        } else {
            try {
                int number = Integer.valueOf(mEtNumber.getText().toString());
                databaseHelper.insert(name, age, null, number);
            } catch (Exception e) {
                databaseHelper.insert(name, age, null, null);
            }
        }
    }

    private void search() {
        ListView mLv = findViewById(R.id.lv_list);
        Cursor cursor = databaseHelper.search();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.item_db_list,
                cursor,
                new String[]{"_id", "name", "age", "isMan", "number"},
                new int[]{R.id.tv_id, R.id.tv_name, R.id.tv_age, R.id.tv_gender, R.id.tv_number},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        //将真实的数据变化显示到界面上
        mLv.setAdapter(adapter);
    }

    private void delete() {
    }

    private void alter() {
    }
}