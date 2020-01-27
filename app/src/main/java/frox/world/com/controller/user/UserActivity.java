package frox.world.com.controller.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import frox.world.com.R;
import frox.world.com.controller.PhotoActivity;
import frox.world.com.firebase.FirebaseDatabaseHelper;
import frox.world.com.firebase.NewClimberActivity;
import frox.world.com.model.Climber;
import frox.world.com.model.User;

public class UserActivity extends AppCompatActivity {

    private ImageButton user;
    private Button submit;
    private EditText firstName;
    private EditText lastName;
    private EditText birth;
    private EditText email;
    private EditText adress;
    private String firstNameText;
    private String lastNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //on recupere les infos passées par putextra dans l'activity qui appel cet actitivty
        Bundle extras = getIntent().getExtras();

        // cas on on à rien passé si on est venu du menu home
        if (extras != null) {
            firstNameText = extras.getString("firstName");
            lastNameText = extras.getString("lastName");
            Log.i("USERACTIVITY_onCreate", firstNameText + ' ' + lastNameText);
            // and get whatever type user account id is
        } else {
            Log.i("USERACTIVITY_onCreate", "pas de texte");
        }


        firstName = findViewById(R.id.activity_user_edit_text_firstname);
        firstName.setText(firstNameText);
        lastName = findViewById(R.id.activity_user_edit_text_lastname);
        lastName.setText(lastNameText);
        birth = findViewById(R.id.activity_user_date);
        user = findViewById(R.id.activity_user_button_picture);
        submit = findViewById(R.id.activity_user_button_submit);
        email = findViewById(R.id.activity_user_email);
        adress = findViewById(R.id.activity_user_adress);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userPhotoIntent = new Intent(UserActivity.this, PhotoActivity.class);
                startActivity(userPhotoIntent);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sauvegarde dans firebase
                Toast.makeText(UserActivity.this, "Save in firebase", Toast.LENGTH_SHORT).show();

                User user = new User();
                user.setFirst_name(firstName.getText().toString());
                user.setLast_name(lastName.getText().toString());
                user.setBirth(birth.getText().toString());
                user.setEmail(email.getText().toString());
                user.setAddress(adress.getText().toString());

                new FirebaseDatabaseHelper().addUser(user, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {
                        String name = "a new climber name:" + user.getFirst_name().toLowerCase() + " is create and add to firebase";
                        Toast.makeText(
                                UserActivity.this, name, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


    }
}
