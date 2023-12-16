package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Direction;

import java.util.HashMap;
import java.util.Map;

public class InputController {
    private final Map<Integer, Direction> keyToDirectionMap = new HashMap<>();

    public void addMapping(int key, Direction direction) {
        keyToDirectionMap.put(key, direction);
    }

    public Direction getDirection() {
        for (Integer key : keyToDirectionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToDirectionMap.get(key);
            }
        }
        return null;
    }
}