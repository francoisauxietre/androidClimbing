package frox.world.com.room.adapter;

import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import frox.world.com.R;
import frox.world.com.model.User;

public class UserViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

    //lient avec buuterknife pour le bind sans mettre
    @BindView(R.id.activity_user_view_holder_text_view_first_name)
    TextView textView;
    @BindView(R.id.activity_user_view_holder_image_view)
    ImageView imageView;
    @BindView(R.id.activity_user_view_holder_image_button)
    ImageButton imageButton;

    // FOR DATA
    private WeakReference<UserAdapter.Listener> callbackWeakRef;

    public UserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithUser(User user, UserAdapter.Listener callback){
        this.callbackWeakRef = new WeakReference<UserAdapter.Listener>(callback);
        this.textView.setText(user.getFirst_name());
        this.imageButton.setOnClickListener(this);
       }

    @Override
    public void onClick(View view) {
        UserAdapter.Listener callback = callbackWeakRef.get();
        if (callback != null) callback.onClickDeleteButton(getAdapterPosition());
    }
}