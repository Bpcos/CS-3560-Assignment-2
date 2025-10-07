package Controller;

import java.util.*;

import Model.GameModel;
import javafx.scene.input.KeyCode;

public class GameController {
    private enum Actions{
        MOVE_LEFT,
        MOVE_RIGHT,
        UP_DIR,
        DOWN_DIR,
        JUMP,
        ATTACK
    }
    private final GameModel model;
    private final Set<KeyCode> activeKeys = new HashSet<>();
    private final Map<Actions, List<KeyCode>> inputMap;
    public GameController(GameModel model){
        this.model = model;
        this.inputMap = new EnumMap<>(Actions.class);
    }

    private void initializeKeybinds(){
        inputMap.put(Actions.ATTACK, List.of(KeyCode.X, KeyCode.ENTER));
        inputMap.put(Actions.JUMP, List.of(KeyCode.Z, KeyCode.SPACE));
        inputMap.put(Actions.MOVE_LEFT, List.of(KeyCode.A, KeyCode.LEFT));
        inputMap.put(Actions.MOVE_RIGHT, List.of(KeyCode.D, KeyCode.RIGHT));
        inputMap.put(Actions.UP_DIR, List.of(KeyCode.W, KeyCode.UP));
        inputMap.put(Actions.DOWN_DIR, List.of(KeyCode.D, KeyCode.DOWN));
    }

    private boolean actionPoll(Actions action){
        List<KeyCode> keys = inputMap.get(action);
        if (keys == null){
            return false;
        }
        for(KeyCode key : keys){
            if(keys.contains(key)){
              return true;
            }
        }
        return false;
    }

    public void handleKey(KeyCode code){
        activeKeys.add(code);
    }
    public void handleRelease(KeyCode code){
        activeKeys.remove(code);
    }

    public InputState getCurrentInputState(){
        boolean left = actionPoll(Actions.MOVE_LEFT);
        boolean right = actionPoll(Actions.MOVE_RIGHT);
        boolean up = actionPoll(Actions.UP_DIR);
        boolean down = actionPoll(Actions.DOWN_DIR);
        boolean jump = actionPoll(Actions.JUMP);
        boolean attack = actionPoll(Actions.ATTACK);
        return new InputState(left,
                right,
                up,
                down,
                jump,
                attack);
    }
}
