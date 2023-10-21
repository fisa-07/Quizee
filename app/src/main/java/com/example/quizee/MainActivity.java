package com.example.quizee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import modelPackage.Model;
public class MainActivity extends AppCompatActivity {

    TextView question,questionCount;
    Button refresh,option1, option2, option3, option4;
    int count = 0;
    String answer = "";
    int score = 0;
    private ArrayList<String> yourAnswer = new ArrayList<>();
    private ArrayList<Model> quizList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.question_text);
        option1 = findViewById(R.id.option1_text);
        option2 = findViewById(R.id.option2_text);
        option3 = findViewById(R.id.option3_text);
        option4 = findViewById(R.id.option4_text);
        refresh = findViewById(R.id.refresh);
        questionCount = findViewById(R.id.question_count);

        Intent intent = getIntent();
        String dataJson = intent.getStringExtra("quizList");

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

        refresh.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder builder;
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Want to Restart ?");
                builder.setTitle("Quizee");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    count = 0;
                    score = 0;
                    yourAnswer.clear();
                    loadView();
                });
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < quizList.size()){
                    yourAnswer.add(option1.getText().toString());
                    if(option1.getText().toString().equals(answer)){
                        score++;
                    }
                    count++;
                    loadView();
                }
            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < quizList.size()){
                    yourAnswer.add(option2.getText().toString());
                    if(option2.getText().toString().equals(answer)){
                        score++;
                    }
                    count++;
                    loadView();
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < quizList.size()){
                    yourAnswer.add(option3.getText().toString());
                    if(option3.getText().toString().equals(answer)){
                        score++;
                    }
                    count++;
                    loadView();
                }
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count < quizList.size()){
                    yourAnswer.add(option4.getText().toString());
                    if(option4.getText().toString().equals(answer)){
                        score++;
                    }
                    count++;
                    loadView();
                }
            }
        });
    }

    private void loadView() {
        AlertDialog.Builder builder;
        if (count < quizList.size()) {

            questionCount.setText("" + (count + 1));
            question.setText(quizList.get(count).getQuestion());
            option1.setText(quizList.get(count).getOption1());
            option2.setText(quizList.get(count).getOption2());
            option3.setText(quizList.get(count).getOption3());
            option4.setText(quizList.get(count).getOption4());
            answer = quizList.get(count).getCorrect();
        } else {
            builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("You score " + score + " !!");
            builder.setTitle("Score Board");
            builder.setCancelable(false);
            builder.setPositiveButton("Answer", (DialogInterface.OnClickListener) (dialog, which) -> {
                Gson gson = new Gson();
                String dataJson = gson.toJson(quizList);

                Intent intent = new Intent(MainActivity.this, Answer_Activity.class);
                intent.putExtra("quizList", dataJson);
                intent.putExtra("yourAnswerList", yourAnswer);
                startActivity(intent);
            });
            builder.setNegativeButton("Close", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
                finish();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
}



