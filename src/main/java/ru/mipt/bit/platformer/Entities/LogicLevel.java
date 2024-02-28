package ru.mipt.bit.platformer.Entities;

import ru.mipt.bit.platformer.Observer.Observable;
import ru.mipt.bit.platformer.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class LogicLevel implements Observable{

    private List<GameEntity> entities;
    private List<Observer>  observers;
    public List<GameEntity> getEntities() {
        return entities;
    }
    public LogicLevel(List<GameEntity> gameEntities) {
        this.entities = gameEntities;
        this.observers = new ArrayList<>();
    }
    public LogicLevel(List<GameEntity> gameEntities, List<Observer> observers) {
        this.entities = gameEntities;
        this.observers = observers;
    }
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    public void addEntity(GameEntity gameEntity){
        entities.add(gameEntity);
        observers.forEach(observer -> observer.onAddEntity(gameEntity));
    }
    public void removeEntity(GameEntity gameEntity){
        entities.remove(gameEntity);
        observers.forEach(observer -> observer.onRemoveEntity(gameEntity));
    }
    public void update(float deltaTime) {
        for (GameEntity obj : entities) {
            {
                obj.updateState(deltaTime);
            }
        }
    }
}
