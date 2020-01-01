package frox.world.com.firebase;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.data.model.Resource;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class RecyclerView_config {
    private Context context;
    private ClimberAdapter climberAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Climber> climberList, List<String> keys) {
        this.context = context;
        this.climberAdapter = new ClimberAdapter(climberList, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(this.climberAdapter);

    }

    class ClimberItemView extends RecyclerView.ViewHolder {

        private TextView first_name;
        private TextView last_name;
        private TextView date;
        private TextView category;

        private String key;

        public ClimberItemView(ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.climber_list_item,
                    parent, false));
            first_name = itemView.findViewById(R.id.climber_list_item_first_name);
            last_name = itemView.findViewById(R.id.climber_list_item_last_name);
            date = itemView.findViewById(R.id.climber_list_item_date);
            category = itemView.findViewById(R.id.climber_list_item_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent climberDetailIntent = new Intent(context, ClimberDetailsActivity.class);
                    climberDetailIntent.putExtra("key", key);
                    climberDetailIntent.putExtra("first_name", first_name.getText().toString());
                    climberDetailIntent.putExtra("last_name", last_name.getText().toString());
                    climberDetailIntent.putExtra("date", date.getText().toString());
                    climberDetailIntent.putExtra("category", category.getText().toString());
                    context.startActivity(climberDetailIntent);
                }
            });

        }

        public void bind(Climber climber, String key) {
            first_name.setText(climber.getFirst_name());
            last_name.setText(climber.getLast_name());
            date.setText("" + climber.getDate());
            category.setText("" + climber.getCategory());
            this.key = key;
        }
    }

    class ClimberAdapter extends RecyclerView.Adapter<ClimberItemView> {
        private List<Climber> climberList;
        private List<String> keys;

        public ClimberAdapter(List<Climber> climberList, List<String> keys) {
            this.climberList = climberList;
            this.keys = keys;
        }

        @NonNull
        @Override
        public ClimberItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ClimberItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ClimberItemView holder, int position) {
            holder.bind(climberList.get(position), keys.get(position));
            if((position % 2 == 0)){
                holder.itemView.setBackgroundColor(Color.parseColor("#808080"));
            }else{
                holder.itemView.setBackgroundColor(Color.parseColor("#A1BEB8"));
            }
        }

        @Override
        public int getItemCount() {
            return climberList.size();
        }
    }

}
