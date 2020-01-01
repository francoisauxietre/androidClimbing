package frox.world.com.controller.climber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import frox.world.com.R;

public class ClimberActivity extends AppCompatActivity {

    private Button button1;
    private EditText time;
    private TextView finalResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climber);

        time = findViewById(R.id.in_time);
        button1 = findViewById(R.id.btn_run);
        finalResult = findViewById(R.id.tv_result);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClimberActivity.AsyncTaskRunner asyncTaskRunner = new ClimberActivity.AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                asyncTaskRunner.execute(sleepTime);
            }
        });
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        private String url = "http://spring.auxietre.com/climbers/";
        private String response;
        ProgressDialog progressDialog;
        private JSONObject jsonObject;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0]) * 1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";

                URL url1 = new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
                try {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    response = readStream(in);
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);
                        Log.i("CLIMBER_ACTIVITY", jsonObject.getString("text"));
                    }

                } finally {
                    urlConnection.disconnect();
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        private String readStream(InputStream is) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
            for (String line = r.readLine(); line != null; line = r.readLine()) {
                sb.append(line);
            }
            is.close();
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            finalResult.setText(result + response);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(ClimberActivity.this,
                    getString(R.string.progress),
                    getString(R.string.waitFor) + time.getText().toString() +getString(R.string.seconds));
        }


        @Override
        protected void onProgressUpdate(String... text) {
            finalResult.setText(text[0]);

        }
    }
}

