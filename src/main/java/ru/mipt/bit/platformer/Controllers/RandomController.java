package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Actions.Command;
import ru.mipt.bit.platformer.Entities.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class RandomController implements Controller {
    private final Map<Integer, Command> keyToActionMap;
    private final GameEntity aiEntity;

    public RandomController(GameEntity aiEntity , Map<Integer, Command> keyToActionMap) {
        this.aiEntity = aiEntity;
        this.keyToActionMap = keyToActionMap;
    }

    public Command getAction(){
        Random random = new Random();
        List<Integer> keysAsArray = new ArrayList<>(keyToActionMap.keySet());
        return keyToActionMap.get(keysAsArray.get(random.nextInt(keysAsArray.size())));
    }
    @Override
    public void execute() {
        Command command = getAction();
        if (command != null) command.execute(aiEntity);
    }
}
