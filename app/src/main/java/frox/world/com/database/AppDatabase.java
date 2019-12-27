package frox.world.com.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import frox.world.com.controller.MainActivity;
import frox.world.com.dao.CardDAO;
import frox.world.com.model.Card;

@Database(entities = {Card.class}, version = 1)
@TypeConverters({MainActivity.Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract CardDAO cardDao();
}
