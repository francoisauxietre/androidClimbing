package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import java.util.Date;
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

    public Climber(String last_name, String first_name, String date, int card_id) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.date = date;
        this.card_id = card_id;
    }

    //creation d'un numero unique de grimpeur par room
    @NotNull
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "first_name")
    private String first_name;

    private String date;

    private int card_id;

//
//    User user;
//    private List<Card> cards;

}