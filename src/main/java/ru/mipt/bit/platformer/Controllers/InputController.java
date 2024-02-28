package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.Actions.Command;
import ru.mipt.bit.platformer.Entities.GameEntity;

import java.util.HashMap;
import java.util.Map;

public class InputController implements Controller {

    private final GameEntity gameEntity;
    private final Map<Integer, Command> keyToActionMap = new HashMap<>();

    public InputController(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }
    @Override
    public void execute() {
        Command command = getAction();
        if (command != null) command.execute(gameEntity);
    }

    public Command getAction() {
        for (Integer key : keyToActionMap.keySet()) {
            if (Gdx.input.isKeyPressed(key)) {
                return keyToActionMap.get(key);
            }
        }
        return null;
    }

    public void addMapping(int key, Command command) {
        keyToActionMap.put(key, command);
    }
}