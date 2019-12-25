package frox.world.com.model;

import java.util.List;

import lombok.Data;

@Data
public class Climber {
    String nom;
    long id;
    private List<Card> cards;
    private List<Climber> climbersSucced;
}