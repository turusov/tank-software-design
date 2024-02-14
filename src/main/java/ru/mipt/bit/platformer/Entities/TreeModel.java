package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.math.GridPoint2;

public class TreeModel implements GameEntity {
    private GridPoint2 coordinates;

    public TreeModel(GridPoint2 coordinates) {
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    @Override
    public void updateState(float deltaTime) {
    }
}
