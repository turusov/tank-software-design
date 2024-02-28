package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Entities.BulletEntity;
import ru.mipt.bit.platformer.Entities.TankEntity;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.moveRectangleAtTileCenter;

public class BulletGraphics implements GraphicsEntity {
    private Visualisation visualisation;
    private BulletEntity bulletEntity;
    private GridPoint2 coordinates;
    public BulletGraphics(Visualisation visualisation, TiledMapTileLayer groundLayer, BulletEntity bulletEntity){
        this.visualisation = visualisation;
        this.bulletEntity = bulletEntity;
        this.coordinates = bulletEntity.getCoordinates();
        moveRectangleAtTileCenter(groundLayer, visualisation.getRectangle(), coordinates);
    }

    @Override
    public void dispose() {
        visualisation.getTexture().dispose();
    }

    @Override
    public void render(Batch batch) {
        visualisation.getTexture().dispose();
    }

    @Override
    public void calculateInterpolatedScreenCoordinates(TileMovement tileMovement) {
        tileMovement.moveRectangleBetweenTileCenters(
                visualisation.getRectangle(),
                bulletEntity.getCoordinates(),
                bulletEntity.getDestinationCoordinates(),
                1
        );
    }
}
