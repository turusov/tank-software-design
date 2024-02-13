package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Graphics.GraphicsObject;
import ru.mipt.bit.platformer.Graphics.Visualisation;
import ru.mipt.bit.platformer.Models.TankModel;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tank implements GraphicsObject {
    private Visualisation visualisation;
    private GridPoint2 coordinates;
    private TankModel tankModel;

    public Tank(Visualisation visualisation, TankModel tankModel, TiledMapTileLayer groundLayer){
        this.visualisation = visualisation;
        this.coordinates = tankModel.getCoordinates();
        this.tankModel = tankModel;
        moveRectangleAtTileCenter(groundLayer, visualisation.getRectangle(), coordinates);
    }
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, visualisation.getTextureRegion(),
                visualisation.getRectangle(), tankModel.getDirection().getRotation());
    }


    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
               visualisation.getRectangle(),
                tankModel.getCoordinates(),
                tankModel.getDestinationCoordinates(),
                tankModel.getMovementProgress()
        );
    }
    public void dispose() {
        visualisation.getTexture().dispose();
    }

}
