package frox.world.com.controller.cartography;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Nominatim extends AsyncTask<URL,Integer,Long> {

    private CartographyActivity activty;
    private JSONObject jsonResult;

    public Nominatim(CartographyActivity activity) {
        this.activty = activity;
    }


    public static void getGeopositionFromAddress(String address, CartographyActivity activity){
        try {
            URL url = new URL("https://nominatim.openstreetmap.org/search?format=json&&country=France&&city=" + address);
            new Nominatim(activity).execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Long doInBackground(URL ... urls) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) urls[0].openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");

            int statusCode = urlConnection.getResponseCode();


        try{
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String result = "";
            String str;
            while( (str = br.readLine()) != null){
                result += str;
            }

            //Log.e("coucou", br.readLine());
            JSONArray jsonArray = new JSONArray(result);
            Log.e("toto", String.valueOf(jsonArray.length()));
            this.jsonResult = new JSONObject(jsonArray.get(0).toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }} catch (IOException e) {
            e.printStackTrace();
        }
        return 1L;
    }

    @Override
    protected void onPostExecute(Long aLong) {
        this.activty.geolocalization(this.jsonResult);
    }
}
