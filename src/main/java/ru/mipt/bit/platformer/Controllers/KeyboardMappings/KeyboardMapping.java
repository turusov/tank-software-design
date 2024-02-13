package ru.mipt.bit.platformer.Controllers.KeyboardMappings;

import com.badlogic.gdx.Input;
import ru.mipt.bit.platformer.Controllers.Direction;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public enum KeyboardMapping {
    UP(Direction.UP, Input.Keys.UP, W),
    LEFT(Direction.LEFT, Input.Keys.LEFT, A),
    DOWN(Direction.DOWN, Input.Keys.DOWN, S),
    RIGHT(Direction.RIGHT, Input.Keys.RIGHT, D);
    private final Integer[] keys;
    Direction direction;
    KeyboardMapping(Direction direction, Integer... keys){
        this.direction = direction;
        this.keys = keys;
    }
}
