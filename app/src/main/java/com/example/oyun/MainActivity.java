package com.example.oyun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void kolay(View view) {
        Intent intent = new Intent(MainActivity.this,KolayActivity.class);
        startActivity(intent);
    }
    public void orta(View view) {
        Intent intent = new Intent(MainActivity.this,OrtaActivity.class);
        startActivity(intent);
    }
    public void zor(View view)  {
        Intent intent = new Intent(MainActivity.this,ZorActivity.class);
        startActivity(intent);
    }
}