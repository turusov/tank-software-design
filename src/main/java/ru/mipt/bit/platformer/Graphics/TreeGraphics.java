package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.TreeModel;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;
import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class TreeGraphics implements GraphicsEntity {
    private final GridPoint2 coordinates;
    private Visualisation visualisation;
    private TreeModel treeModel;
    public TreeGraphics(Visualisation visualisation, TiledMapTileLayer groundLayer, TreeModel treeModel){
        this.visualisation = visualisation;
        this.coordinates = treeModel.getCoordinates();
        this.treeModel = treeModel;
        moveRectangleAtTileCenter(groundLayer, visualisation.getRectangle(), coordinates);
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
