package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import frox.world.com.R;
import frox.world.com.model.Card;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Card card;
    private TextView textView_reponse;

    private RatingBar ratingBarStar;
    private RatingBar ratingBar0;
    private RatingBar ratingBar1;
    private RatingBar ratingBar2;
    private RatingBar ratingBar3;

    private float rating0 =0;
    private float rating1 =0;
    private float rating2 =0;
    private float rating3 =0;

    private TextView activity_game_textview_total;
    private Button buttonSave;
    private ImageButton buttonReset0;
    private ImageButton buttonReset1;
    private ImageButton buttonReset2;
    private ImageButton buttonReset3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Spinner spinnerTechnical = findViewById(R.id.activity_game_spinner_level);
        spinner(spinnerTechnical);

        addListenerOnRatingBar();
        addListenerOnButton();


    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void spinner(Spinner spinner) {

        String[] items = new String[]{"1", "2", "3", "4a", "4b", "4c"};

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.climbingroute_array, android.R.layout.simple_spinner_item);
//set the spinners adapter to the previously created one.
        spinner.setAdapter(adapter);
    }

    public void addListenerOnRatingBar() {

        //ratingBarStar = (RatingBar) findViewById(R.id.activity_game_ratingbar_star);
        ratingBar0 = (RatingBar) findViewById(R.id.activity_game_ratingbar_technical);
        ratingBar1 = (RatingBar) findViewById(R.id.activity_game_ratingbar_mental);
        ratingBar2 = (RatingBar) findViewById(R.id.activity_game_ratingbar_tactical);
        ratingBar3 = (RatingBar) findViewById(R.id.activity_game_ratingbar_physical);

        activity_game_textview_total = (TextView) findViewById(R.id.activity_game_textview_total);

        //mise a jour du calcul
        ratingBar0.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating0 = (int)rating;
               updateRating();
                System.out.println(rating0);
            }
        });

        //mise a jour du calcul
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating1 = (int)rating;
                updateRating();
            }
        });

        //mise a jour du calcul
        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating2 = (int)rating;
                updateRating();
            }
        });
        //mise a jour du calcul
        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                rating3 = (int)rating;
                updateRating();
            }
        });

    }


    public void updateRating (){
        String rating = ""+ (rating0 + rating1 + rating2 + rating3)/4 ;
        activity_game_textview_total.setText(rating);
    }


    public void addListenerOnButton() {

        ratingBarStar =findViewById(R.id.activity_game_ratingbar_star);
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


        //if click on me, then display the current rating value.
        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cardActivityIntent = new Intent(GameActivity.this, CardActivity.class);
                startActivity(cardActivityIntent);

                Toast.makeText(GameActivity.this,
                        String.valueOf(ratingBarStar.getRating()),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }
}
