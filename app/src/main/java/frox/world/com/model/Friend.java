package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

import lombok.Data;

// Entity une table chaque colonne est un attribut chaque ligne une instance de l'ojet user
// constructed using an annotated java data object. Each entity is persisted into its own table.

@Data
@Entity(tableName = "friend_table")
public class Friend  implements Serializable {

    //constructeur vide
    public Friend() { }

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "first_name")
    private String first_name;

    @ColumnInfo(name = "last_name")
    private String last_name;

    @ColumnInfo(name = "id_user")
    private Long id_user;
}

