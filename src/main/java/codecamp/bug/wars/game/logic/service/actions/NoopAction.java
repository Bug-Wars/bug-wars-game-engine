package codecamp.bug.wars.game.logic.service.actions;

import codecamp.bug.wars.game.logic.models.Map;
import codecamp.bug.wars.game.logic.service.engine.BugExecutor;
import lombok.Data;

@Data
public class NoopAction implements Action {

    @Override
    public void execute(BugExecutor bugExecutor, Map map) {

    }
}
