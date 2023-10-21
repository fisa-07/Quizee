package com.example.quizee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import modelPackage.Model;

public class Answer_Activity extends AppCompatActivity {

    Button backButton,next;
    TextView question, correct, your;
    private ArrayList<Model> quizList = new ArrayList<>();
    private  ArrayList<String> yourAnswerList = new ArrayList<>();
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        backButton = findViewById(R.id.back_btn);
        next = findViewById(R.id.next);
        question = findViewById(R.id.question1_text);
        correct = findViewById(R.id.answer_text);
        your = findViewById(R.id.your_answer_text);

        Intent intent = getIntent();
        String dataJson = intent.getStringExtra("quizList");
        yourAnswerList = intent.getStringArrayListExtra("yourAnswerList");

        if (dataJson != null) {
            // Deserialize the JSON string back to an ArrayList<Model>
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Model>>() {
            }.getType();
            ArrayList<Model> receivedData = gson.fromJson(dataJson, type);

            if (receivedData != null) {
                // You now have the ArrayList<Model> in receivedData
                quizList = receivedData;
                loadView();
            }
            else{
                Toast.makeText(this, "Some issues in receiving data", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Some issues in receiving json data", Toast.LENGTH_SHORT).show();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                loadView();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadView() {
        if (count < quizList.size()) {

            question.setText(quizList.get(count).getQuestion());
            correct.setText(quizList.get(count).getCorrect());
            your.setText(yourAnswerList.get(count));

        } else {
            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
        }
    }
}