package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;
import org.lwjgl.system.CallbackI;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {
    private Tank tank;
    private Tree tree;
    private Level level;
    private InputController inputController;
    @Override
    public void create() {
        level = new Level("level.tmx");

        tank = new Tank(new Visualisation("images/tank_blue.png"), new GridPoint2(1,1), Direction.UP);

        tree = new Tree(new Visualisation("images/greenTree.png"), new GridPoint2(1,3));

        moveRectangleAtTileCenter(level.getGroundLayer(), tree.getVisualisation().getRectangle(), tree.getCoordinates());

        initKeyMappings();
    }

    @Override
    public void render() {
        clearScreen();

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        Direction direction = inputController.getDirection();

        tankMoveToDirection(direction, deltaTime);
        renderGame();
    }

    private void tankMoveToDirection(Direction direction, float deltaTime) {
        if(direction!=null && !tank.isMoving()){
            GridPoint2 tankTargetCoordinates = direction.add(tank.getCoordinates());
            if(!collides(tree.getCoordinates(), tankTargetCoordinates)){
                tank.moveTo(tankTargetCoordinates);
            }
            tank.rotate(direction);
        }
        // calculate interpolated player screen coordinates
        level.getTileMovement().moveRectangleBetweenTileCenters(tank.getVisualisation().getRectangle(),
                tank.getCoordinates(), tank.getDestinationCoordinates(), tank.getMovementProgress());
        tank.updateState(deltaTime);
    }

    private void renderGame() {
        // render each tile of the level
        level.getLevelRenderer().render();

        // start recording all drawing commands
        level.getBatch().begin();

        drawTextureRegionUnscaled(level.getBatch(),
                tank.getVisualisation().getTextureRegion(),
                tank.getVisualisation().getRectangle(),
                tank.getDirection().getRotation());
        drawTextureRegionUnscaled(level.getBatch(),
                tree.getVisualisation().getTextureRegion(),
                tree.getVisualisation().getRectangle(), 0f
                );
        // submit all drawing requests
        level.getBatch().end();
    }

    private boolean collides(GridPoint2 object1, GridPoint2 object2) {
        return object1.equals(object2);
    }
    private void initKeyMappings() {
        inputController = new InputController();
        inputController.addMapping(UP, Direction.UP);
        inputController.addMapping(W, Direction.UP);
        inputController.addMapping(LEFT, Direction.LEFT);
        inputController.addMapping(A, Direction.LEFT);
        inputController.addMapping(DOWN, Direction.DOWN);
        inputController.addMapping(S, Direction.DOWN);
        inputController.addMapping(RIGHT, Direction.RIGHT);
        inputController.addMapping(D, Direction.RIGHT);
    }
    private void clearScreen() {
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        tree.getVisualisation().getTexture().dispose();
        tank.getVisualisation().getTexture().dispose();
        level.getLevel().dispose();
        level.getBatch().dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
