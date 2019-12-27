package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

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

    }
}
