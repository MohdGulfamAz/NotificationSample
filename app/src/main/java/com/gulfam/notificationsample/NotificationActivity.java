package com.gulfam.notificationsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        TextView  mTextView = findViewById(R.id.textView);

        String message1 = getIntent().getStringExtra("message");
        mTextView.setText(message1);


    }
}