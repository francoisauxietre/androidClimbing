package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Data;

@Data
@Entity(tableName = "user_table")
// Entity une table chaque colonne est un attribut chaque ligne une instance de l'ojet user
// constructed using an annotated java data object. Each entity is persisted into its own table.
public class User implements Serializable {

    //constructeur vide
    public User() { }

    @PrimaryKey
    private long id;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "birth")
    private String birth;

    @ColumnInfo(name = "phone_number")
    private String phone_number;

    @ColumnInfo(name = "town")
    private String town;

    @ColumnInfo(name = "department")
    private String department;

    @ColumnInfo(name = "rating_bar")
    private int ratingbar;

    @ColumnInfo(name = "image")
    private int image;
}