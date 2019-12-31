package frox.world.com.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@Entity
public class Climbingroute {

    @NotNull
    @PrimaryKey
    long id;

    @ColumnInfo(name = "climbingroute_name")
    String climbingroute_name;

    @ColumnInfo(name = "difficulty")
    String difficulty;

    @ColumnInfo(name = "technical")
    int  technical;
    @ColumnInfo(name = "tactical")
    int tactical;
    @ColumnInfo(name = "mental")
    int mental;
    @ColumnInfo(name = "physical")
    int physical;

    public List<Climbingroute> getClimbingroute(int climberId){
        List<Climbingroute> climbingroutes = new ArrayList<>();
        return climbingroutes;
    }

}
