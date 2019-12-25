package frox.world.com.model;

import java.util.List;

import lombok.Data;

@Data
public class Card {
    private String info;
    private String bonus;
    private float latitude;
    private float longitude;
    private int difficulty;

    private String nom;
    long id;


}
