package frox.world.com.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

import frox.world.com.R;
import frox.world.com.controller.card.Card2Activity;
import frox.world.com.model.Card;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Card card;
    private TextView textView_reponse;

    private RatingBar ratingBarStar;
    private RatingBar ratingBar0;
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;
    private RatingBar ratingBar3;
    private RatingBar ratingBar4;

    private float rating0 = 0;
    private float rating1 = 0;
    private float rating2 = 0;
    private float rating3 = 0;
    private float rating4 = 0;

    private TextView total;
    private EditText info;
    private EditText climbingroute;

    private Button buttonSave;

    private ImageButton buttonReset0;
    private ImageButton buttonReset1;
    private ImageButton buttonReset2;
    private ImageButton buttonReset3;
    private ImageButton buttonReset4;

    private ImageButton  picture;
    private String user;
    private String[] climbingrouteArray;
    private String[] bonusArray;
    private String[] routeTypeArray;
    private String[] zoneTypeArray;
    private Intent intentGame;
    private Spinner spinnerDifficulty;
    private Spinner spinnerBonus;
    private Spinner spinerZoneType;
    private Spinner spinnerRouteType;

    private String difficulty = "";
    private String bonus = "";
    private String routeType ="";
    private String zoneType ="";
    private String rank;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        intentGame = getIntent();
        user = intentGame.getStringExtra("user");

        spinnerDifficulty = findViewById(R.id.activity_game_spinner_difficulty);
        spinner(spinnerDifficulty, 0);

        spinnerBonus = findViewById(R.id.activity_game_spinner_bonus);
        spinner(spinnerBonus, 1);

        spinnerRouteType = findViewById(R.id.activity_game_spinner_route_type);
        spinner(spinnerRouteType, 2);

        spinerZoneType = findViewById(R.id.activity_game_spinner_zone_type);
        spinner(spinerZoneType, 3);

        info = findViewById(R.id.activity_game_info);
        climbingroute = findViewById(R.id.activity_game_climbingroute_name);

        picture = findViewById(R.id.activity_game_button_picture);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pictureIntent = new Intent(GameActivity.this, PhotoActivity.class);
                startActivity(pictureIntent);
            }
        });

        addListenerOnRatingBar();
        addListenerOnButton();

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void essai(Spinner spinner, ArrayAdapter<CharSequence> adapter, int ess) {
        //spinner.setAdapter(adapter);

        switch(ess){
            case 0:{
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        difficulty = (spinnerDifficulty).getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
            }
            break;
            case 1:{
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        bonus = (spinnerBonus).getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
            }
            break;
            case 2:{
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        routeType = (spinnerRouteType).getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
            }
            case 3:{
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        zoneType = (spinerZoneType).getSelectedItem().toString();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });
            }
        }
    }


    //choix du spinner 0 ou  1
    public void spinner(Spinner spinner, int choice) {

        //les deux methodes marches
        Resources res = getResources();
        climbingrouteArray = res.getStringArray(R.array.climbingroute_array);
        bonusArray = res.getStringArray(R.array.bonus_array);
        routeTypeArray = res.getStringArray(R.array.route_type_array);
        zoneTypeArray = res.getStringArray(R.array.route_type_array);

        ArrayAdapter<CharSequence> adapter;
        switch (choice) {
            case 0: {
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.climbingroute_array,
                        android.R.layout.simple_spinner_item);
                        spinner.setAdapter(adapter);
                        essai(spinner, adapter,0);
            }
            break;
            case 1: {
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.bonus_array,
                        android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                essai(spinner, adapter,1);
            }
            break;
            case 2: {
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.route_type_array,
                        android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                essai(spinner, adapter,2);
            }
            break;
            case 3: {
                adapter = ArrayAdapter.createFromResource(
                        this,
                        R.array.zone_type_array,
                        android.R.layout.simple_spinner_item);
                spinner.setAdapter(adapter);
                essai(spinner, adapter,3);
            }
            break;
        }
    }


    public void addListenerOnRatingBar() {

        //ratingBarStar = (RatingBar) findViewById(R.id.activity_game_ratingbar_star);
        ratingBar0 = findViewById(R.id.activity_game_ratingbar_technical);
        ratingBar1 = findViewById(R.id.activity_game_ratingbar_mental);
        ratingBar2 = findViewById(R.id.activity_game_ratingbar_tactical);
        ratingBar3 = findViewById(R.id.activity_game_ratingbar_physical);
        ratingBar4 = findViewById(R.id.activity_game_ratingbar_star);

        total = findViewById(R.id.activity_game_textview_total);

        //mise a jour du calcul
        ratingBar0.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating0 = (int) rating;
                updateRating();
            }
        });

        //mise a jour du calcul
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating1 = (int) rating;
                updateRating();
            }
        });

        //mise a jour du calcul
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating2 = (int) rating;
                updateRating();
            }
        });
        //mise a jour du calcul
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating3 = (int) rating;
                updateRating();
            }
        });

        //mise a jour du calcul pour le bouton star
        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating4 = (int) rating;
                updateRating();
            }
        });

    }


    public void updateRating() {
        String rating = "" + (rating0 + rating1 + rating2 + rating3) / 4;
        total.setText(rating);
    }


    public void addListenerOnButton() {

        ratingBarStar = findViewById(R.id.activity_game_ratingbar_star);
        buttonSave = findViewById(R.id.activity_main_button_save);

        buttonReset0 = findViewById(R.id.activity_game_button_reset_technical);
        buttonReset0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateRating();
                ratingBar0.setRating(0);
            }
        });

        buttonReset1 = findViewById(R.id.activity_game_button_reset_mental);
        buttonReset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRating();
                ratingBar1.setRating(0);
            }
        });
        buttonReset2 = findViewById(R.id.activity_game_button_reset_tactical);
        buttonReset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRating();
                ratingBar2.setRating(0);
            }
        });
        buttonReset3 = findViewById(R.id.activity_game_button_reset_physical);
        buttonReset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRating();
                ratingBar3.setRating(0);
            }
        });

        buttonReset4 = findViewById(R.id.activity_game_button_reset_star);
        buttonReset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRating();
                ratingBar4.setRating(0);
            }
        });


        //message a l ecran pour verifier les valeur passer avec putextra a l'intent suivant
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //creation de la boite de dialogue
                save();

            }

        });



    }

    //creation d'une boite de dialogue generique avec recuperation du message en fonction de la langue de l'utilisateur
    private void save() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String saving = getString(R.string.activity_game_saving);
        card = new Card();
        card.setFirstName(user);
        card.setBonus(bonus);
        card.setTechnical((int) ratingBar0.getRating());
        card.setMental((int) ratingBar1.getRating());
        card.setTactical((int) ratingBar2.getRating());
        card.setPhysical((int) ratingBar3.getRating());
        card.setStar((int) ratingBarStar.getRating());
        card.setDifficulty(difficulty);
        card.setInfo(info.getText().toString());
        card.setDate(Calendar.getInstance().getTime().toString());
        //card.setClimbingRouteName("orpierre");
        card.setClimbingRouteName(climbingroute.getText().toString());

        int place = new Random().nextInt(100);
        int random = new Random().nextInt(100)+100;
        card.setRank(place +"/"+random);


        //affichage d'un message de sauvegarde qui peut etre enlever juste en mettant intent et put extra
        // + intentGame.getStringExtra("user")
        builder.setTitle(saving)
                .setMessage(card.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent cardActivityIntent = new Intent(GameActivity.this, Card2Activity.class);
                        cardActivityIntent.putExtra("card", card);
                        startActivity(cardActivityIntent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}
