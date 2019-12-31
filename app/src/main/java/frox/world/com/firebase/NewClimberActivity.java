package frox.world.com.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class NewClimberActivity extends AppCompatActivity {
    private EditText lastName;
    private EditText firstName;
    private EditText date;
    private String categorie;
    private Spinner spinner;
    private Button addButton;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_climber);
        lastName = findViewById(R.id.activity_new_climber_last_name);
        firstName = findViewById(R.id.activity_new_climber_first_name);
        date = findViewById(R.id.activity_new_climber_date);
        spinner = findViewById(R.id.activity_new_climber_spinner);
        addButton = findViewById(R.id.activity_new_climber_button_add);
        backButton = findViewById(R.id.activity_new_climber_button_back);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Climber climber = new Climber();
                climber.setFirst_name(firstName.getText().toString());
                climber.setLast_name(lastName.getText().toString());
                climber.setDate(date.getText().toString());
                climber.setCategory(spinner.getSelectedItem().toString());
                new FirebaseDatabaseHelper().addClimber(climber, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInseted() {
                        String name ="a new climber name:" + climber.getFirst_name().toLowerCase().toString()+" is create and add to firebase";
                        Toast.makeText(
                                NewClimberActivity.this,name,Toast.LENGTH_LONG).show();
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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });

    }
}
