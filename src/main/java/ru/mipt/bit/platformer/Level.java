package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.Tank;
import ru.mipt.bit.platformer.Entities.Tree;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Level {
    private final TiledMap levelTiledMap;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final Batch batch;


    private List<GameEntity> entitiesOnLevel = new ArrayList<>();

    public TiledMap getLevelTiledMap() {
        return levelTiledMap;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public Batch getBatch() {
        return batch;
    }

    private final TileMovement tileMovement;
    public void addEntityOnLevel(GameEntity gameEntity){
        entitiesOnLevel.add(gameEntity);
    }
    public void calcInterpolatedTankCoordinates(Tank tank){
        tileMovement.moveRectangleBetweenTileCenters(tank.getVisualisation().getRectangle(),
                tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
    }
    public void renderAllObjects(){
        levelRenderer.render();
        batch.begin();
        for (GameEntity gameEntity : entitiesOnLevel){
            gameEntity.drawEntityTexture(batch);
        }
        batch.end();
    }

    public Level(String levelPath) {
        this.batch = new SpriteBatch();
        this.levelTiledMap = new TmxMapLoader().load(levelPath);
        this.levelRenderer =  createSingleLayerMapRenderer(levelTiledMap, batch);
        this.groundLayer = getSingleLayer(levelTiledMap);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }
    public void moveTreeRectangle(Tree tree){
        moveRectangleAtTileCenter(groundLayer, tree.getVisualisation().getRectangle(), tree.getCoordinates());
    }
    public void dispose(){
        for (GameEntity gameEntity : entitiesOnLevel) {
            gameEntity.dispose();
        }
        levelTiledMap.dispose();
        batch.dispose();
    }
}
