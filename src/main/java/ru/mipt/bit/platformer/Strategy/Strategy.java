package ru.mipt.bit.platformer.Strategy;

import ru.mipt.bit.platformer.Entities.GameEntity;

import java.util.List;

public interface Strategy {
    public List<GameEntity> generate();
}
