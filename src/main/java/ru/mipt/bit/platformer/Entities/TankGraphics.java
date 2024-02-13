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

public class TankGraphics implements GraphicsObject {
    private Visualisation visualisation;
    private GridPoint2 coordinates;
    private TankModel tankModel;
    private float rotation;

    public TankGraphics(Visualisation visualisation, TiledMapTileLayer groundLayer, TankModel tankModel){
        this.visualisation = visualisation;
        this.tankModel = tankModel;
        this.coordinates = tankModel.getCoordinates();
        this.rotation = tankModel.getDirection().getRotation();
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
