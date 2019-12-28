package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

//on pourrait aussi faire avec parcelable mais il semble avoir des methodes a implementer en plus
// cf https://www.techjini.com/blog/passing-objects-via-intent-in-android/
//@Data
//public class Card implements Serializable {
//    private String info;
//    private String bonus;
//    private float latitude;
//    private float longitude;
//    private int difficulty;
//    private String nom;
//    long id;
//
//    public Card() {
//    }
//
//}
@Entity
@Data
public class Card implements Serializable {

    @PrimaryKey
    public int uid;

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