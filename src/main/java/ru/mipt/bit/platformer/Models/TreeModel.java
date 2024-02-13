package ru.mipt.bit.platformer.Models;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.GameEntity;

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
