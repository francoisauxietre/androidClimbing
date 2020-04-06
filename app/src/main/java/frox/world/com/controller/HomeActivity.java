package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import frox.world.com.R;
import frox.world.com.controller.card.Card2Activity;
import frox.world.com.controller.cartography.CartographyActivity;
import frox.world.com.controller.user.UserActivity;

public class HomeActivity extends AppCompatActivity {


    private ImageButton imageButtonFriends;
    private ImageButton imageButtonProfile;
    private ImageButton imageButtonCard;
    private ImageButton imageButtonMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.imageButtonFriends = findViewById(R.id.activity_home_button_friends);
        this.imageButtonProfile = findViewById(R.id.activity_home_button_profile);
        this.imageButtonCard = findViewById(R.id.activity_home_button_carte_min);
        this.imageButtonMaps = findViewById(R.id.activity_home_button_maps);

        //listener sur le bouton
        this.imageButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilIntent = new Intent(HomeActivity.this, UserActivity.class);
                profilIntent.putExtra("firstName", ' ');
                profilIntent.putExtra("lastName", ' ');
                startActivity(profilIntent);
            }
        });

        this.imageButtonFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friendsIntent = new Intent(HomeActivity.this, FriendActivity.class);
                startActivity(friendsIntent);
            }
        });



        this.imageButtonCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardIntent = new Intent(HomeActivity.this, CardActivity.class);
                startActivity(cardIntent);
            }
        });


        this.imageButtonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardIntent = new Intent(HomeActivity.this, CartographyActivity.class);
                startActivity(cardIntent);
            }
        });

    }
}
