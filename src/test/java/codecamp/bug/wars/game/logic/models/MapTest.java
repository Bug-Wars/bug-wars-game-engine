package codecamp.bug.wars.game.logic.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    Map map;



    @BeforeEach
    public void setup(){
        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.WALL, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN, MapSpace.WALL)),
                new MapSpaceRow(Arrays.asList(MapSpace.WALL, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.WALL))
        );
        map = new Map(null, rows, null, null);
    };

    @Test
    public void getSpace_shouldReturnMapSpaceAtCoordinate(){
        assertEquals(MapSpace.OPEN, map.getSpace(1,1));
        assertEquals(MapSpace.WALL, map.getSpace(0,0));
    };

    @Test
    public void getSpaceOutOfBounds_shouldReturnAWall(){
        assertEquals(MapSpace.WALL, map.getSpace(0,-1));
        assertEquals(MapSpace.WALL, map.getSpace(-1,0));
        assertEquals(MapSpace.WALL, map.getSpace(5,0));
        assertEquals(MapSpace.WALL, map.getSpace(0,5));
    }



    }

