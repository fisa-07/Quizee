package com.example.quizee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Activity extends AppCompatActivity {

    private final String URL = "https://opentdb.com/api.php?amount=10&category=17&difficulty=easy&type=multiple";
    private final String URLKIDS = "https://opentdb.com/api.php?amount=10&category=32&difficulty=easy&type=multiple";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Activity.this,Load_Activity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}