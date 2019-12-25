package frox.world.com.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Climbingroute {
    String name;
    long id;
    String level;
    int  technical;
    int tactical;
    int mental;
    int physical;

    public List<Climbingroute> getClimbingroute(int climberId){
        List<Climbingroute> climbingroutes = new ArrayList<>();


        return climbingroutes;
    }

}
