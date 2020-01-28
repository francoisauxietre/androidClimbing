package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import frox.world.com.R;
import frox.world.com.firebase.FirebaseDatabaseHelper;
import frox.world.com.model.Climber;
import frox.world.com.model.Friend;

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
                //sauvegarde dans firebase
                Toast.makeText(FriendActivity.this, "Save in firebase", Toast.LENGTH_SHORT).show();

                Friend friend = new Friend();
                friend.setId(1); // ici on devra passer UserID quand on aura fait authentification
                friend.setFirst_name(firstName.getText().toString());
                friend.setLast_name(lastName.getText().toString());


                new FirebaseDatabaseHelper().addFriend(friend, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {
                        String name = "a new friend name:" + friend.getFirst_name().toLowerCase() + " is create and add to firebase";
                        Toast.makeText(
                                FriendActivity.this, name, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

    }
}
