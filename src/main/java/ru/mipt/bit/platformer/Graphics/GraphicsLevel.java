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
import ru.mipt.bit.platformer.Entities.LogicLevel;
import ru.mipt.bit.platformer.Entities.TankEntity;
import ru.mipt.bit.platformer.Entities.TreeEntity;
import ru.mipt.bit.platformer.util.TileMovement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class GraphicsLevel{
    private Batch batch;
    private TiledMap level;
    private MapRenderer levelRenderer;
    private TiledMapTileLayer groundLayer;
    private TileMovement tileMovement;
    private List<GraphicsEntity> graphicsEntities;
    private LogicLevel logicLevel;
    private Map<GameEntity, GraphicsEntity> gameEntityGraphicsEntityMap;

    public GraphicsLevel(String pathGameField, LogicLevel logicLevel) {
        this.batch = new SpriteBatch();
        this.level = new TmxMapLoader().load(pathGameField);
        this.levelRenderer = createSingleLayerMapRenderer(level, batch);
        this.groundLayer = getSingleLayer(level);
        this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
        this.graphicsEntities = new ArrayList<>();
        this.logicLevel = logicLevel;
        this.gameEntityGraphicsEntityMap = new HashMap<>();
        createGraphicsObjects(logicLevel.getEntities());
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

    private void createGraphicsObjects(List<GameEntity> entities) {
        for(GameEntity gameEntity : entities){
            addGraphicsEntity(gameEntity);
        }
    }
    public void addGraphicsEntity(GameEntity gameEntity){
        GraphicsEntity graphicsEntity = null;
        if(gameEntity instanceof TreeEntity){
            graphicsEntity = new TreeGraphics(new Visualisation("images/greenTree.png"),groundLayer, (TreeEntity) gameEntity);
        }
        if(gameEntity instanceof TankEntity){
            graphicsEntity = new TankGraphics(new Visualisation("images/tank_blue.png"),groundLayer, (TankEntity) gameEntity);
        }
        graphicsEntities.add(graphicsEntity);
        gameEntityGraphicsEntityMap.put(gameEntity, graphicsEntity);
    }

    private void calculateInterpolatedScreenCoordinatesForAllObjects() {
        for(GraphicsEntity graphicsEntity : graphicsEntities){
            graphicsEntity.calculateInterpolatedScreenCoordinates(tileMovement);
        }
    }
    public float getDeltaTime(){
        return Gdx.graphics.getDeltaTime();
    }
    public void removeGraphicsEntity(GameEntity gameEntity){
        graphicsEntities.remove(gameEntityGraphicsEntityMap.get(gameEntity));
    }
}

