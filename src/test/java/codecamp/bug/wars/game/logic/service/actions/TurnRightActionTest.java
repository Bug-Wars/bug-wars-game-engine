package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TurnRightActionTest {

    TurnRightAction turnRightAction;
    Map map;
    BugExecutor bug;

    @BeforeEach
    public void setup() {
        bug = new BugExecutor();
        bug.setStartingX(1);
        bug.setStartingY(1);
        bug.setEndingX(1);
        bug.setEndingY(1);

        turnRightAction = new TurnRightAction();

        List<MapSpaceRow> rows = Arrays.asList(
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                new MapSpaceRow(Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        List<Spawn> spawns = Arrays.asList(new Spawn(1, 0, 1, Direction.NORTH));
        List<Food> food = Arrays.asList(new Food(1, 1));
        map = new Map(null, rows, spawns, food);
    }

    @Test
    public void execute_shouldMakeTheBugFaceEast() {
        bug.setDirection(Direction.NORTH);
        turnRightAction.execute(bug, map);
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void execute_shouldMakeTheBugFaceSouth() {
        bug.setDirection(Direction.EAST);
        turnRightAction.execute(bug, map);
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void execute_shouldMakeTheBugFaceWest() {
        bug.setDirection(Direction.SOUTH);
        turnRightAction.execute(bug, map);
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void execute_shouldMakeTheBugFaceNorth() {
        bug.setDirection(Direction.WEST);
        turnRightAction.execute(bug, map);
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void execute_shouldNotChangeCoordinates(){
        bug.setDirection(Direction.EAST);
        turnRightAction.execute(bug, map);
        assertEquals(1, bug.getEndingX());
        assertEquals(1, bug.getEndingY());
    }
}