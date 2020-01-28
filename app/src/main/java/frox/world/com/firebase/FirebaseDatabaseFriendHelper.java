package frox.world.com.firebase;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import frox.world.com.model.Climber;
import frox.world.com.model.Friend;
import frox.world.com.model.User;

public class FirebaseDatabaseFriendHelper {
    //private StorageReference storageReference;

    //database des friend
    private DatabaseReference databaseReferenceFriend;


    private Uri imageUri;

    private FirebaseDatabase firebaseDatabase;
    private List<Friend> friendList = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoaded(List<Friend> friendList, List<String> keysList);

        void DataIsInserted();

        void DataIsUpdated();

        void DataIsDeleted();
    }

    public FirebaseDatabaseFriendHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReferenceFriend = firebaseDatabase.getReference("friend");
        Log.i("FirebaseDatabaseFriendHelper constructeur", ""+databaseReferenceFriend.equals(null));
    }



    public void getFriends(final DataStatus dataStatus) {
        databaseReferenceFriend.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                friendList.clear();
                List<String> keysList = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    keysList.add(keyNode.getKey());
                    Friend friend = keyNode.getValue(Friend.class);
                    friendList.add(friend);
                }
                dataStatus.DataIsLoaded(friendList, keysList);
                Log.i("FirebaseDatabaseFriendHelper getfriends", ""+friendList.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addFriend(Friend friend, final DataStatus dataStatus) {
        String key = databaseReferenceFriend.push().getKey();
        databaseReferenceFriend.child(key).setValue(friend)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }

    public void deleteFriend(String key, final DataStatus dataStatus) {
        databaseReferenceFriend.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

    }

    public void updateFriend(String key, Friend friend, final DataStatus dataStatus) {
        databaseReferenceFriend.child(key).setValue(friend)
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
