package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import frox.world.com.R;

public class UrlActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);

        textView = findViewById(R.id.activity_url_textview);
        button = findViewById(R.id.activity_url_button);

        queue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jsonParse();
            }
        });
    }

    private void jsonParse() {

        String url = "https://api.myjson.com/bins/kp9wz";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    //creation de l'object de recupération de l'url

                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");

                            for(int i =0; i<jsonArray.length(); i++){
//                                JSONObject climber = jsonArray.getJSONObject(i);
//                                String firstName = climber.getString("firstName");
////                                String language = climber.getString("language");
                                textView.append(" " +"\n\n");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}