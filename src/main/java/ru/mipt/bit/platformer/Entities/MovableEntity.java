package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;

public interface MovableEntity extends GameEntity {
    boolean isMoving();
    void rotate(Direction direction);
    void moveTo(GridPoint2 targetCoordinates);
    GridPoint2 getDestinationCoordinates();
    Direction getDirection();
}
