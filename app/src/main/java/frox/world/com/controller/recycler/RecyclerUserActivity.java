package frox.world.com.controller.recycler;

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
import frox.world.com.room.AppDatabase;
import frox.world.com.model.User;

public class RecyclerUserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_new_user;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<User> usersList;

    private static final String DATABASE_NAME = "client_db";
    private AppDatabase userDatabase;

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_user);

        usersList = new ArrayList<User>();
        recyclerAdapter = new RecyclerAdapter(usersList);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {

                recyclerAdapter.setUsersList(users);
            }
        });

        User fa = new User();
        fa.setFirst_name("fa");
        fa.setLast_name("ox");
        fa.setBirth("18/09/1972");
        fa.setDepartment("35");
        fa.setTown("rennes");
        this.usersList.add(fa);

        userDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

//        button_new_user = findViewById(R.id.button_new_user);
//        button_new_user.setOnClickListener(this);

        // handle the recycler view
        recyclerView = findViewById(R.id.activity_recycler_user_recycler_view);
        recyclerAdapter = new RecyclerAdapter(this.usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onClick(View v) {
        Log.i("info", "click in RecyclerActivity");
//        Intent recycler_intent = new Intent(getApplicationContext(), UserActivity.class);
//        startActivityForResult(recycler_intent, 0);
    }

    //methode apelle lors du retour du bouton valider de UserActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        Log.i("info", "retour de User Activity");
        Bundle extras = intent.getExtras();

        User user = intent.getParcelableExtra("user");
        //cree instance viewmodel
        //crer un add

        viewModel.insert(user);
//        usersList.add(user);
//        recyclerAdapter.notifyDataSetChanged();
    }
}