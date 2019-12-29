package frox.world.com.oldfiles;

public class Old {
    //        card.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent cardAcitivityIntent =  new Intent(MainActivity.this, CardActivity.class);
//                startActivity(cardAcitivityIntent);
//                AsyncHTTP task = new AsyncHTTP(MainActivity.this);
//                //task.execute("http://spring.auxietre.com/climbers/");
//            }
//        });

//        url.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent urlActivity= new Intent(MainActivity.this, UrlActivity.class);
//                startActivity(urlActivity);
//            }
//        });


//   private class AsyncHTTP extends AsyncTask<String, Integer, String> {
//
//        private AppCompatActivity myActivity;
//
//        public AsyncHTTP(AppCompatActivity mainActivity) {
//            myActivity = mainActivity;
//        }
//
//        @Override
//        protected void onPreExecute() {
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            publishProgress(1);
//            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
//
//            URL url = null;
//            HttpURLConnection urlConnection = null;
//            String result = null;
//            try {
//                url = new URL(strings[0]);
//                urlConnection = (HttpURLConnection) url.openConnection(); // Open
//                InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream
//                publishProgress(2);
//                result = readStream(in); // Read stream
//            }
//            catch (MalformedURLException e) { e.printStackTrace(); }
//            catch (IOException e) { e.printStackTrace(); }
//            finally { if (urlConnection != null)
//                urlConnection.disconnect();  }
//
//            publishProgress(4);
//            return result; // returns the result
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
//            pb.setProgress(values[0]);
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            TextView text = (TextView) myActivity.findViewById(R.id.text);
//            text.setText(s); // Updates the textview
//            ProgressBar pb = (ProgressBar) myActivity.findViewById(R.id.progressBar);
//            pb.setProgress(5);
//        }
//
//        private String readStream(InputStream is) throws IOException {
//            StringBuilder sb = new StringBuilder();
//            BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
//            for (String line = r.readLine(); line != null; line =r.readLine()){
//                sb.append(line);
//            }
//            is.close();
//            return sb.toString();
//        }
}

//Appdatabase


//ancienne version de database

//import androidx.room.Database;
//import androidx.room.RoomDatabase;
//import androidx.room.TypeConverters;
//
//import frox.world.com.controller.MainActivity;
//import frox.world.com.dao.CardDAO;
//import frox.world.com.dao.UserDAO;
//import frox.world.com.model.Card;
//
//@Database(entities = {Card.class}, version = 1)
//@TypeConverters({MainActivity.Converters.class})
//public abstract class AppDatabase extends RoomDatabase {
//    public abstract CardDAO cardDAO();
//    public abstract UserDAO userDAO();
//}