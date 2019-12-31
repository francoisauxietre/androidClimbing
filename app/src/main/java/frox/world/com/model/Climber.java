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

    //constructeur vide necessaire
    public Climber() { }

    public Climber(String last_name, String first_name, String category, String date) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.category = category;
        this.date = date;
    }

    public Climber(String file_name, String imageUrl) {
//        suprime les espaces
        if (file_name.trim().toLowerCase().equals("")){
            this.file_name = "empty";
        }else{
            //met en emaj et suprime les espaces
            this.file_name = file_name.trim().toUpperCase();
        }

        this.file_name = file_name;
        this.imageUrl = imageUrl;
    }

    //creation d'un numero unique de grimpeur par room
    @NotNull
    @PrimaryKey(autoGenerate = true)
    long id;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "card_id")
    private String card_id;

    @ColumnInfo(name = "file_name")
    private String file_name;

    @ColumnInfo(name = "image_url")
    private String imageUrl;


//
//    User user;
//    private List<Card> cards;

}