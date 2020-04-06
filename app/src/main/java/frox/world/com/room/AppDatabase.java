package frox.world.com.room;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import frox.world.com.dao.CardDAO;
import frox.world.com.dao.UserDAO;
import frox.world.com.model.Card;
import frox.world.com.model.User;

//F Auxietre : une Database permet de sauver en interne des données
// on utilise des entités et un repository

@Database(entities = {User.class, Card.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract UserDAO userDAO();
    public abstract CardDAO cardDAO();


    // creation de la base de donnee
    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database-climbing")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return appDatabase;
    }

    //execution non sur le thread principal
    /**
     * ajout en asynchrone pour ne pas bloquer le thread principal
     */
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(appDatabase).execute();
        }
    };

    /**
     * ajout d'un utilisateur
     *
     * @param database : la base de donnee
     * @param user     : l'utilisateur a jouter
     * @return l'utilisateur ajouter à la base de donnée
     */
    public static User addUser(final AppDatabase database, User user) {
        database.userDAO().insertAll(user);
        Log.i("APPDATABASE", "ajout d un nouvel utilisateur " + user.getFirst_name());
        return user;
    }

    /**
     * ajout en tache de fond
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;

        private PopulateDbAsyncTask(AppDatabase db) {
            userDAO = db.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            User fa0 = new User();
            fa0.setFirst_name("fafa");
            fa0.setLast_name("auxietre");
            addUser(appDatabase, fa0);
            userDAO.insert(fa0);
            Log.i("Appdatabase", "________________ajout de utilisateur "+fa0.getFirst_name() + "resuusi");

            User fa1 = new User();
            fa1.setFirst_name("leon");
            fa1.setLast_name("le cochon");
            addUser(appDatabase, fa1);
            userDAO.insert(fa1);
            Log.i("Appdatabase", "________________ajout de utilisateur "+fa1.getFirst_name() + "resuusi");
            return null;
        }
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

}

