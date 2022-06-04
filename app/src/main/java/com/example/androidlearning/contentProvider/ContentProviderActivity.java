package com.example.androidlearning.contentProvider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.example.androidlearning.R;
import com.example.androidlearning.storage.DataStorageActivity;

import javax.security.auth.login.LoginException;

public class ContentProviderActivity extends AppCompatActivity {
    private static final String TAG = "<ContentProviderActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        findViewById(R.id.btn_query_sms).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取内容处理者
                ContentResolver resolver = getContentResolver();
                //调用查询方法
                //sms:short message service
                //content://sms/inbox收件箱
                //content://sms/sent发件箱
                //content://sms/draft草稿箱
                Uri uri = Uri.parse("content://sms");
                Cursor c = resolver.query(uri, null, null, null, null);
                //解析cursor
                while (c.moveToNext()) {
                    //对象，内容
                    String msg = "";
                    String address = c.getString(Math.abs(c.getColumnIndex("address")));
                    String body = c.getString(Math.abs(c.getColumnIndex("body")));
                    msg = address + ":" + body;
                    Log.e(TAG, msg);
                }

                findViewById(R.id.btn_read_contact).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentResolver resolver = getContentResolver();
                        //对于联系人而言，姓名和其他内容（电话号码），由不同的contentProvider操作
                        //姓名和其他内容分属于不同的表
                        //姓名所在的表是主表，其他内容位于从表
                        //主表中的主键会在从表中作为外键使用
                        Cursor c = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null);
                        while (c.moveToNext()) {
                            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                            String _id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                            String selections = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
                            Cursor c2 = resolver.query(
                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    selections,
                                    new String[]{_id},
                                    null);
                            while (c2.moveToNext()) {
                                String phone = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                name += phone;
                            }
                            Log.e(TAG, "读取: " + name);
                        }

                    }
                });

                findViewById(R.id.btn_add_contact).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentResolver resolver = getContentResolver();
                        //往contentResolver中插入一条空数据，获取新生成的id
                        //利用刚刚生成的id分别组合姓名和电话号码往另一个contentProvider中插入数据
                        Uri uri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI, new ContentValues());
                        long id = ContentUris.parseId(uri);
                        //插入姓名
                        ContentValues values = new ContentValues();
                        values.put(ContactsContract.Data.RAW_CONTACT_ID, id);
                        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, "李清秋");
                        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                        //插入电话号码
                        values.clear();
                        values.put(ContactsContract.Data.RAW_CONTACT_ID, id);
                        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "13600050551");
                        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                        resolver.insert(ContactsContract.Data.CONTENT_URI, values);
                    }
                });
            }
        });
    }
}