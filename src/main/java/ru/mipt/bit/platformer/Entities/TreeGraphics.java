package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Graphics.GraphicsObject;
import ru.mipt.bit.platformer.Graphics.Visualisation;
import ru.mipt.bit.platformer.Models.TreeModel;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class Tree implements GraphicsObject {
    private final GridPoint2 coordinates;
    private final float rotation;
    private Visualisation visualisation;
    private TreeModel treeModel;
    public Tree(Visualisation visualisation, TiledMapTileLayer groundLayer){
        this.visualisation = visualisation;
        this.coordinates = treeModel.getCoordinates();
        this.rotation = 0f;
        moveRectangleAtTileCenter(groundLayer, visualisation.getRectangle(), coordinates);
    }

    public Visualisation getVisualisation() {
        return visualisation;
    }
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, visualisation.getTextureRegion(), visualisation.getRectangle(), 0f);
    }

    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                visualisation.getRectangle(),
                treeModel.getCoordinates(),
                treeModel.getCoordinates(),
                1
        );
    }
    public void dispose(){
        visualisation.getTexture().dispose();
    }
}
