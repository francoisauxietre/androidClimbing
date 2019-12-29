package frox.world.com.controller;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.TypeConverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import frox.world.com.R;
import frox.world.com.controller.cartography.CartographyActivity;
import frox.world.com.controller.climber.ClimberActivity;
import frox.world.com.controller.user.UserActivity;
import frox.world.com.room.AppDatabase;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;

    private Button createCard;
    private Button home;
    private Button api;
    private Button climber;
    private Button card;
    private Button url;
    private Button cartography;
    private Button userProfile;

    private String user;

    private AppDatabase db;

    private boolean firstNameActive;
    private boolean lastNameActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        //demande a l'utilisateur son nom et prenom
        firstName = findViewById(R.id.activity_main_edit_text_first_name);
        lastName = findViewById(R.id.activity_main_edit_text_last_name);

        //desactivation du bouton pour etre sur que l utilisateur rentre son nom avant de faire une carte
        createCard = findViewById(R.id.activity_main_button_create_card);
        createCard.setEnabled(false);

        home = findViewById(R.id.activity_main_button_home);
        api = findViewById(R.id.activity_main_button_api);

        climber = findViewById(R.id.activity_main_button_climber);
        //card = findViewById(R.id.activity_main_button_card);
        url = findViewById(R.id.activity_main_button_url);
        cartography = findViewById(R.id.mapview);

        userProfile = findViewById(R.id.activity_main_button_user_profile);
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userProfileIntent = new Intent(MainActivity.this, UserActivity.class);
                startActivity(userProfileIntent);
            }
        });



        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                firstNameActive = true;
                if (firstNameActive && lastNameActive){
                    createCard.setEnabled(s.toString().length() != 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lastNameActive = true;
                if (firstNameActive && lastNameActive){
                    createCard.setEnabled(s.toString().length() != 0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Hello " + firstName.getText() +" !";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                user = firstName.getText().toString() + " " + lastName.getText().toString();
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

        cartography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardActivity= new Intent(MainActivity.this, CartographyActivity.class);
                startActivity(cardActivity);
            }
        });




    }
    public static class Converters {
        @TypeConverter
        public static Date fromTimestamp(Long value) {
            return value == null ? null : new Date(value);
        }

        @TypeConverter
        public static Long dateToTimestamp(Date date) {
            return date == null ? null : date.getTime();
        }
    }

}