package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.*;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MoveActionTest {
    MoveAction moveAction;
    Map map;
    BugResponse bug;
    GameState gameState;

    @BeforeEach
    public void runBeforeEveryTest() {
        List<List<MapSpace>> rows = Arrays.asList(
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.WALL, MapSpace.OPEN)),
                (Arrays.asList(MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN, MapSpace.OPEN))
        );

        map = Map.builder().mapGrid(rows).build();
        bug = new BugResponse();
        moveAction = new MoveAction();
        gameState = new GameState(1,map, Arrays.asList(bug), null);
    }

    @Test
    public void movesNorth_decrementsY() {
        // arrange
        bug.setStartingX(1);
        bug.setStartingY(2);
        bug.setDirection(Direction.NORTH);

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(1, bug.getEndingX());
        assertEquals(1, bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(1, bug.getEndingX());
        assertEquals(0, bug.getEndingY());
        assertEquals(Direction.NORTH, bug.getDirection());

    }

    @Test
    public void movesWest_decrementsX() {
        // arrange
        bug.setStartingX(2);
        bug.setStartingY(1);
        bug.setDirection(Direction.WEST);

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(1, bug.getEndingX());
        assertEquals(1, bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(0, bug.getEndingX());
        assertEquals(1, bug.getEndingY());
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void movesEast_incrementsX() {
        // arrange
        bug.setStartingX(1);
        bug.setStartingY(1);
        bug.setDirection(Direction.EAST);

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(2, bug.getEndingX());
        assertEquals(1, bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(3, bug.getEndingX());
        assertEquals(1, bug.getEndingY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void movesSouth_incrementsY() {
        // arrange
        bug.setStartingX(1);
        bug.setStartingY(1);
        bug.setDirection(Direction.SOUTH);

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(1, bug.getEndingX());
        assertEquals(2, bug.getEndingY());

        // arrange
        bug.setStartingX(bug.getEndingX());
        bug.setStartingY(bug.getEndingY());

        // act
        moveAction.execute(bug, gameState);

        // assert
        assertEquals(1, bug.getEndingX());
        assertEquals(3, bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_NORTH() {
        //arrange
        bug.setStartingX(3);
        bug.setStartingY(4);
        bug.setEndingX(3);
        bug.setEndingY(4);
        bug.setDirection(Direction.NORTH);

        //act
        moveAction.execute(bug, gameState);

        //assert
        assertEquals(3, bug.getEndingX());
        assertEquals(4, bug.getEndingY());
        assertEquals(Direction.NORTH, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_EAST() {
        //arrange
        bug.setStartingX(2);
        bug.setStartingY(3);
        bug.setEndingX(2);
        bug.setEndingY(3);
        bug.setDirection(Direction.EAST);

        //act
        moveAction.execute(bug, gameState);

        //assert
        assertEquals(2, bug.getEndingX());
        assertEquals(3, bug.getEndingY());
        assertEquals(Direction.EAST, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_SOUTH() {
        //arrange
        bug.setStartingX(3);
        bug.setStartingY(2);
        bug.setEndingX(3);
        bug.setEndingY(2);
        bug.setDirection(Direction.SOUTH);

        //act
        moveAction.execute(bug, gameState);

        //assert
        assertEquals(3, bug.getEndingX());
        assertEquals(2, bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }

    @Test
    public void cantMoveIntoWall_WEST() {
        //arrange
        bug.setStartingX(4);
        bug.setStartingY(3);
        bug.setEndingX(4);
        bug.setEndingY(3);
        bug.setDirection(Direction.WEST);

        //act
        moveAction.execute(bug, gameState);

        //assert
        assertEquals(4, bug.getEndingX());
        assertEquals(3, bug.getEndingY());
        assertEquals(Direction.WEST, bug.getDirection());
    }

    @Test
    public void cantMoveIntoOtherBug(){
        //arrange
        bug.setStartingX(1);
        bug.setStartingY(1);
        bug.setEndingX(1);
        bug.setEndingY(1);
        bug.setTeam(1);
        bug.setDirection(Direction.SOUTH);

        BugResponse bug2 = new BugResponse();
        bug2.setTeam(2);
        bug2.setStartingX(1);
        bug2.setStartingY(2);
        bug2.setEndingX(1);
        bug2.setEndingY(2);

        gameState.setBugs(Arrays.asList(bug, bug2));

        //act
        moveAction.execute(bug, gameState);

        //assert
        assertNotEquals(bug.getTeam(), bug2.getTeam());
        assertEquals(1, bug.getEndingX());
        assertEquals(1, bug.getEndingY());
        assertEquals(Direction.SOUTH, bug.getDirection());
    }


}