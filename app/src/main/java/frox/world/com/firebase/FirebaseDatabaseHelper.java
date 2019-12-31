package frox.world.com.firebase;

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

public class FirebaseDatabaseHelper {
  private    FirebaseDatabase firebaseDatabase;
   private DatabaseReference databaseReference;
   private List<Climber> climberList = new ArrayList<>();

   public interface DataStatus{
       void DataIsLoaded(List<Climber> climberList, List<String> keysList);
       void DataIsInserted();
       void DataIsUpdated();
       void DataIsDeleted();
   }

    public FirebaseDatabaseHelper() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("climber");
    }

    public void getClimbers(final DataStatus dataStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                climberList.clear();
                List<String> keysList = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keysList.add( keyNode.getKey());
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
    public void addClimber(Climber  climber, final DataStatus dataStatus){

       String key = databaseReference.push().getKey();
       databaseReference.child(key).setValue(climber)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {
                       dataStatus.DataIsInserted();
                   }
               });

    }
    public void deleteClimber(String key, final DataStatus dataStatus){
        databaseReference.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });

    }
    public void updateClimber(String key, Climber climber, final DataStatus dataStatus){
        databaseReference.child(key).setValue(climber)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsUpdated();
                    }
                });
    }

}
