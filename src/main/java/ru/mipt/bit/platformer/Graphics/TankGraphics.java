package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.TankEntity;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class TankGraphics implements GraphicsEntity {
    private Visualisation visualisation;
    private GridPoint2 coordinates;
    private TankEntity tankEntity;

    public TankGraphics(Visualisation visualisation, TiledMapTileLayer groundLayer, TankEntity tankEntity){
        this.visualisation = visualisation;
        this.tankEntity = tankEntity;
        this.coordinates = tankEntity.getCoordinates();
        moveRectangleAtTileCenter(groundLayer, visualisation.getRectangle(), coordinates);
    }
    public void render(Batch batch) {
        drawTextureRegionUnscaled(batch, visualisation.getTextureRegion(),
                visualisation.getRectangle(), tankEntity.getDirection().getRotation());
    }


    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
               visualisation.getRectangle(),
                tankEntity.getCoordinates(),
                tankEntity.getDestinationCoordinates(),
                tankEntity.getMovementProgress()
        );
    }
    public void dispose() {
        visualisation.getTexture().dispose();
    }

}
