package ru.mipt.bit.platformer.Generators;

import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.LogicLevel;

public class PlayerLogicLevel {
    private final LogicLevel logicLevel;
    private final GameEntity playerEntity;

    public PlayerLogicLevel(LogicLevel logicLevel, GameEntity entity) {
        this.logicLevel = logicLevel;
        this.playerEntity = entity;
    }

    public LogicLevel getLevelGame() {
        return logicLevel;
    }

    public GameEntity getPlayerEntity() {
        return playerEntity;
    }
}
