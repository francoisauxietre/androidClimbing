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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class FirebaseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebase_climber);
        recyclerView = findViewById(R.id.firebase_climber_recycler_view);

        new FirebaseDatabaseHelper().getClimbers(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {
                new RecyclerView_config().setConfig(recyclerView, FirebaseActivity.this, climberList, keysList);
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

        Toast.makeText(FirebaseActivity.this, "connect successful to firebase database", Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.climberlist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case
                 R.id.activity_climber_list_new_climber_menu:
                    startActivity(new Intent(this, NewClimberActivity.class));
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
