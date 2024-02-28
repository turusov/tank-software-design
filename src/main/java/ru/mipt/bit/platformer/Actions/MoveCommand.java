package ru.mipt.bit.platformer.Actions;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.CollisionHandler;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.MovableEntity;

public class MoveCommand implements Command {
    private final Direction direction;
    private final CollisionHandler collisionHandler;

    public MoveCommand(Direction direction, CollisionHandler collisionHandler) {
        this.direction = direction;
        this.collisionHandler = collisionHandler;
    }
    private boolean isPossibleToApplyAction(Direction direction, GameEntity gameEntity) {
        return collisionHandler.ifCollides(gameEntity, direction);
    }

    @Override
    public void execute(GameEntity gameEntity) {
        MovableEntity movableEntity = (MovableEntity) gameEntity;
        if (!movableEntity.isMoving()) {
            movableEntity.rotate(direction);
            if (isPossibleToApplyAction(direction, movableEntity)) {
                GridPoint2 newCoordinates = direction.add(movableEntity.getCoordinates());
                movableEntity.moveTo(newCoordinates);
            }
        }
    }
}
