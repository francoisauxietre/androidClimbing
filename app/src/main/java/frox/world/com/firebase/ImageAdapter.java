package frox.world.com.firebase;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<Climber> climberList;
    private OnItemClickListener itemClickListener;

    public ImageAdapter(Context context, List<Climber> climberList) {
        this.context = context;
        this.climberList = climberList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Climber climberCurrent = climberList.get(position);
        Log.i("DEBUGGAGE", "" + climberCurrent.getFile_name());
        holder.textViewName.setText("" + climberCurrent.getFile_name());
        //ou centerInside
        Picasso.get()
                .load(climberCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit().centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return climberList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.activity_image_item_text_view_name);
            imageView = itemView.findViewById(R.id.activity_image_view_upload);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) ;
                itemClickListener.onItemCLick(position);
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            //autre moyen quand on est pas dans un activity pour passer un champ
            String message = context.getString(R.string.selectAction);
            String message1 = context.getString(R.string.editAction);
            String message2 = context.getString(R.string.likeAction);
            String message3 = context.getString(R.string.deleteAction);
            menu.setHeaderTitle(message);
            MenuItem edit = menu.add(Menu.NONE, 1, 1, message1);
            MenuItem like = menu.add(Menu.NONE, 2, 2, message2);
            MenuItem delete = menu.add(Menu.NONE, 3, 3, message3);

            edit.setOnMenuItemClickListener(this);
            like.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (itemClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (item.getItemId()) {
                        case 1:
                            itemClickListener.onItemEdit(position);
                            return true;
                        case 2:
                            itemClickListener.onItemLike(position);
                            return true;
                        case 3:
                            itemClickListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }

    }

    //quand on restera cliker sur l'image on pourra la suprimer
    public interface OnItemClickListener {
        void onItemCLick(int position);

        void onItemEdit(int position);

        void onItemLike(int position);

        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener _itemClickListener) {

        itemClickListener = _itemClickListener;
    }
}