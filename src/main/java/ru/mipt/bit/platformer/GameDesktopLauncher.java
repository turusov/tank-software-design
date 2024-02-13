package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.Controllers.InputController;
//import ru.mipt.bit.platformer.Entities.Level;
import ru.mipt.bit.platformer.Entities.LevelGame;
import ru.mipt.bit.platformer.Graphics.FieldGraphics;
import ru.mipt.bit.platformer.Strategy.LevelRandomly;


import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private FieldGraphics fieldGraphics;
    private InputController inputController;
    private LevelGame levelGame;

    @Override
    public void create() {
        fieldGraphics = new FieldGraphics("level.tmx");
        levelGame = new LevelGame(fieldGraphics, new LevelRandomly(3));
        inputController = new InputController();
        inputController.init();
    }

    @Override
    public void render() {
        clearScreen();
        fieldGraphics.renderAllObjects();
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
        fieldGraphics.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
