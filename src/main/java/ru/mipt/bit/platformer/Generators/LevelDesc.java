package ru.mipt.bit.platformer.Generators;

import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.PlayerLevel;

public class LevelDesc {
    private final PlayerLevel playerLevel;
    private final GameEntity playerEntity;

    public LevelDesc(PlayerLevel playerLevel, GameEntity entity) {
        this.playerLevel = playerLevel;
        this.playerEntity = entity;
    }

    public PlayerLevel getLevelGame() {
        return playerLevel;
    }

    public GameEntity getPlayerEntity() {
        return playerEntity;
    }
}
