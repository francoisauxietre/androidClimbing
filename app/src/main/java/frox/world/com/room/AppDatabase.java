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

import frox.world.com.dao.UserDAO;
import frox.world.com.model.User;


//Database is a holder class that uses annotation to define the list of
// entities and database version. This class content defines the list of DAOs.


@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase  extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDAO userDAO();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static User addUser(final AppDatabase db, User user) {
        db.userDAO().insertAll(user);
        Log.i("Database", "add "+user.getFirst_name());
        return user;
    }


    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;

        private PopulateDbAsyncTask(AppDatabase db) {
            userDAO = db.userDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {


            User fa = new User();
            fa.setBirth("12/12/1972");
            addUser(instance, fa);

//            userDao.insert(fa);
//            userDao.insert(fa1);
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

