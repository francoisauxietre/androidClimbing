package frox.world.com.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import frox.world.com.dao.CardDAO;
import frox.world.com.dao.UserDAO;

import frox.world.com.model.Card;
import frox.world.com.model.User;
import frox.world.com.room.AppDatabase;

//F Auxietre  la classe repository permet de lier la database et les entités.

public class Repository {
    //utilisateurs
    private UserDAO userDAO;
    private LiveData<List<User>> allUsers;

    //cartes
    private CardDAO cardDAO;
    private List<Card> cardList;
    private LiveData<List<Card>> allCards;

    public Repository(Application application) {
        // on recupere la database en cours
        AppDatabase database = AppDatabase.getInstance(application);
        // et un utilisateur
        userDAO = database.userDAO();
        // et la liste de tous les utilisateurs
        allUsers = userDAO.getAll();

        // de meme  pour les cartes
        cardDAO = database.cardDAO();
        allCards = cardDAO.getAll();
    }

    /**
     * ajout d'un utilisateur
     *
     * @param user: l'utilisateur a ajouter
     */
    public void insert(User user) {
        new InsertUserAsyncTask(userDAO).execute(user);
    }

    /**
     * supression d'un utilisateur
     *
     * @param user :l'utilisateur a suprimer de la database
     */
    public void delete(User user) {
        new DeleteUserAsyncTask(userDAO).execute(user);
    }

    /**
     * supression de tous les utilisateurs (Que pour un admin loggé)
     */
    public void deleteAllUsers() {
        new DeleteAllUsersAsyncTask(userDAO).execute();
    }

    /**
     * donne la liste de tous les utlilisateurs
     *
     * @return LiveData <List<Useer>> de tous les utilisateurs
     */
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    /**
     * ajoute une carte à la database
     *
     * @param card : la carte a joutée
     */
    public void insert(Card card) {
        new InsertCardAsyncTask(cardDAO).execute(card);
    }

    /**
     * supprime une carte dela database
     *
     * @param card: la carte a supprimer
     */
    public void delete(Card card) {
        new DeleteCardAsyncTask(cardDAO).execute(card);
    }

    /**
     * donne la liste de tous les cartes
     *
     * @return LiveData <List<Card>> de tous les cartes
     */
    public LiveData<List<Card>> getAllCards() {
        return allCards;
    }


    /**
     * ajout en asynchrone
     */
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

    /**
     * supprime en asynchrone
     */
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

    /**
     * supprime en asynchrone
     */

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

    /**
     * ajout en asynchrone
     */
    private static class InsertCardAsyncTask extends AsyncTask<Card, Void, Void> {
        private CardDAO cardDAO;

        private InsertCardAsyncTask(CardDAO cardDAO) {
            this.cardDAO = cardDAO;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDAO.insert(cards[0]);
            return null;
        }
    }


    /**
     * supprime en asynchrone
     */
    private static class DeleteCardAsyncTask extends AsyncTask<Card, Void, Void> {
        private CardDAO cardDAO;

        private DeleteCardAsyncTask(CardDAO cardDAO) {
            this.cardDAO = cardDAO;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDAO.delete(cards[0]);
            return null;
        }
    }


}