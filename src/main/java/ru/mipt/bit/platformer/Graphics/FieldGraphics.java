package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.PlayerLevel;
import ru.mipt.bit.platformer.Entities.TankModel;
import ru.mipt.bit.platformer.Entities.TreeModel;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class FieldGraphics {
    private Batch batch;
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TiledMapTileLayer groundLayer;
    private TileMovement tileMovement;
    private List<GraphicsEntity> graphicsEntities;

    public List<GraphicsEntity> getGraphicsEntities() {
        return graphicsEntities;
    }

    private List<GameEntity> entities;

    public List<GameEntity> getEntities() {
        return entities;
    }

    public FieldGraphics(String pathGameField, PlayerLevel playerLevel) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.graphicsEntities = new ArrayList<>();
        this.entities = playerLevel.getEntities();
        createGraphicsObjects();
    }

    public void renderAllObjects() {
        levelRenderer.render();
        batch.begin();
        for(GraphicsEntity graphicsEntity : graphicsEntities){
            graphicsEntity.render(batch);
        }
        batch.end();
        calculateInterpolatedScreenCoordinatesForAllObjects();
    }

    public void dispose() {
        for (GraphicsEntity graphicsEntity : graphicsEntities) graphicsEntity.dispose();
        level.dispose();
        batch.dispose();
    }

    private void createGraphicsObjects() {
        for(GameEntity gameEntity : entities){
            if(gameEntity instanceof TreeModel){
                graphicsEntities.add(new TreeGraphics(new Visualisation("images/greenTree.png"),groundLayer, (TreeModel) gameEntity));
            }
            if(gameEntity instanceof TankModel){
                graphicsEntities.add(new TankGraphics(new Visualisation("images/tank_blue.png"),groundLayer, (TankModel) gameEntity));
            }
        }
    }

    private void calculateInterpolatedScreenCoordinatesForAllObjects() {
        for(GraphicsEntity graphicsEntity : graphicsEntities){
            graphicsEntity.calculateInterpolatedScreenCoordinates(tileMovement);
        }
    }
    public float getDeltaTime(){
        return Gdx.graphics.getDeltaTime();
    }
}

