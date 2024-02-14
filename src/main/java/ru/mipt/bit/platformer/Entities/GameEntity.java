package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.math.GridPoint2;

public interface GameEntity {
    GridPoint2 getCoordinates();
    void updateState(float deltaTime);
}
