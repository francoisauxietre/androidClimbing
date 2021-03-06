package frox.world.com.room;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

import frox.world.com.model.Card;
import frox.world.com.model.User;
import frox.world.com.repository.Repository;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Card>> allCards;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allUsers = repository.getAllUsers();
        allCards =  repository.getAllCards();
    }

    public void insert(User user) {
        repository.insert(user);
    }
    public void delete(User user) {
        repository.delete(user);
    }
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }


    public void insert(Card card) {
        repository.insert(card);
    }
    public void delete(Card card) {
        repository.delete(card);
    }
    public LiveData<List<Card>> getAllCards() {
        return allCards;
    }


}