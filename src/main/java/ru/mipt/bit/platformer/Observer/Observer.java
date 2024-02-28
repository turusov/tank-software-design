package ru.mipt.bit.platformer.Observer;

import ru.mipt.bit.platformer.Entities.GameEntity;

public interface Observer {
    void onAddEntity(GameEntity gameEntity);
    void onRemoveEntity(GameEntity gameEntity);
}
