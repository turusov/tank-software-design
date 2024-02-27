package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.mipt.bit.platformer.Actions.MoveAction;
import ru.mipt.bit.platformer.Adapter.AIAdapterController;
import ru.mipt.bit.platformer.Controllers.*;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.MovingEntity;
import ru.mipt.bit.platformer.Entities.PlayerLevel;
import ru.mipt.bit.platformer.Generators.LevelDesc;
import ru.mipt.bit.platformer.Graphics.FieldGraphics;
import ru.mipt.bit.platformer.Generators.LevelRandomly;
import ru.mipt.bit.platformer.Entities.LevelShape;
import static com.badlogic.gdx.Input.Keys.*;


import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private FieldGraphics fieldGraphics;
    private InputController inputController;
    private LevelRandomly levelGenerator;
    private PlayerLevel playerLevel;
    private CollisionHandler collisionHandler;
    private GameEntity playerEntity;
    private AIAdapterController aiController;


    @Override
    public void create() {
        LevelShape levelShape = new LevelShape(8, 10);
        levelGenerator = new LevelRandomly(levelShape, 10, 5);
        LevelDesc levelDesc = levelGenerator.generateLevelDesc();
        playerEntity = levelDesc.getPlayerEntity();
        playerLevel = levelDesc.getLevelGame();
        collisionHandler = new CollisionHandler(playerLevel.getEntities(), levelShape);
        fieldGraphics = new FieldGraphics("level.tmx", playerLevel);
        inputController = new InputController(playerEntity);
        aiController = new AIAdapterController(playerLevel.getEntities(), (MovingEntity) playerEntity, levelShape, collisionHandler);
        initKeyMappingsForPlayerInputController();
    }

    private void initKeyMappingsForPlayerInputController() {
        inputController.addMapping(UP, new MoveAction(Direction.UP, collisionHandler));
        inputController.addMapping(W, new MoveAction(Direction.UP, collisionHandler));
        inputController.addMapping(LEFT, new MoveAction(Direction.LEFT, collisionHandler));
        inputController.addMapping(A, new MoveAction(Direction.LEFT, collisionHandler));
        inputController.addMapping(DOWN, new MoveAction(Direction.DOWN, collisionHandler));
        inputController.addMapping(S, new MoveAction(Direction.DOWN, collisionHandler));
        inputController.addMapping(RIGHT, new MoveAction(Direction.RIGHT, collisionHandler));
        inputController.addMapping(D, new MoveAction(Direction.RIGHT, collisionHandler));
    }

    @Override
    public void render() {
        clearScreen();
        inputController.execute();
        aiController.execute();
        playerLevel.update(fieldGraphics.getDeltaTime());
        fieldGraphics.renderAllObjects();
        collisionHandler.update();
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
