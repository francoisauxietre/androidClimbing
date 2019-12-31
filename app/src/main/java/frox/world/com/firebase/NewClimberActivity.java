package frox.world.com.firebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class NewClimberActivity extends AppCompatActivity {
    private static  final int PICK_IMAGE_REQUEST = 1;

    private EditText lastName;
    private EditText firstName;
    private EditText date;
    private String categorie;
    private Spinner spinner;
    private Button addButton;
    private Button backButton;

    private Button addPhoto;
    private Button upload;
    private EditText filename;

    private TextView showUpload;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri imageUri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_climber);
        lastName = findViewById(R.id.activity_new_climber_last_name);
        firstName = findViewById(R.id.activity_new_climber_first_name);
        date = findViewById(R.id.activity_new_climber_date);
        spinner = findViewById(R.id.activity_new_climber_spinner);
        addButton = findViewById(R.id.activity_new_climber_button_add);
        backButton = findViewById(R.id.activity_new_climber_button_back);

        filename = findViewById(R.id.activity_new_climber_edit_text_enter_file_name);
        addPhoto = findViewById(R.id.activity_new_climber_button_add_photo);
        upload = findViewById(R.id.activity_new_climber_button_upload);
        showUpload = findViewById(R.id.activity_new_climber_show_upload);
        progressBar = findViewById(R.id.activity_new_climber_progress_bar);
        imageView = findViewById(R.id.activity_new_climber_image_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Climber climber = new Climber();
                climber.setFirst_name(firstName.getText().toString());
                climber.setLast_name(lastName.getText().toString());
                climber.setDate(date.getText().toString());
                climber.setCategory(spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelper().addClimber(climber, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {
                        String name ="a new climber name:" + climber.getFirst_name().toLowerCase().toString()+" is create and add to firebase";
                        Toast.makeText(
                                NewClimberActivity.this,name,Toast.LENGTH_LONG).show();
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

        addPhoto.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        }));

        upload.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));

        showUpload.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }));


    }
    private void openFileChooser(){
        Intent fileIntent =  new Intent();
        fileIntent.setType("image/*");
        fileIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(fileIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!= null && data.getData() != null){
            imageUri = data.getData();

                Log.i("-----------------------ICI---------------", "test");
            imageView.setImageURI(imageUri);
        }
    }
}
