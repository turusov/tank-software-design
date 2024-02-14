package ru.mipt.bit.platformer.Actions;

import ru.mipt.bit.platformer.Entities.GameEntity;

public interface Action {
    void apply(GameEntity gameEntity);
}
