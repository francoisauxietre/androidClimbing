package frox.world.com.room.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import frox.world.com.R;
import frox.world.com.room.ViewModel;
import frox.world.com.room.AppDatabase;
import frox.world.com.model.User;
import frox.world.com.room.adapter.UserAdapter;

public class RecyclerUserActivity extends AppCompatActivity implements View.OnClickListener {

//    private Button button_new_user;
    private RecyclerAdapter userAdapter;
    private List<User> usersList;
    private static final String DATABASE_NAME = "database-climbing";
    private AppDatabase userDatabase;
    private ViewModel viewModel;

    private RecyclerView recyclerView;

    private AppDatabase appDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_user);
        recyclerView = findViewById(R.id.room_user_recycler_view);

        appDatabase = AppDatabase.getInstance(this);
        Log.w("RecyclerUserActivity", "database trouvée");

        usersList = new ArrayList<>();

        User fa = new User();
        fa.setFirst_name("fa");
        fa.setLast_name("ox");
        fa.setBirth("18/09/1972");
        fa.setDepartment("35");
        fa.setAddress("rennes");
        this.usersList.add(fa);

        User fa1 = new User();
        fa.setFirst_name("fa1");
        fa.setLast_name("ox");
        fa.setBirth("18/09/1972");
        fa.setDepartment("35");
        fa.setAddress("rennes");
        this.usersList.add(fa1);

        this.usersList.add(fa);
        this.usersList.add(fa1);

        appDatabase.addUser(appDatabase, fa);


//        userDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
//                .fallbackToDestructiveMigration()
//                .build();

//        button_new_user = findViewById(R.id.activity_recycler_user_button_new_user);
//        button_new_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(" RecyclerUserActivity onClick", "bouton ajout");
//            }
//        });

        // handle the recycler view


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setHasFixedSize(true);


        //ajoute de l'adapter
        userAdapter = new RecyclerAdapter(this.usersList);

        viewModel =  ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllUsers().observe(this, users -> userAdapter.setUsersList(users));

        Log.i("RECYCLER_USER_ACTIVITY", ""+usersList.size());
        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                userAdapter.setUsersList(users);
            }
        });

;

    }

    @Override
    public void onClick(View v) {
        Log.i("USER_ACTIVITY_onClick", "click in RecyclerActivity");
//        Intent recycler_intent = new Intent(getApplicationContext(), UserActivity.class);
//        startActivityForResult(recycler_intent, 0);
    }

    //methode apelle lors du retour du bouton valider de UserActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Log.i("USER_ACTIVITY_onActivityResult", "retour de User Activity");
        Bundle extras = intent.getExtras();

        User user = intent.getParcelableExtra("user");
        //cree instance viewmodel

        viewModel.insert(user);
//        usersList.add(user);
//        recyclerAdapter.notifyDataSetChanged();
    }
}