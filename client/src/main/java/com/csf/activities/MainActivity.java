package com.csf.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import services.GidService;

public class MainActivity extends AppCompatActivity {

    private GidService gidService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gidService = GidService.getInstance();
    }



    public void move(View view){
        Intent intent = new Intent(this, ArActivity.class);
        startActivity(intent);
    }

    public void locate(View view){

    }
}
