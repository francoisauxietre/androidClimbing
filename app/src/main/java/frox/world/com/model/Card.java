package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;


import lombok.Data;
@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId"))
@Data
public class Card implements Serializable {

    //constructeur vide
    public Card() { }

    //creation d'un identifiant unique pour chaque carte
    @NotNull
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    //TODO ajouter rank
    public String rank;

    @ColumnInfo(name = "bonus")
    private String bonus;

    @ColumnInfo(name = "info")
    private String info;

    @ColumnInfo(name = "photo")
    private String photo;

    @ColumnInfo(name = "date")
    private Date date;

    //TODO erreur ici dans le java aussi de zone difficuty
    @ColumnInfo(name = "difficulty")
    private String difficulty;

    @ColumnInfo(name = "physical")
    private Integer physical;

    @ColumnInfo(name = "technical")
    private Integer technical;

    @ColumnInfo(name = "tactical")
    private Integer tactical;

    @ColumnInfo(name = "mental")
    private Integer mental;

    @ColumnInfo(name= "qrcode" )
//    private Blob qrcode;
    private String qrcode;

    @ColumnInfo(name= "star")
    private  int star;

    @ColumnInfo(name = "climbing_route_name")
    private String climbingRouteName;


}