package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class Level {
    private final TiledMap level;
    private final MapRenderer levelRenderer;
    private final TiledMapTileLayer groundLayer;
    private final Batch batch;

    public TiledMap getLevel() {
        return level;
    }

    public MapRenderer getLevelRenderer() {
        return levelRenderer;
    }

    public TiledMapTileLayer getGroundLayer() {
        return groundLayer;
    }

    public Batch getBatch() {
        return batch;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    private final TileMovement tileMovement;


    public Level(String levelPath) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(levelPath);
        this.levelRenderer =  createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
    }
}
