package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import frox.world.com.R;


public class ApiActivity extends AppCompatActivity {

    private String proxy = "http://";
    private String urlClibingroutes = "spring.auxietre.com/climbingroutes/";
    private String urlClimbers = "spring.auxietre.com/climbers/";
    private String urlCards = "spring.auxietre.com/cards/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

//        try {
//            getUrl(proxy+urlClibingroutes);
//            getUrl(proxy+urlClimbers);
//            getUrl(proxy+urlCards);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }

    private void getUrl(String url) throws IOException {

        URL url1 = new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            readStream(in);
        } finally {
            urlConnection.disconnect();
        }
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
