package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.Actions.LevelListener;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.TankGraphics;
import ru.mipt.bit.platformer.Actions.Action;
import ru.mipt.bit.platformer.Entities.TreeGraphics;
import ru.mipt.bit.platformer.Models.TankModel;
import ru.mipt.bit.platformer.Models.TreeModel;
import ru.mipt.bit.platformer.util.TileMovement;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

import static ru.mipt.bit.platformer.util.GdxGameUtils.createSingleLayerMapRenderer;
import static ru.mipt.bit.platformer.util.GdxGameUtils.getSingleLayer;

public class FieldGraphics implements LevelListener {
        private Batch batch;
        private TiledMap level;
        private MapRenderer levelRenderer;
        private TiledMapTileLayer groundLayer;
        private TileMovement tileMovement;
        private List<GraphicsObject> graphicsObjects;
        private List<GameEntity> entities;
        private float deltaTime;

        public FieldGraphics(String pathGameField) {
            this.batch = new SpriteBatch();
            this.level = new TmxMapLoader().load(pathGameField);
            this.levelRenderer = createSingleLayerMapRenderer(level, batch);
            this.groundLayer = getSingleLayer(level);
            this.tileMovement = new TileMovement(groundLayer, Interpolation.smooth);
            this.graphicsObjects = new ArrayList<>();
            this.entities = new ArrayList<>();
        }

        public void renderAllObjects() {
            levelRenderer.render();
            batch.begin();
            for(GraphicsObject graphicsObject : graphicsObjects){
                graphicsObject.render(batch);
//                System.out.println(graphicsObject.getClass().getSimpleName());
//                if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
//                    System.out.println("pressed");
//                }
            }
            batch.end();
            calculateInterpolatedScreenCoordinatesForAllObjects();
        }

        public void dispose() {
            for (GraphicsObject graphicsObject : graphicsObjects) graphicsObject.dispose();
            level.dispose();
            batch.dispose();
        }

        private void createGraphicsObject(TreeModel model) {
            TreeGraphics newGraphicsObject = new TreeGraphics(new Visualisation("images/greenTree.png"), groundLayer, model);
            graphicsObjects.add(newGraphicsObject);
            entities.add(model);
        }

        private void createGraphicsObject(TankModel model) {
            TankGraphics newGraphicsObject = new TankGraphics(new Visualisation("images/tank_blue.png"), groundLayer, model);
            graphicsObjects.add(newGraphicsObject);
            entities.add(model);
        }

        private void calculateInterpolatedScreenCoordinatesForAllObjects() {
            for(GraphicsObject graphicsObject : graphicsObjects){
                graphicsObject.calculateInterpolatedScreenCoordinates(tileMovement);
            }
        }

        public float getDeltaTime() {
            return deltaTime;
        }

        @Override
        public void addGameEntity(GameEntity gameEntity) {
            if (gameEntity.getClass().getSimpleName().equals("TreeModel")) {
                createGraphicsObject((TreeModel) gameEntity);
                System.out.println("ss2");
            }
            else if (gameEntity.getClass().getSimpleName().equals("TankModel")){
                createGraphicsObject((TankModel) gameEntity);
                System.out.println("ss3");
            }
        }
}

