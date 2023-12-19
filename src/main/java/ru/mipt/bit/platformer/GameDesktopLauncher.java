package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.InputController;
import ru.mipt.bit.platformer.Entities.Tank;
import ru.mipt.bit.platformer.Entities.Tree;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private Tank tank;
    private Tree tree;
    private Level level;
    private InputController inputController;
    @Override
    public void create() {
        level = new Level("level.tmx");

        tank = new Tank(new Visualisation("images/tank_blue.png"), new GridPoint2(1,1), Direction.UP, 0.4f);

        tree = new Tree(new Visualisation("images/greenTree.png"), new GridPoint2(1,3), 0f);

        level.addEntityOnLevel(tank);
        level.addEntityOnLevel(tree);

        level.moveTreeRectangle(tree);

        inputController = new InputController();
        inputController.init();
    }

    @Override
    public void render() {
        clearScreen();
        float deltaTime = Gdx.graphics.getDeltaTime();

        Direction direction = inputController.getDirection();

        tank.tryMove(direction, deltaTime, tree);
        level.calcInterpolatedTankCoordinates(tank);
        level.renderAllObjects();
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
        level.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
