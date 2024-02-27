package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.Entities.GameEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class RandomController implements Controller {
    private final Map<Integer, Action> keyToActionMap;
    private final GameEntity aiEntity;

    public RandomController(GameEntity aiEntity , Map<Integer, Action> keyToActionMap) {
        this.aiEntity = aiEntity;
        this.keyToActionMap = keyToActionMap;
    }

    public Action getAction(){
        Random random = new Random();
        List<Integer> keysAsArray = new ArrayList<>(keyToActionMap.keySet());
        return keyToActionMap.get(keysAsArray.get(random.nextInt(keysAsArray.size())));
    }
    @Override
    public void execute() {
        Action action = getAction();
        if (action != null) action.apply(aiEntity);
    }
}
