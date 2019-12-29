package frox.world.com.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import frox.world.com.dao.UserDAO;

import frox.world.com.model.User;
import frox.world.com.room.AppDatabase;

public class Repository {
    private UserDAO userDAO;
    private LiveData<List<User>> allUsers;

    public Repository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        userDAO = database.userDAO();
        allUsers = userDAO.getAll();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDAO).execute(user);
    }


    public void delete(User user) {
        new DeleteUserAsyncTask(userDAO).execute(user);
    }

    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(userDAO).execute();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.insert(users[0]);
            return null;
        }
    }


    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDAO;

        private DeleteUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDAO.delete(users[0]);
            return null;
        }
    }

    private static class DeleteAllUsersAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDAO;

        private DeleteAllUsersAsyncTask(UserDAO noteDao) {
            this.userDAO = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDAO.deleteAll();
            return null;
        }
    }
}