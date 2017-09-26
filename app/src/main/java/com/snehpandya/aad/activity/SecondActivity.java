package com.snehpandya.aad.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.snehpandya.aad.R;

/**
 * Created by Sneh on 25-09-2017.
 */

public class SecondActivity extends AppCompatActivity {

    private EditText mEditText;
    private TextView mTextView;
    private TextView mTextView2;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mEditText = (EditText) findViewById(R.id.edittext_name);
        mButton = (Button) findViewById(R.id.btn_second);
        mTextView = (TextView) findViewById(R.id.text_name_second);
        mTextView2 = (TextView) findViewById(R.id.text_hello);

        mTextView2.setText("Hello World!");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setText(mEditText.getText().toString());
            }
        });
    }
}
