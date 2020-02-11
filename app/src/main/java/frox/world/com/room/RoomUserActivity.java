package frox.world.com.room;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.User;

public class RoomUserActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_user);
        recyclerView = findViewById(R.id.room_user_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final RoomUserAdapter adapter = new RoomUserAdapter();
        recyclerView.setAdapter(adapter);

        // on crée une instance de notre ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        viewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                adapter.setUsers(users);
            }
        });

//pour ajouter a doite l'effacement
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                viewModel.delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
//                Toast.makeText(MainActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
//            }
//        }).attachToRecyclerView(recyclerView);

        Toast.makeText(RoomUserActivity.this, "connect successful to room database", Toast.LENGTH_LONG).show();
    }

    public void addUser(View v) {
        Log.i("TAG","Adding a view...");
        User user1 = new User();
        user1.setFirst_name("fa");
        viewModel.insert(user1);
    }

}
