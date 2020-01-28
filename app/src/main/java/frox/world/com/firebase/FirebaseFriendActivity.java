package frox.world.com.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Friend;

public class FirebaseFriendActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_friend);
        recyclerView = findViewById(R.id.firebase_friend_recycler_view);


        new FirebaseDatabaseFriendHelper().getFriends(new FirebaseDatabaseFriendHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Friend> friendList, List<String> keysList) {
                new RecyclerViewFriend_config().setConfig(recyclerView, FirebaseFriendActivity.this, friendList, keysList);

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

        Toast.makeText(FirebaseFriendActivity.this, "connect successful to firebase friend database", Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.climberlist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_friend_list_new_friend_menu:
                startActivity(new Intent(this, NewFriendActivity.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}