package frox.world.com.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import frox.world.com.model.Card;
import frox.world.com.model.User;

@Dao
public interface CardDAO {
    @Query("SELECT * FROM card_table")
    LiveData<List<Card>> getAll();

    @Query("SELECT * FROM card_table WHERE id IN (:cardIds)")
    List<Card> loadAllByIds(int[] cardIds);

    @Query("SELECT * FROM card_table WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Card findByName(String first, String last);

    @Insert
    void insert(Card card);


    @Insert
    void insertAll(Card... cards);

    @Delete
    void delete(Card card);

}
