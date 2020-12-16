package codecamp.bug.wars.game.logic.models;

import codecamp.bug.wars.game.logic.exceptions.InvalidInputException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Map {
    List<MapSpaceRow> mapGrid;
    List<Spawn> spawns;
    List<Food> foods;

    public MapSpace getSpace(int x, int y){
        if (y < 0 || x < 0 || y >= mapGrid.size()) {
            return MapSpace.WALL;
        }

       MapSpaceRow row = mapGrid.get(y);

        if (x >= row.getSpaces().size()){
            return MapSpace.WALL;
        }

        return row.getSpaces().get(x);
    }

    public int getSpawnIndex(int team) {
        int counter = 0;
        for(Spawn s : spawns){
            if(s.getTeam() == team){
                return counter;
            }
            counter++;
        }
        throw new InvalidInputException("Team does not exist in spawns");
    }
}