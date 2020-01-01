package frox.world.com.firebase;

import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.HttpResponse;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import frox.world.com.model.Climber;
import io.fabric.sdk.android.services.network.HttpRequest;

public class FirebaseDatabaseHelper {
    //private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private Uri imageUri;

    private FirebaseDatabase firebaseDatabase;
    private List<Climber> climberList = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Climber> climberList, List<String> keysList);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("climber");
    }

    public void getClimbers(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                climberList.clear();
                List<String> keysList = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keysList.add(keyNode.getKey());
                    Climber climber = keyNode.getValue(Climber.class);
                    climberList.add(climber);
                }
                dataStatus.DataIsLoaded(climberList, keysList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    public void addClimber(Climber climber, final DataStatus dataStatus) {



        String key = databaseReference.push().getKey();
        databaseReference.child(key).setValue(climber)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });

        //peut etre mise dehors



    }

    public void deleteClimber(String key, final DataStatus dataStatus) {
        databaseReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

    }

    public void updateClimber(String key, Climber climber, final DataStatus dataStatus) {
        databaseReference.child(key).setValue(climber)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }


    // essai encoi vers bbd en post
//
//    public HttpResponse<String> post(String url, Path file, String... headers) throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .headers(headers)
//                .POST(null == file ? HttpRequest.BodyPublishers.noBody() :
//                        HttpRequest.BodyPublishers.ofFile(file))
//                .build();
//
//        log.i("FIREBASE_DATABASE_HELPER_post:『{}』, Url:『{}』", request.method(),
//                request.uri().toString());
//        return client.send(request, HttpResponse.BodyHandlers.ofString());
//    }
}
