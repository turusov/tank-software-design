package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Actions.Action;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class InputController {
    private final Map<Integer, Action> keyToActionMap = new HashMap<>();

    public Action getAction() {
        for (Integer key : keyToActionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToActionMap.get(key);
            }
        }
        return null;
    }
    public void init(){
        keyToActionMap.put(UP, Direction.UP);
        keyToActionMap.put(W, Direction.UP);
        keyToActionMap.put(LEFT, Direction.LEFT);
        keyToActionMap.put(A, Direction.LEFT);
        keyToActionMap.put(DOWN, Direction.DOWN);
        keyToActionMap.put(S, Direction.DOWN);
        keyToActionMap.put(RIGHT, Direction.RIGHT);
        keyToActionMap.put(D, Direction.RIGHT);
    }
}