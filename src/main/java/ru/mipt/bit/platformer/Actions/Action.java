package ru.mipt.bit.platformer.Actions;

import ru.mipt.bit.platformer.Entities.GameEntity;

import java.util.List;

public interface Action {
    public void apply(GameEntity object, float deltaTime);
    public boolean applyToObject(GameEntity entity, List<GameEntity> objects);
}
