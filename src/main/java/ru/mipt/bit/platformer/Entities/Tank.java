package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.CollisionDetector;
import ru.mipt.bit.platformer.Direction;
import ru.mipt.bit.platformer.Visualisation;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;
import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class Tank implements GameEntity{
    private final float movementSpeed;
    private Direction direction;
    private float movementProgress;
    private Visualisation visualisation;
    private GridPoint2 destinationCoordinates;
    private GridPoint2 coordinates;

    public Tank(Visualisation visualisation, GridPoint2 coordinates, Direction direction, float movementSpeed){
        this.movementProgress = 1f;
        this.visualisation = visualisation;
        this.coordinates = coordinates;
        this.destinationCoordinates = coordinates;
        this.direction = direction;
        this.movementSpeed = 0.4f;
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
    public Visualisation getVisualisation() { return visualisation; }
    public void moveTo(GridPoint2 tankTargetCoordinates) {
        destinationCoordinates = tankTargetCoordinates;
        movementProgress = 0;
    }
    public void rotate(Direction direction) {
        this.direction = direction;
    }
    public void updateState(float deltaTime) {
        movementProgress = continueProgress(movementProgress, deltaTime, movementSpeed);
        if (!isMoving()) {
            coordinates = destinationCoordinates;
        }
    }
    public boolean isMoving() {
        return !isEqual(movementProgress, 1f);
    }
    public void drawEntityTexture(Batch batch){
        drawTextureRegionUnscaled(batch,
                visualisation.getTextureRegion(),
                visualisation.getRectangle(),
                direction.getRotation());
    }
    public void tryMove(Direction direction, float deltaTime, Tree tree) {
        if(direction!=null && !this.isMoving()){
            if(!CollisionDetector.ifCollides(this, tree, direction)) {
                this.moveTo(direction.add(this.getCoordinates()));
            }
            this.rotate(direction);
        }
        this.updateState(deltaTime);
    }
    public void dispose(){
        this.getVisualisation().getTexture().dispose();
    }
}
