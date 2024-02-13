package ru.mipt.bit.platformer.Entities;

import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.Actions.LevelListener;
//import ru.mipt.bit.platformer.Entities.Level;
import ru.mipt.bit.platformer.Strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

public class LevelGame {
    private Strategy strategy;
    private List<GameEntity> entities;
    private LevelListener levelListener;
    public LevelGame(LevelListener levelListener, Strategy strategy) {
        this.entities = new ArrayList<>();
        this.levelListener = levelListener;
        this.strategy = strategy;
        generateObjects();
    }

    public void generateObjects() {
//        List<GameEntity> arr = new ArrayList<>();
//        arr.add(new TankModel(new GridPoint2(1,1), Direction.UP));
        entities.addAll(strategy.generate());
//        objectsInGameList.addAll(arr);
        for(GameEntity ge : entities){
            System.out.println(ge.getClass());
        }
        for (GameEntity ge : entities){
            levelListener.addGameEntity(ge);
            System.out.println("ss");
        }
    }

    public void update(float deltaTime) {
        for (GameEntity obj : entities) {
            {
                obj.updateState(deltaTime);
            }
        }
    }

    public void apply(Action action, GameEntity object, float deltaTime) {
        if (action.applyToObject(object, entities))
            action.apply(object, deltaTime);
    }
}
