package com.example.quizee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import modelPackage.Model;

public class Load_Activity extends AppCompatActivity {

    private final String SCIENCEURL = "https://opentdb.com/api.php?amount=10&category=17&difficulty=easy&type=multiple";
    private final String URLMYTHOLOGY = "https://opentdb.com/api.php?amount=10&category=20&difficulty=easy&type=multiple";
    private final String URLGEO = "https://opentdb.com/api.php?amount=10&category=22&difficulty=easy&type=multiple";
    private  final  String URLSPORT = "https://opentdb.com/api.php?amount=10&category=21&difficulty=easy&type=multiple";
    private final String URLCOMPUTER = "https://opentdb.com/api.php?amount=10&category=18&difficulty=easy&type=multiple";
    Button scienceButton,mythologyButton,geographyButton,sportButton,computerButton;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        scienceButton = findViewById(R.id.science);
        mythologyButton = findViewById(R.id.mythology);
        geographyButton = findViewById(R.id.geography);
        sportButton = findViewById(R.id.sport);
        computerButton = findViewById(R.id.computer);
        progressBar = findViewById(R.id.progress);

        progressBar.setVisibility(View.GONE);

        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, SCIENCEURL, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    ArrayList<Model> data = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String question = jsonObject.getString("question");
                                        String correct = jsonObject.getString("correct_answer");
                                        JSONArray jsonArray1 = jsonObject.getJSONArray("incorrect_answers");
                                        String option1 = jsonArray1.getString(0);
                                        String option2 = jsonArray1.getString(1);
                                        String option3 = jsonArray1.getString(2);
                                        String option4 = correct;
                                        ArrayList<String> options = new ArrayList<>();
                                        options.add(option1);
                                        options.add(option2);
                                        options.add(option3);
                                        options.add(option4);
                                        Collections.shuffle(options);
                                        Model model = new Model(question,options.get(0),options.get(1),options.get(2),options.get(3),correct);
                                        data.add(model);
                                    }
                                    Gson gson = new Gson();
                                    String dataJson = gson.toJson(data);

                                    Intent intent = new Intent(Load_Activity.this, MainActivity.class);
                                    intent.putExtra("quizList", dataJson);
                                    startActivity(intent);
                                    progressBar.setVisibility(View.GONE);
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Load_Activity.this, "Issue in loading", Toast.LENGTH_SHORT).show();
                                    throw new RuntimeException(e);

                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Load_Activity.this, "Network issue", Toast.LENGTH_SHORT).show();

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });

        mythologyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, URLMYTHOLOGY, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    ArrayList<Model> data = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String question = jsonObject.getString("question");
                                        String correct = jsonObject.getString("correct_answer");
                                        JSONArray jsonArray1 = jsonObject.getJSONArray("incorrect_answers");
                                        String option1 = jsonArray1.getString(0);
                                        String option2 = jsonArray1.getString(1);
                                        String option3 = jsonArray1.getString(2);
                                        String option4 = correct;
                                        ArrayList<String> options = new ArrayList<>();
                                        options.add(option1);
                                        options.add(option2);
                                        options.add(option3);
                                        options.add(option4);
                                        Collections.shuffle(options);
                                        Model model = new Model(question,options.get(0),options.get(1),options.get(2),options.get(3),correct);
                                        data.add(model);
                                    }
                                    Gson gson = new Gson();
                                    String dataJson = gson.toJson(data);

                                    Intent intent = new Intent(Load_Activity.this, MainActivity.class);
                                    intent.putExtra("quizList", dataJson);
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Load_Activity.this, "Issue in loading", Toast.LENGTH_SHORT).show();
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Load_Activity.this, "Network issue", Toast.LENGTH_SHORT).show();

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });

        geographyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, URLGEO, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    ArrayList<Model> data = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String question = jsonObject.getString("question");
                                        String correct = jsonObject.getString("correct_answer");
                                        JSONArray jsonArray1 = jsonObject.getJSONArray("incorrect_answers");
                                        String option1 = jsonArray1.getString(0);
                                        String option2 = jsonArray1.getString(1);
                                        String option3 = jsonArray1.getString(2);
                                        String option4 = correct;
                                        ArrayList<String> options = new ArrayList<>();
                                        options.add(option1);
                                        options.add(option2);
                                        options.add(option3);
                                        options.add(option4);
                                        Collections.shuffle(options);
                                        Model model = new Model(question,options.get(0),options.get(1),options.get(2),options.get(3),correct);
                                        data.add(model);
                                    }
                                    Gson gson = new Gson();
                                    String dataJson = gson.toJson(data);

                                    Intent intent = new Intent(Load_Activity.this, MainActivity.class);
                                    intent.putExtra("quizList", dataJson);
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Load_Activity.this, "Issue in loading", Toast.LENGTH_SHORT).show();
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Load_Activity.this, "Network issue", Toast.LENGTH_SHORT).show();

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });

        sportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, URLSPORT, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    ArrayList<Model> data = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String question = jsonObject.getString("question");
                                        String correct = jsonObject.getString("correct_answer");
                                        JSONArray jsonArray1 = jsonObject.getJSONArray("incorrect_answers");
                                        String option1 = jsonArray1.getString(0);
                                        String option2 = jsonArray1.getString(1);
                                        String option3 = jsonArray1.getString(2);
                                        String option4 = correct;
                                        ArrayList<String> options = new ArrayList<>();
                                        options.add(option1);
                                        options.add(option2);
                                        options.add(option3);
                                        options.add(option4);
                                        Collections.shuffle(options);
                                        Model model = new Model(question,options.get(0),options.get(1),options.get(2),options.get(3),correct);
                                        data.add(model);
                                    }
                                    Gson gson = new Gson();
                                    String dataJson = gson.toJson(data);

                                    Intent intent = new Intent(Load_Activity.this, MainActivity.class);
                                    intent.putExtra("quizList", dataJson);
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Load_Activity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Load_Activity.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });

        computerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, URLCOMPUTER, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("results");
                                    ArrayList<Model> data = new ArrayList<>();
                                    for(int i=0;i<jsonArray.length();i++){
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        String question = jsonObject.getString("question");
                                        String correct = jsonObject.getString("correct_answer");
                                        JSONArray jsonArray1 = jsonObject.getJSONArray("incorrect_answers");
                                        String option1 = jsonArray1.getString(0);
                                        String option2 = jsonArray1.getString(1);
                                        String option3 = jsonArray1.getString(2);
                                        String option4 = correct;
                                        ArrayList<String> options = new ArrayList<>();
                                        options.add(option1);
                                        options.add(option2);
                                        options.add(option3);
                                        options.add(option4);
                                        Collections.shuffle(options);
                                        Model model = new Model(question,options.get(0),options.get(1),options.get(2),options.get(3),correct);
                                        data.add(model);
                                    }
                                    Gson gson = new Gson();
                                    String dataJson = gson.toJson(data);

                                    Intent intent = new Intent(Load_Activity.this, MainActivity.class);
                                    intent.putExtra("quizList", dataJson);
                                    progressBar.setVisibility(View.GONE);
                                    startActivity(intent);
                                } catch (JSONException e) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(Load_Activity.this, "Issue in loading", Toast.LENGTH_SHORT).show();
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Load_Activity.this, "Network issue", Toast.LENGTH_SHORT).show();

                            }
                        });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}