package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank {
    private static final float MOVEMENT_SPEED = 0.4f;
    private Direction direction;
    private float movementProgress;
    private Visualisation visualisation;
    private GridPoint2 destinationCoordinates;
    private GridPoint2 coordinates;

    public Tank(Visualisation visualisation, GridPoint2 coordinates, Direction direction){
        this.movementProgress = 1f;
        this.visualisation = visualisation;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public GridPoint2 getDestinationCoordinates() {
        return destinationCoordinates;
    }
    public float getMovementProgress(){
        return movementProgress;
    }
    public GridPoint2 getCoordinates() {
        return coordinates;
    }
    public boolean isMoving() {
        return !isEqual(movementProgress, 1f);
    }
    public void moveTo(GridPoint2 tankTargetCoordinates) {
        destinationCoordinates = tankTargetCoordinates;
        movementProgress = 0;
    }
    public void rotate(Direction direction) {
        this.direction = direction;
    }
    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, MOVEMENT_SPEED);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
        }
    }

    public Visualisation getVisualisation() {
        return visualisation;
    }
}
