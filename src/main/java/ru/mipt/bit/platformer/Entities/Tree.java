package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Level;
import ru.mipt.bit.platformer.Visualisation;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class Tree implements Obstacle {
    private final GridPoint2 coordinates;
    private final float rotation;
    private Visualisation visualisation;
    public Tree(Visualisation visualisation, GridPoint2 coordinates, float rotation){
        this.visualisation = visualisation;
        this.coordinates = coordinates;
        this.rotation = 0f;
    }

    public float getRotation() {
        return rotation;
    }
    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Visualisation getVisualisation() {
        return visualisation;
    }
    public void drawEntityTexture(Batch batch){
        drawTextureRegionUnscaled(batch,
                visualisation.getTextureRegion(),
                visualisation.getRectangle(),
                rotation);
    }
    public void dispose(){
        this.getVisualisation().getTexture().dispose();
    }
}
