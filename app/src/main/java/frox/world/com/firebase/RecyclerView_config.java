package frox.world.com.firebase;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        private TextView card_id;

        private String key;

        public ClimberItemView(ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.climber_list_item,
                    parent, false));
            first_name = itemView.findViewById(R.id.climber_list_item_first_name);
            last_name = itemView.findViewById(R.id.climber_list_item_last_name);
            date = itemView.findViewById(R.id.climber_list_item_date);
            card_id = itemView.findViewById(R.id.climber_list_item_card_id);

        }

        public void bind(Climber climber, String key) {
            first_name.setText(climber.getFirst_name());
            last_name.setText(climber.getLast_name());
            date.setText("" + climber.getDate());
            card_id.setText("" + climber.getCard_id());
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
        }

        @Override
        public int getItemCount() {
            return climberList.size();
        }
    }

}
