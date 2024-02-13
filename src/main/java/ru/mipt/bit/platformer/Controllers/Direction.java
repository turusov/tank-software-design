package ru.mipt.bit.platformer.Controllers;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.MovingEntity;

import java.util.List;

public enum Direction implements Action {
    UP(new GridPoint2(0,1), 90),
    DOWN(new GridPoint2(0,-1), -90),
    LEFT(new GridPoint2(-1,0), -180),
    RIGHT(new GridPoint2(1,0), 0);

    private final GridPoint2 vector;
    private final float rotation;

    Direction(GridPoint2 vector, float rotation) {
        this.vector = vector;
        this.rotation = rotation;
    }
    public GridPoint2 add(GridPoint2 point) {
        return point.cpy().add(vector);
    }
    public float getRotation() {
        return rotation;
    }

    @Override
    public void apply(GameEntity object, float deltaTime) {
        MovingEntity movingObjects = (MovingEntity) object;
        movingObjects.moveTo(add(movingObjects.getCoordinates()));
    }

    @Override
    public boolean applyToObject(GameEntity object, List<GameEntity> listObjectsInGame) {
        boolean isApplyToObject = true;
        MovingEntity movingObject = (MovingEntity) object;
        for (GameEntity obj : listObjectsInGame) {
            isApplyToObject = CollisionDetector.ifCollides(obj, movingObject, this);
            if (!isApplyToObject) return false;
        }
        return isApplyToObject;
    }
}
