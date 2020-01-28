package frox.world.com.firebase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;
import frox.world.com.model.Friend;

public class RecyclerViewFriend_config {
    private Context context;
    private FriendAdapter friendAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Friend> friendList, List<String> keys) {
        this.context = context;
        this.friendAdapter = new FriendAdapter(friendList, keys);

        recyclerView.setAdapter(this.friendAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));

    }

    class FriendItemView extends RecyclerView.ViewHolder {

        private TextView first_name;
        private TextView last_name;
        private TextView id;
        private String key;

        public FriendItemView(ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.friend_list_item,
                    parent, false));
            first_name = itemView.findViewById(R.id.friend_list_item_first_name);
            last_name = itemView.findViewById(R.id.friend_list_item_last_name);
            id = itemView.findViewById(R.id.friend_list_item_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent friendDetailIntent = new Intent(context, FriendDetailsActivity.class);
                    friendDetailIntent.putExtra("key", key);
                    friendDetailIntent.putExtra("first_name", first_name.getText().toString());
                    friendDetailIntent.putExtra("last_name", last_name.getText().toString());
                    friendDetailIntent.putExtra("id", id.getText().toString());
                    context.startActivity(friendDetailIntent);
                }
            });

        }

        public void bind(Friend friend, String key) {
            first_name.setText(friend.getFirst_name());
            last_name.setText(friend.getLast_name());
            id.setText(""+friend.getId());
            this.key = key;
        }
    }

    class FriendAdapter extends RecyclerView.Adapter<FriendItemView> {
        private List<Friend> friendList;
        private List<String> keys;

        public FriendAdapter(List<Friend> friendList, List<String> keys) {
            this.friendList = friendList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public FriendItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FriendItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull FriendItemView holder, int position) {
            holder.bind(friendList.get(position), keys.get(position));
            if((position % 2 == 0)){
                holder.itemView.setBackgroundColor(Color.parseColor("#808080"));
            }else{
                holder.itemView.setBackgroundColor(Color.parseColor("#A1BEB8"));
            }
        }

        @Override
        public int getItemCount() {
            return friendList.size();
        }
    }

}
