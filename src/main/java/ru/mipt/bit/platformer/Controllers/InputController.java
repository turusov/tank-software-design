package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Direction;

import java.util.HashMap;
import java.util.Map;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class InputController {
    private final Map<Integer, Direction> keyToDirectionMap = new HashMap<>();

//    public void addMapping(int key, Direction direction) {
//        keyToDirectionMap.put(key, direction);
//    }

    public Direction getDirection() {
        for (Integer key : keyToDirectionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToDirectionMap.get(key);
            }
        }
        return null;
    }
    public void init(){
        keyToDirectionMap.put(UP, Direction.UP);
        keyToDirectionMap.put(W, Direction.UP);
        keyToDirectionMap.put(LEFT, Direction.LEFT);
        keyToDirectionMap.put(A, Direction.LEFT);
        keyToDirectionMap.put(DOWN, Direction.DOWN);
        keyToDirectionMap.put(S, Direction.DOWN);
        keyToDirectionMap.put(RIGHT, Direction.RIGHT);
        keyToDirectionMap.put(D, Direction.RIGHT);
    }
}