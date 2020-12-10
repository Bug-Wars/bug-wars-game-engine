package codecamp.bug.wars.game.logic.service.engine;

import codecamp.bug.wars.game.logic.models.Direction;
import codecamp.bug.wars.game.logic.service.actions.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BugExecutor {
    int x;
    int y;
    Direction direction;
    boolean dying;
    int team;
    List<Integer> program;
    int programCounter;
    private static Map<Integer,Action> actionMap;

    static {
        actionMap = new HashMap<>();
        actionMap.put(0, new NoopAction());
        actionMap.put(2, new MoveAction());
        actionMap.put(3, new TurnLeftAction());
        actionMap.put(4, new TurnRightAction());
    }

    public Action getNextCommand() {
        int objCode = program.get(programCounter);
        Action action = actionMap.get(objCode);
        programCounter++;
        return action;
    }
}
