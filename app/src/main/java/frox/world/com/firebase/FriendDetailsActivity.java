package frox.world.com.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;
import frox.world.com.model.Friend;

public class FriendDetailsActivity extends AppCompatActivity {

    private EditText lastName;
    private EditText firstName;
    private EditText id_user;

    private String intentKey;
    private String intentFirstName;
    private String intentLastName;
    private String intentIdUser;

    private Button backButton;
    private Button deleteButton;
    private Button updateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_details);

        intentKey = getIntent().getStringExtra("key");
        intentFirstName = getIntent().getStringExtra("first_name");
        intentLastName = getIntent().getStringExtra("last_name");
        intentIdUser = getIntent().getStringExtra("id_user");


        lastName = findViewById(R.id.activity_friend_detail_last_name);
        lastName.setText(intentLastName);
        firstName = findViewById(R.id.activity_friend_detail_first_name);
        firstName.setText(intentFirstName);
        id_user = findViewById(R.id.activity_friend_detail_user_id);
        id_user.setText(intentIdUser);


        deleteButton = findViewById(R.id.activity_friend_detail_button_delete);
        updateButton = findViewById(R.id.activity_friend_detail_button_Update);
        backButton = findViewById(R.id.activity_friend_detail_button_back);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseFriendHelper().deleteFriend(intentKey, new FirebaseDatabaseFriendHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Friend> friendList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {

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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friend friend = new Friend();
                friend.setFirst_name(firstName.getText().toString());
                friend.setLast_name(lastName.getText().toString());
                long num = Long.parseLong(id_user.getText().toString());
                friend.setId_user(num);


                new FirebaseDatabaseFriendHelper().updateFriend(intentKey, friend, new FirebaseDatabaseFriendHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Friend> friendList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        String message = "update of " + friend.getFirst_name();
                        Toast.makeText(FriendDetailsActivity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });


                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        return;
                    }
                });
            }
        });


    }
}
