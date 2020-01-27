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
import frox.world.com.model.Friend;
import frox.world.com.model.User;
import io.fabric.sdk.android.services.network.HttpRequest;

public class FirebaseDatabaseHelper {
    //private StorageReference storageReference;

    //database des grimpeurs
    private DatabaseReference databaseReference;

    //database des user
    private DatabaseReference databaseReferenceUser;

    //database des user
    private DatabaseReference databaseReferenceFriend;


    private Uri imageUri;

    private FirebaseDatabase firebaseDatabase;
    private List<Climber> climberList = new ArrayList<>();
    private List<User> userList = new ArrayList<>();
    private List<Friend> friendList = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Climber> climberList, List<String> keysList);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("climber");
        databaseReferenceUser = firebaseDatabase.getReference("user");
        databaseReferenceFriend = firebaseDatabase.getReference("friend");
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

    public void getUsers(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userList.clear();
                List<String> keysList = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keysList.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    userList.add(user);
                }
                dataStatus.DataIsLoaded(climberList, keysList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getFriends(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendList.clear();
                List<String> keysList = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keysList.add(keyNode.getKey());
                    Friend friend = keyNode.getValue(Friend.class);
                    friendList.add(friend);
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
    }


    public void addUser(User user, final DataStatus dataStatus) {
   String key = databaseReference.push().getKey();
        databaseReferenceUser.child(key).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void addFriend(Friend friend, final DataStatus dataStatus) {
        String key = databaseReference.push().getKey();
        databaseReferenceUser.child(key).setValue(friend)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }


//TODO DELETE FRIEND ET UPDATE


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
