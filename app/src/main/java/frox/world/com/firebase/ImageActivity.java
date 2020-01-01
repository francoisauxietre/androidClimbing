package frox.world.com.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class ImageActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private DatabaseReference databaseReference;
    private List<Climber> climberList;
    private String path = "imagesUpload";
    private ProgressBar progressBar;


    private TextView imaageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        recyclerView = findViewById(R.id.activity_image_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressBar = findViewById(R.id.activity_image_progress_bar);

        climberList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference(path);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotPosted : dataSnapshot.getChildren()){
                    Climber climber = dataSnapshotPosted.getValue(Climber.class);
                    climberList.add(climber);
                }
                //adpater est un convertisseur entre un objet et une activity
                //ici entre la imageView et la liste des climbers
                imageAdapter = new ImageAdapter(ImageActivity.this, climberList);
                recyclerView.setAdapter(imageAdapter);
                imageAdapter.setOnItemClickListener(ImageActivity.this);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImageActivity.this,databaseError.getMessage(), Toast.LENGTH_LONG ).show();
                Log.w("IMAGE_ACTIVITY", getString(R.string.errorDownloadingImage));
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }


    @Override
    public void onItemCLick(int position) {

        Toast.makeText(this, getString(R.string.activity_image_clickAction) + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemEdit(int position) {
        Toast.makeText(this, getString(R.string.activity_image_editAction) + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLike(int position) {
        Toast.makeText(this, getString(R.string.activity_image_likeAction)+ position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Toast.makeText(this, getString(R.string.activity_image_deleteAction) + position, Toast.LENGTH_SHORT).show();
    }
}
