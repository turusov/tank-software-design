package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.math.GridPoint2;

public class TreeEntity implements GameEntity {
    private GridPoint2 coordinates;

    public TreeEntity(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    @Override
    public void updateState(float deltaTime) {
    }
}
