package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.Entities.GameEntity;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.RIGHT;

public class InputController implements Controller {

    private final GameEntity gameEntity;
    private final Map<Integer, Action> keyToActionMap = new HashMap<>();

    public InputController(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }
    @Override
    public void execute() {
        Action action = getAction();
        if (action != null) action.apply(gameEntity);
    }

    public Action getAction() {
        for (Integer key : keyToActionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToActionMap.get(key);
            }
        }
        return null;
    }
//    public void init(CollisionHandler collisionHandler) {
//        addMapping(UP, new MoveAction(Direction.UP, collisionHandler));
//        addMapping(W, new MoveAction(Direction.UP, collisionHandler));
//        addMapping(LEFT, new MoveAction(Direction.LEFT, collisionHandler));
//        addMapping(A, new MoveAction(Direction.LEFT, collisionHandler));
//        addMapping(DOWN, new MoveAction(Direction.DOWN, collisionHandler));
//        addMapping(S, new MoveAction(Direction.DOWN, collisionHandler));
//        addMapping(RIGHT, new MoveAction(Direction.RIGHT, collisionHandler));
//        addMapping(D, new MoveAction(Direction.RIGHT, collisionHandler));
//    }

    public void addMapping(int key, Action action) {
        keyToActionMap.put(key, action);
    }
}