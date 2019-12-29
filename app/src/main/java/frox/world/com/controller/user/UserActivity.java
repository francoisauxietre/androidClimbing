package frox.world.com.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;

import frox.world.com.R;
import frox.world.com.controller.PhotoActivity;

public class UserActivity extends AppCompatActivity {

    private ImageButton user;
    private EditText firstName;
    private EditText lastName;
    private EditText birth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        firstName =findViewById(R.id.activity_user_edit_text_firstname);
        lastName =findViewById(R.id.activity_user_edit_text_lastname);
        birth = findViewById(R.id.activity_user_date);

        user = findViewById(R.id.activity_user_button_picture);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userPhotoIntent = new Intent(UserActivity.this, PhotoActivity.class);
                startActivity(userPhotoIntent);

            }
        });



    }
}
