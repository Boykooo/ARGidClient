package com.csf.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.randomTextView);
    }

    public void changeField(View view){
        textView.setText("HELLO");
    }

    public void move(View view){
        Intent intent = new Intent(this, ArActivity.class);
        startActivity(intent);
    }

}
