package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;

public interface MovingEntity extends GameEntity{
    public boolean isMoving();
    public void rotate(Direction direction);
    public void moveTo(GridPoint2 targetCoordinates);
}
