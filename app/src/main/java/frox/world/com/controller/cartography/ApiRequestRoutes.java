package frox.world.com.controller.cartography;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ApiRequestRoutes extends AsyncTask<URL,Integer,Long> {

    private String resp;
    private String url = "http://spring.auxietre.com/climbingroutes/";
    private String response;
    private ArrayList<JSONObject> routes;
    private CartographyActivity activity;

    ApiRequestRoutes(CartographyActivity activity){
        this.activity = activity;
    }

    public static void getRoutes(CartographyActivity activity){
        try {
            URL url = new URL("http://spring.auxietre.com/climbingroutes/");
            new ApiRequestRoutes(activity).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Long doInBackground(URL... params) {
        try {
            routes = new ArrayList<>();
            Thread.sleep(1);

            URL url1 = new URL(url);

            HttpURLConnection urlConnection = (HttpURLConnection) url1.openConnection();
            int statusCode = urlConnection.getResponseCode();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                JSONArray jsonArray = new JSONArray(br.readLine());
                for (int i = 0; i < jsonArray.length(); i++) {
                    routes.add(jsonArray.getJSONObject(i));
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
        return 1L;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        this.activity.addRouteMarkers(routes);
    }
}
