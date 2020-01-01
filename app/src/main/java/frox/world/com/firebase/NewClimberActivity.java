package frox.world.com.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class NewClimberActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private String path = "imagesUpload";

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

    //pour l'instant
    public Uri imageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    // pour tout mettre dans le meme grimpeur
    private String uploadImageId;
    private String fileName;


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

        // ajout pour tester la sauvegarde en base de donnee


        storageReference = FirebaseStorage.getInstance().getReference(path);
        databaseReference = FirebaseDatabase.getInstance().getReference(path);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Climber climber = new Climber();
                climber.setFirst_name(firstName.getText().toString());
                climber.setLast_name(lastName.getText().toString());
                climber.setDate(date.getText().toString());
                climber.setCategory(spinner.getSelectedItem().toString());
                climber.setImageUrl(uploadImageId);
                climber.setFile_name(filename.getText().toString());
                new FirebaseDatabaseHelper().addClimber(climber, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {
                        String name = "a new climber name:" + climber.getFirst_name().toLowerCase() + " is create and add to firebase";
                        Toast.makeText(
                                NewClimberActivity.this, name, Toast.LENGTH_LONG).show();
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
                uploadFile();
            }
        }));

        showUpload.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(NewClimberActivity.this, ImageActivity.class);
                startActivity(imageIntent);
            }
        }));


    }

    private void openFileChooser() {
        Intent fileIntent = new Intent();
        fileIntent.setType("image/*");
        fileIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(fileIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    private void uploadFile() {
        if (imageUri != null) {
            Log.i("NEW_CLIMBER_ACTIVITY", "imageUri est nulle");
           StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(NewClimberActivity.this,imageUri));
           fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                   Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           progressBar.setProgress(0);
                       }
                   }, 500);
                   Toast.makeText(NewClimberActivity.this, getString(R.string.success), Toast.LENGTH_SHORT).show();

                   //ici on devrait avoir getDownloadUrl
                   //Uri url = taskSnapshot.getDownloadUrl();
                   Task<Uri> uri = taskSnapshot.getStorage().getDownloadUrl();
                   while(!uri.isComplete());
                   Uri url = uri.getResult();

                   Toast.makeText(NewClimberActivity.this, R.string.success +
                           url.toString(), Toast.LENGTH_LONG).show();
                   Log.i("NEW_CLIMBER_ACTIVITY_onSucces ", url.toString());


                   Climber climber = new Climber(filename.getText().toString().trim(), url.toString());
                   //TODO VERIFIER QU'ON A BIEN UN UPLOADIMAGE ID dans climber
                   uploadImageId = databaseReference.push().getKey();
                   databaseReference.child(uploadImageId).setValue(climber);

               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                   Toast.makeText(NewClimberActivity.this, getString(R.string.errorUploadingImage), Toast.LENGTH_SHORT).show();
               }
           }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                   double progress = (100.0 *taskSnapshot.getBytesTransferred()/ taskSnapshot.getTotalByteCount());
                   progressBar.setProgress((int) progress);
               }
           });
        } else {
            Log.i("NEW_CLIMBER_ACTIVITY", "imageUri != null");
            Toast.makeText(this, getString(R.string.noFile), Toast.LENGTH_SHORT).show();
        }
    }

    public static String getFileExtension(Context context, Uri uri) {
        String extension;

        //Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
            //If scheme is a File
            //This will replace white spaces with %20 and also other special characters.
            // This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }
        Log.i("NEW_CLIMBER_ACTIVITY:file_extension", "" +extension);
        return extension;
    }
}
