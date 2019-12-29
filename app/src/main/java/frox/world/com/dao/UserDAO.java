package frox.world.com.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import frox.world.com.model.User;

@Dao //defines the method that access the database, using annotation to bind SQL to each method.
// DAO data acces objects
// 4  @Query, @Insert, @Update, @Delete
public interface UserDAO {
    @Query("SELECT * FROM user_table ")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM user_table WHERE user_id IN (:userIds)")
    LiveData<List<User>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user_table WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    @Insert
    void insertAll(User... users);

    @Insert
    void insertList(List<User> list);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(User... users);

}