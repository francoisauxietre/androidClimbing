package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import lombok.Data;

@Data
@Entity(tableName = "climber_table",
        foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId"))
public class Climber {

    //constructeur vide
    public Climber() { }

    //creation d'un numero unique de grimpeur par room
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "first_name")
    private String first_name;


    User user;
    private List<Card> cards;

}