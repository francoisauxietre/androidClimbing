package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import frox.world.com.R;

public class HomeActivity extends AppCompatActivity {

    private TextView textView;
    private Image image;
    private Button buttonHome;
    private Button buttonStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       this.textView = findViewById(R.id.activity_home_textview_ess);
       this.buttonHome =findViewById(R.id.activity_home_button_home);
       this.buttonStart =findViewById(R.id.activity_home_button_start);
       this.buttonStart.setEnabled(false);

       //listener sur le champ de texte
       this.textView.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {
           buttonStart.setEnabled(s.toString().length()>1);
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });
        //listener sur le bouton
       this.buttonStart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }
}
