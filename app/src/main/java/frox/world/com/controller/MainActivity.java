package frox.world.com.controller;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import frox.world.com.R;
import frox.world.com.model.User;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;
    private Button home;
    private Button api;
    private Button climber;
    private Button card;
    private Button url;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = new User();
        //user.setFirstName("toto");
        textView = findViewById(R.id.activity_main_textview);
        editText = findViewById(R.id.activity_main_hello_input);
        button = findViewById(R.id.activity_main_button);
        home = findViewById(R.id.activity_main_button_home);
        api = findViewById(R.id.activity_main_button_api);
        climber = findViewById(R.id.activity_main_button_climber);
        card = findViewById(R.id.activity_main_button_Card);
        url = findViewById(R.id.activity_url_button);

        button.setEnabled(false);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                button.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;
                Toast.makeText(context, text, duration).show();
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(gameActivityIntent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeActivityIntent);
            }
        });

        api.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent apiActivityIntent = new Intent(MainActivity.this, ApiActivity.class);
                startActivity(apiActivityIntent);
        }});

        climber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent climberActivity= new Intent(MainActivity.this, ClimberActivity.class);
                startActivity(climberActivity);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent cardAcitivityIntent =  new Intent(MainActivity.this, CardActivity.class);
                startActivity(cardAcitivityIntent);
                AsyncHTTP task = new AsyncHTTP(MainActivity.this);
                //task.execute("http://spring.auxietre.com/climbers/");
            }
        });

//        url.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent urlActivity= new Intent(MainActivity.this, UrlActivity.class);
//                startActivity(urlActivity);
//            }
//        });

    }
   private class AsyncHTTP extends AsyncTask<String, Integer, String> {

        private AppCompatActivity myActivity;

        public AsyncHTTP(AppCompatActivity mainActivity) {
            myActivity = mainActivity;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(1);
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

            URL url = null;
            HttpURLConnection urlConnection = null;
            String result = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection(); // Open
                InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
                publishProgress(2);
                result = readStream(in); // Read stream
            }
            catch (MalformedURLException e) { e.printStackTrace(); }
            catch (IOException e) { e.printStackTrace(); }
            finally { if (urlConnection != null)
                urlConnection.disconnect();  }

            publishProgress(4);
            return result; // returns the result
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
            pb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            TextView text = (TextView) myActivity.findViewById(R.id.text);
            text.setText(s); // Updates the textview
            ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
            pb.setProgress(5);
        }

        private String readStream(InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
            for (String line = r.readLine(); line != null; line =r.readLine()){
                sb.append(line);
            }
            is.close();
            return sb.toString();
        }


    }
}