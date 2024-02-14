package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class TankModel implements MovingEntity {
    private static final float MOVEMENT_SPEED = 0.4f;
    public static final float MOVEMENT_COMPLETED = 1f;
    public static final int MOVEMENT_STARTED = 0;

    private float movementProgress;
    private GridPoint2 coordinates;
    private GridPoint2 destinationCoordinates;
    private Direction direction;


    public TankModel(GridPoint2 coordinates, Direction direction) {
        this.movementProgress = 1f;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        this.direction = direction;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public boolean isMoving() {
        return !isEqual(movementProgress, MOVEMENT_COMPLETED);
    }

    public void moveTo(GridPoint2 targetCoordinates) {
        destinationCoordinates = targetCoordinates;
        movementProgress = MOVEMENT_STARTED;
    }

    public void rotate(Direction direction) {
        this.direction = direction;
    }
    @Override
    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
        }
    }

    public Direction getDirection() {
        return direction;
    }


    public float getMovementProgress() {
        return movementProgress;
    }
}
