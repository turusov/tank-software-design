package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Actions.Command;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.TankEntity;

import java.util.*;

public class AIController implements Controller {
    private List<Controller> aiController;
    private Map<Integer, Command> keyToActionMap;
    private List<GameEntity> aiEntity;
    private GameEntity playerEntity;

    public AIController(List<GameEntity> aiEntity, GameEntity playerEntity) {
        this.aiEntity = aiEntity;
        this.playerEntity = playerEntity;
        this.keyToActionMap = new HashMap<>();
        createAIControllers();
    }


    private void createAIControllers() {
        this.aiController = new ArrayList<>();
        for (GameEntity entity : aiEntity) {
            Controller controller = createOneAIController(entity);
            if (controller != null) {
                aiController.add(controller);
            }
        }
    }
    private Controller createOneAIController(GameEntity gameEntity){
        if (gameEntity != playerEntity && gameEntity.getClass() == TankEntity.class){
            return new RandomController(gameEntity, keyToActionMap);
        }
        return null;
    }
    @Override
    public void execute() {
        aiController.forEach(Controller::execute);
    }

    public void addMapping(int key, Command command) {
        keyToActionMap.put(key, command);
    }

}
