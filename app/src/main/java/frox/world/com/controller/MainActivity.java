package frox.world.com.controller;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import frox.world.com.R;
import frox.world.com.model.User;

import android.app.ProgressDialog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button createCard;
    private Button home;
    private Button api;
    private Button climber;
    private Button card;
    private Button url;
    private String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // textView = findViewById(R.id.activity_main_textview);
        editText = findViewById(R.id.activity_main_hello_input);
        //desactivation du bouton pour etre sur que l utilisateur rentre son nom avant de faire une carte
        createCard = findViewById(R.id.activity_main_button_create_card);
        createCard.setEnabled(false);
        home = findViewById(R.id.activity_main_button_home);
        api = findViewById(R.id.activity_main_button_api);
        climber = findViewById(R.id.activity_main_button_climber);
        card = findViewById(R.id.activity_main_button_Card);
        url = findViewById(R.id.activity_url_button);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                createCard.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Hello " + editText.getText() +" !";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                user = editText.getText().toString();
                gameActivityIntent.putExtra("user",user);
                startActivity(gameActivityIntent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeActivityIntent);
            }
        });

        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apiActivityIntent = new Intent(MainActivity.this, ApiActivity.class);
                startActivity(apiActivityIntent);
        }});

        climber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent climberActivity= new Intent(MainActivity.this, ClimberActivity.class);
                startActivity(climberActivity);
            }
        });




    }


}