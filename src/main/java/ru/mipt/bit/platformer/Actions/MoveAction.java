package ru.mipt.bit.platformer.Actions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.CollisionHandler;
import ru.mipt.bit.platformer.Controllers.Controller;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.MovingEntity;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class MoveAction implements Action{
    private final Direction direction;
    private final CollisionHandler collisionHandler;

    public MoveAction(Direction direction, CollisionHandler collisionHandler) {
        this.direction = direction;
        this.collisionHandler = collisionHandler;
    }
    private boolean isPossibleToApplyAction(Direction direction, GameEntity gameEntity) {
        return collisionHandler.ifCollides(gameEntity, direction);
    }

    @Override
    public void apply(GameEntity gameEntity) {
        MovingEntity movingEntity = (MovingEntity) gameEntity;
        if (!movingEntity.isMoving()) {
            movingEntity.rotate(direction);
            if (isPossibleToApplyAction(direction, movingEntity)) {
                GridPoint2 newCoordinates = direction.add(movingEntity.getCoordinates());
                movingEntity.moveTo(newCoordinates);
            }
        }
    }
}
