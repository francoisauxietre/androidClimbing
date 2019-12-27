package frox.world.com.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import frox.world.com.R;
import frox.world.com.model.Card;

public class CardActivity extends AppCompatActivity {
    private Card card;
    private TextView nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Intent intent = getIntent();
        card = (Card) intent.getSerializableExtra("card");
        nom = findViewById(R.id.activity_card_textview_name);
        nom.setText(card.getNom());
        //save();

    }

    //creation d'une boite de dialogue generique avec recuperation du message en fonction de la langue de l'utilisateur
    private void save(){
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
