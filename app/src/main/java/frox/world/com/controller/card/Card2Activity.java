package frox.world.com.controller.card;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import frox.world.com.R;
import frox.world.com.controller.fragment.CardFragment;
import frox.world.com.model.Card;

public class Card2Activity extends AppCompatActivity {
    private Card card;
    private TextView climbingroute;
    private TextView bonus;
    private TextView difficulty;
    private TextView rank;
    private TextView star;
    private TextView user;
    private TextView technical;
    private TextView tactical;
    private TextView mental;
    private TextView physical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

//        essai dimplementation de fragment
//        1 - Get our FragmentManager & FragmentTransaction (Inside an activity)
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        2 - Create a new fragment and add it into activty
//        CardFragment cardFragment = new CardFragment()
//      fragmentTransaction.add(R.id.fragment_container, cardFragment);
//       fragmentTransaction.commit();

//        FragmentManager fragmentManager = getFragmentManager();
//      FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, new CardFragment());
//        fragmentTransaction.commit();



        Intent intent = getIntent();
        card = (Card) intent.getSerializableExtra("card");
        Log.w("DEBUG", "" + card.getClimbingRouteName());

        climbingroute = findViewById(R.id.activity_card_climbingroute_name);
        climbingroute.setText("" + card.getClimbingRouteName());

        bonus = findViewById(R.id.activity_card_bonus);
        bonus.setText(card.getBonus().toString());
        star = findViewById(R.id.activity_card_star);
        star.setText("" + card.getStar());
        difficulty = findViewById(R.id.activity_card_difficulty);
        difficulty.setText(card.getDifficulty());
        rank = findViewById(R.id.activity_card_rank);
        rank.setText(card.getRank());
        technical = findViewById(R.id.activity_card_technical);
        technical.setText(R.string.activity_card_technical);
        tactical = findViewById(R.id.activity_card_tactical);
        tactical.setText(R.string.activity_card_tactical);
        mental = findViewById(R.id.activity_card_mental);
        mental.setText(R.string.activity_card_mental);
        physical = findViewById(R.id.activity_card_physical);
        physical.setText(R.string.activity_card_physical);
        user = findViewById(R.id.activity_card_user);
        user.setText("" + card.getFirstName());

        ImageView t1 = findViewById(R.id.activity_card_technical_progress1);
        ImageView t2 = findViewById(R.id.activity_card_technical_progress2);
        ImageView t3 = findViewById(R.id.activity_card_technical_progress3);
        ImageView t4 = findViewById(R.id.activity_card_technical_progress4);
        ImageView t5 = findViewById(R.id.activity_card_technical_progress5);
        int number = card.getTechnical();
        showProgress(number, t1, t2, t3, t4, t5);

        t1 = findViewById(R.id.activity_card_tactical_progress1);
        t2 = findViewById(R.id.activity_card_tactical_progress2);
        t3 = findViewById(R.id.activity_card_tactical_progress3);
        t4 = findViewById(R.id.activity_card_tactical_progress4);
        t5 = findViewById(R.id.activity_card_tactical_progress5);
        number = card.getTactical();
        showProgress(number, t1, t2, t3, t4, t5);

        t1 = findViewById(R.id.activity_card_mental_progress1);
        t2 = findViewById(R.id.activity_card_mental_progress2);
        t3 = findViewById(R.id.activity_card_mental_progress3);
        t4 = findViewById(R.id.activity_card_mental_progress4);
        t5 = findViewById(R.id.activity_card_mental_progress5);
        number = card.getMental();
        showProgress(number, t1, t2, t3, t4, t5);

        t1 = findViewById(R.id.activity_card_physical_progress1);
        t2 = findViewById(R.id.activity_card_physical_progress2);
        t3 = findViewById(R.id.activity_card_physical_progress3);
        t4 = findViewById(R.id.activity_card_physical_progress4);
        t5 = findViewById(R.id.activity_card_physical_progress5);
        number = card.getPhysical();
        showProgress(number, t1, t2, t3, t4, t5);

   }

 //
    public void showProgress(int number, ImageView t1, ImageView t2, ImageView t3, ImageView t4, ImageView t5) {
        switch (number) {
            case 0: {
                t1.setImageAlpha(0);
                t2.setImageAlpha(0);
                t3.setImageAlpha(0);
                t4.setImageAlpha(0);
                t5.setImageAlpha(0);
            }
            break;

            case 1: {
                t1.setImageAlpha(255);
                t2.setImageAlpha(0);
                t3.setImageAlpha(0);
                t4.setImageAlpha(0);
                t5.setImageAlpha(0);
            }
            break;
            case 2: {
                t1.setImageAlpha(255);
                t2.setImageAlpha(255);
                t3.setImageAlpha(0);
                t4.setImageAlpha(0);
                t5.setImageAlpha(0);
            }
            break;
            case 3: {
                t1.setImageAlpha(255);
                t2.setImageAlpha(255);
                t3.setImageAlpha(255);
                t4.setImageAlpha(0);
                t5.setImageAlpha(0);
            }
            break;
            case 4: {
                t1.setImageAlpha(255);
                t2.setImageAlpha(255);
                t3.setImageAlpha(255);
                t4.setImageAlpha(255);
                t5.setImageAlpha(0);
            }
            break;
            case 5: {
                t1.setImageAlpha(255);
                t2.setImageAlpha(255);
                t3.setImageAlpha(255);
                t4.setImageAlpha(255);
                t5.setImageAlpha(255);
            }
        }
    }

//    creation d'une boite de dialogue generique avec recuperation du message en fonction de la langue de l'utilisateur
    private void save() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String saving = getString(R.string.activity_game_saving);
        builder.setTitle(saving)
                .setMessage(card.toString())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .create()
                .show();

    }
}
