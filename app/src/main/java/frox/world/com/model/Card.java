package frox.world.com.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

//on pourrait aussi faire avec parcelable mais il semble avoir des methodes a implementer en plus
// cf https://www.techjini.com/blog/passing-objects-via-intent-in-android/
@Data
public class Card implements Serializable {
    private String info;
    private String bonus;
    private float latitude;
    private float longitude;
    private int difficulty;
    private String nom;
    long id;

    public Card() {
    }

}
