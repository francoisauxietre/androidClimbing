package frox.world.com.controller.recycler;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import frox.world.com.R;
import frox.world.com.model.User;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<User> usersList ;


    public RecyclerAdapter(List<User> usersList) {
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_user, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User currentUser = usersList.get(position);
        holder.first_name.setText(currentUser.getFirst_name());
        holder.last_name.setText(currentUser.getLast_name());
        holder.birth.setText(currentUser.getBirth());
        holder.town.setText(currentUser.getTown());
        holder.phone_number.setText(currentUser.getPhone_number());
        holder.department.setText(currentUser.getDepartment());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView first_name;
        TextView last_name;
        TextView birth;
        TextView town;
        TextView department;
        TextView phone_number;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.item_first_name);
            last_name = itemView.findViewById(R.id.item_last_name);
            birth = itemView.findViewById(R.id.item_birth);
            town =itemView.findViewById(R.id.item_town);
            phone_number = itemView.findViewById(R.id.item_phone_number);
            department = itemView.findViewById(R.id.item_department);

        }


    }
    public void setUsersList(List<User> usersList){
        this.usersList = usersList;
        notifyDataSetChanged();
    }

    public User getUserAt(int adapterPosition) {
        return usersList.get(adapterPosition);
    }
}