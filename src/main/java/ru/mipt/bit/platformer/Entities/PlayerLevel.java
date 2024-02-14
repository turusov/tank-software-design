package ru.mipt.bit.platformer.Entities;

import java.util.List;

public class PlayerLevel {

    private List<GameEntity> entities;
    public List<GameEntity> getEntities() {
        return entities;
    }
    public PlayerLevel(List<GameEntity> gameEntities) {
        this.entities = gameEntities;
    }

    public void update(float deltaTime) {
        for (GameEntity obj : entities) {
            {
                obj.updateState(deltaTime);
            }
        }
    }
}
