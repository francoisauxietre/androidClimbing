package frox.world.com.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
            public void DataIsInseted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        //myRef.setValue("Hello, World!");
        Toast.makeText(FirebaseActivity.this, "connect successful to firebase database", Toast.LENGTH_LONG).show();


    }
}
