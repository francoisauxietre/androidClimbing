package frox.world.com.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

import frox.world.com.model.User;

@Dao //F auxietre :defini les methodes pour acceder aux element de la base
// don utilise les annotations pour (bind) lier le SQL a chaque  methode.
// DAO data acces objects
// 4  @Query, @Insert, @Update, @Delete
public interface UserDAO {


    //tres bizarre il ecrase en cas de conflit l'utilisateur
    // TODO VERIFIER il faut mettre autogenerate sur user a mon avis
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createUser(User user);
    @Query("SELECT * FROM user_table WHERE id = :userId")
    LiveData<User> getUser(long userId);

    @Query("SELECT * FROM user_table ")
    LiveData<List<User>> getAll();

    //:userIds  = tous les Id de la table user
    @Query("SELECT * FROM user_table WHERE id IN (:userIds)")
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

    @Update
    int updateUser(User user);

    @Query("DELETE FROM user_table WHERE id = :userId")
    int deleteUser(long userId);

    @Delete
    void delete(User user);

    @Delete
    void deleteAll(User... users);

}