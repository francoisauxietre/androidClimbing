package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import frox.world.com.R;

public class FriendActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private Button ask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        firstName = findViewById(R.id.activity_friend_first_name);
        lastName = findViewById(R.id.activity_friend_last_name);
        ask = findViewById(R.id.activity_friend_ask);
        ask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sauvegarde dans firebase /friend en recuperant Id

            }
        });

    }
}
