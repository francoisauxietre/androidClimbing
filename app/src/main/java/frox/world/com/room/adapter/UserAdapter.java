package frox.world.com.room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import frox.world.com.R;
import frox.world.com.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    // CALLBACK
    public interface Listener { void onClickDeleteButton(int position); }
    private final Listener callback;

    // FOR DATA
    private List<User> users;

    // CONSTRUCTOR
    public UserAdapter() {
        this.users = new ArrayList<>();
        this.callback = null;
    }

    // CONSTRUCTOR
    public UserAdapter(Listener callback) {
        this.users = new ArrayList<>();
        this.callback = callback;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_user_adapter, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder viewHolder, int position) {
        viewHolder.updateWithUser(this.users.get(position), this.callback);
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public User getItem(int position){
        return this.users.get(position);
    }

    public void updateData(List<User> users){
        this.users = users;
        this.notifyDataSetChanged();
    }
}