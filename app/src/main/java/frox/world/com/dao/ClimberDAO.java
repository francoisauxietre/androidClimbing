package frox.world.com.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import frox.world.com.model.Climber;
import frox.world.com.model.User;

@Dao //defini les methodes pour acceder aux element de la base
// don utilise les annotations pour (bind) lier le SQL a chaque  methode.
// DAO data acces objects
// 4  @Query, @Insert, @Update, @Delete
public interface ClimberDAO {

    @Query("SELECT * FROM user_table ")
    LiveData<List<Climber>> getAll();

    //:userIds  = tous les Id de la table user
    @Query("SELECT * FROM user_table WHERE id IN (:climberIds)")
    LiveData<List<Climber>> loadAllByIds(int[] climberIds);

    @Query("SELECT * FROM climber_table WHERE first_name LIKE :firstname AND " +
            "last_name LIKE :lastname LIMIT 1")
    Climber findByName(String firstname, String lastname);

    @Insert
    void insertAll(Climber... climbers);

    @Insert
    void insertList(List<Climber> list);

    @Insert
    void insert(Climber climber);

    @Delete
    void delete(Climber climber);

    @Delete
    void deleteAll(Climber... climbers);

}