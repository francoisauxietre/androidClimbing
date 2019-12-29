package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import frox.world.com.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView profilImageView;
    private ImageButton buttonCamera;
    private int SELECT_FILES=0;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_photo);
            setUserInterface();

        } catch (Exception e) {
            Log.i("info", "error while taking photo in PhotoActivity");
            Toast.makeText(this, "error, please retry", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUserInterface() {
        this.profilImageView = findViewById(R.id.activity_photo_imageView_profil);
        this.buttonCamera = findViewById(R.id.activity_photo_imageButton_camera);
        this.buttonCamera.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.activity_photo_imageButton_camera:
                selectImage();
                break;
        }
    }

    private void selectImage() {

        final CharSequence[] items = {"Take photo", "Chose from library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("add a photo");
        Log.i("info", "----------------testt---------");
        builder.setItems(items, new DialogInterface.OnClickListener(){


            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result =  true;
                Log.i("TAG", "on click");
                if(items[item].equals("Take photo")){
                    take_picture();
                }

                if(items[item].equals("Chose from library")){
                    librayIntent();
                }

                if(items[item].equals("Cancel")){
                    dialog.dismiss();
                }
            }

        });
        builder.create();
        builder.show();

    }




    private void take_picture(){

//TODO hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY) si un appareil n'a pas de camera
        // a nous de le verifier dans notre code au momnet de prendre une photo

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

//        // Ensure that there's a camera activity to handle the intent
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            // Create the File where the photo should go
//            File photoFile = null;
//            try {
//                photoFile = createImageFile();
//                Log.i("photo", ""+photoFile);
//            } catch (IOException ex) {
//                // Error occurred while creating the File
//            }
//            // Continue only if the File was successfully created
//            if (photoFile != null) {
//                Uri photoURI = FileProvider.getUriForFile(this,
//                        "com.tp1.AndoidManifest",
//                        photoFile);
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
//            }
//        }
        Log.i("photo avant", "essai de sauver une photo");
        File photoFile = null;
        try {
            photoFile = createImageFile();
            Log.i("photo", ""+photoFile);
        } catch (IOException ex) {
            // Error occurred while creating the File
        }

//        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        camera_intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
//        startActivityForResult(camera_intent, REQUEST_CAMERA);

//        File file = new File(storage(), "MyFile.jpg");
//        Uri uri = Uri.fromFile(file);
//
//        Uri uriSavedImage=Uri.fromFile(new File("/sdcard/flashCropped.png"));
//        Intent cameraIntent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        startActivityForResult(cameraIntent, REQUEST_CAMERA);


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//         if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//        }
//    }

    private void librayIntent(){
        Log.i("TAG", "library");
        Intent library_Intent = new Intent();
        library_Intent.setType("images/*");
        library_Intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(library_Intent, "Select file"),SELECT_FILES );

    }

    String currentPhotoPath;

    private File createImageFile() throws IOException {
        // creation de l'image
        String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        String imageFileName = "tp1_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
