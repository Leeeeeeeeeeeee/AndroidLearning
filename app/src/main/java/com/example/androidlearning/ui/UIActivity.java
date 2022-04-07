package com.example.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlearning.R;
import com.example.androidlearning.ui.expandable_list_view.ExpandableListViewActivity;
import com.example.androidlearning.ui.listview.ListViewActivity;

public class UIActivity extends AppCompatActivity {

    private Button mBtnTextView;
    private Button mBtnButton;
    private Button mBtnEditText;
    private Button mBtnRadioButton;
    private Button mBtnCheckBox;
    private Button mBtnImageBox;
    private Button mBtnListView;
    private Button mBtnToast;
    private Button mBtnDialog;
    private Button mBtnProgress;
    private Button mBtnCustomDialog;
    private Button mBtnPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        mBtnTextView = (Button) findViewById(R.id.btn_textview);
        mBtnButton = findViewById(R.id.btn_button);
        mBtnEditText = findViewById(R.id.btn_edittext);
        mBtnRadioButton = findViewById(R.id.btn_radiobutton);
        mBtnCheckBox = findViewById(R.id.btn_checkbox);
        mBtnImageBox = findViewById(R.id.btn_imageview);
        mBtnListView = findViewById(R.id.btn_listview);
        mBtnToast = findViewById(R.id.btn_toast);
        mBtnDialog = findViewById(R.id.btn_dialog);
        mBtnProgress = findViewById(R.id.btn_progress);
        mBtnCustomDialog = findViewById(R.id.btn_custom_dialog);
        mBtnPopupWindow = findViewById(R.id.btn_popup_window);


        OnClick onClick = new OnClick();
        mBtnTextView.setOnClickListener(onClick);
        mBtnButton.setOnClickListener(onClick);
        mBtnEditText.setOnClickListener(onClick);
        mBtnRadioButton.setOnClickListener(onClick);
        mBtnCheckBox.setOnClickListener(onClick);
        mBtnImageBox.setOnClickListener(onClick);
        mBtnListView.setOnClickListener(onClick);
        mBtnToast.setOnClickListener(onClick);
        mBtnDialog.setOnClickListener(onClick);
        mBtnProgress.setOnClickListener(onClick);
        mBtnCustomDialog.setOnClickListener(onClick);
        mBtnPopupWindow.setOnClickListener(onClick);

        findViewById(R.id.btn_expandable_listview).setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_textview:
                    //跳转到TextView演示页面
                    intent = new Intent(UIActivity.this, TextViewActivity.class);
                    break;
                case R.id.btn_button:
                    //跳转到Button演示页面
                    intent = new Intent(UIActivity.this, ButtonActivity.class);
                    break;
                case R.id.btn_edittext:
                    //跳转到EditText演示页面
                    intent = new Intent(UIActivity.this, EditTextActivity.class);
                    break;
                case R.id.btn_radiobutton:
                    //跳转到RadioButton演示页面
                    intent = new Intent(UIActivity.this, RadioButtonActivity.class);
                    break;
                case R.id.btn_checkbox:
                    //跳转到CheckBox演示页面
                    intent = new Intent(UIActivity.this, CheckBoxActivity.class);
                    break;
                case R.id.btn_imageview:
                    //跳转到ImageView演示页面
                    intent = new Intent(UIActivity.this, ImageViewActivity.class);
                    break;
                case R.id.btn_listview:
                    //跳转到ListView演示页面
                    intent = new Intent(UIActivity.this, ListViewActivity.class);
                    break;
                case R.id.btn_toast:
                    //跳转到Toast演示页面
                    intent = new Intent(UIActivity.this, ToastActivity.class);
                    break;
                case R.id.btn_dialog:
                    //跳转到Dialog演示页面
                    intent = new Intent(UIActivity.this, DialogActivity.class);
                    break;
                case R.id.btn_progress:
                    //跳转到Progress页面
                    intent = new Intent(UIActivity.this, ProgressActivity.class);
                    break;
                case R.id.btn_custom_dialog:
                    //跳转到CustomDialog页面
                    intent = new Intent(UIActivity.this, CustomDialogActivity.class);
                    break;
                case R.id.btn_popup_window:
                    //跳转到PopUpWindow页面
                    intent = new Intent(UIActivity.this, PopupWindowActivity.class);
                    break;
                case R.id.btn_expandable_listview:
                    intent = new Intent(UIActivity.this, ExpandableListViewActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }
}