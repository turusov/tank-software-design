package ru.mipt.bit.platformer.Observer;

import ru.mipt.bit.platformer.Entities.GameEntity;

public interface Observable {
    void addEntity(GameEntity gameEntity);
    void removeEntity(GameEntity gameEntity);
}
