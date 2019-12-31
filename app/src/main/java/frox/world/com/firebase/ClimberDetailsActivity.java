package frox.world.com.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import frox.world.com.R;
import frox.world.com.model.Climber;

public class ClimberDetailsActivity extends AppCompatActivity {
    private EditText lastName;
    private EditText firstName;
    private EditText date;
    private String category;
    private Spinner spinner;

    private Button backButton;
    private Button deleteButton;
    private Button updateButton;

    private String intentKey;
    private String intentFirstName;
    private String intentLastname;
    private String intentDate;
    private String intentCategory;

    private ImageView imageView;



    private int getIndexSpinnerItem(Spinner spinner, String item){
        int index = 0;
        for (int i=0; i<spinner.getBaseline(); i++){
            if(spinner.getItemAtPosition(i).equals(item));
            index=i;
            break;
        }
        return index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climber_details);

        intentKey = getIntent().getStringExtra("key");
        intentFirstName = getIntent().getStringExtra("first_name");
        intentLastname = getIntent().getStringExtra("last_name");
        intentDate = getIntent().getStringExtra("date");
        intentCategory = getIntent().getStringExtra("category");



        lastName = findViewById(R.id.activity_climber_detail_last_name);
        lastName.setText(intentLastname);
        firstName = findViewById(R.id.activity_climber_detail_first_name);
        firstName.setText(intentFirstName);
        date = findViewById(R.id.activity_climber_detail_date);
        date.setText(intentDate);
        spinner = findViewById(R.id.activity_climber_detail_spinner);
        spinner.setSelection(getIndexSpinnerItem(spinner, category));


        deleteButton = findViewById(R.id.activity_climber_detail_button_delete);
        updateButton = findViewById(R.id.activity_climber_detail_button_Update);
        backButton = findViewById(R.id.activity_climber_detail_button_back);

        imageView = findViewById(R.id.activity_climber_detail_image_view);



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().deleteClimber(intentKey, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        String message = "delete of this climber";
                        Toast.makeText(ClimberDetailsActivity.this, message, Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                });

            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Climber climber = new Climber();
                climber.setFirst_name(firstName.getText().toString());
                climber.setLast_name(lastName.getText().toString());
                climber.setDate(date.getText().toString());
                climber.setCategory(spinner.getSelectedItem().toString());

                new FirebaseDatabaseHelper().updateClimber(intentKey, climber, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Climber> climberList, List<String> keysList) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        String message = "update of " + climber.getFirst_name();
                        Toast.makeText(ClimberDetailsActivity.this, message, Toast.LENGTH_LONG).show();
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
                finish(); return;
            }
        });
    }
}
