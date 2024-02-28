package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Actions.MoveCommand;
import ru.mipt.bit.platformer.Controllers.*;
import ru.mipt.bit.platformer.Entities.*;
import ru.mipt.bit.platformer.Generators.PlayerLogicLevel;
import ru.mipt.bit.platformer.Graphics.GraphicsLevel;
import ru.mipt.bit.platformer.Generators.LevelRandomGenerator;
import ru.mipt.bit.platformer.Observer.GraphicsObserver;

import static com.badlogic.gdx.Input.Keys.*;


import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;

public class GameDesktopLauncher implements ApplicationListener {
    private GraphicsLevel graphicsLevel;
    private InputController inputController;
    private LevelRandomGenerator levelGenerator;
    private LogicLevel logicLevel;
    private CollisionHandler collisionHandler;
    private GameEntity playerEntity;
    private AIController aiController;


    @Override
    public void create() {
        LevelShape levelShape = new LevelShape(8, 10);
        levelGenerator = new LevelRandomGenerator(levelShape, 10, 2);
        PlayerLogicLevel playerLogicLevel = levelGenerator.generatePlayerLogicLevel();
        playerEntity = playerLogicLevel.getPlayerEntity();
        logicLevel = playerLogicLevel.getLevelGame();
        collisionHandler = new CollisionHandler(logicLevel.getEntities(), levelShape);
        graphicsLevel = new GraphicsLevel("level.tmx", logicLevel);
        inputController = new InputController(playerEntity);
//        aiController = new AIAdapterController(logicLevel.getEntities(), (MovableEntity) playerEntity, levelShape, collisionHandler);
        aiController = new AIController(logicLevel.getEntities(), playerEntity);
        initKeyMappingsForPlayerInputController();
        initKeyMappingsForAIController();

        logicLevel.addObserver(new GraphicsObserver(graphicsLevel));
        logicLevel.addEntity(new TankEntity(new GridPoint2(0,0), Direction.DOWN));
    }
    private void initKeyMappingsForAIController() {
        aiController.addMapping(UP, new MoveCommand(Direction.UP, collisionHandler));
        aiController.addMapping(DOWN, new MoveCommand(Direction.DOWN, collisionHandler));
        aiController.addMapping(LEFT, new MoveCommand(Direction.LEFT, collisionHandler));
        aiController.addMapping(RIGHT, new MoveCommand(Direction.RIGHT, collisionHandler));
    }

    private void initKeyMappingsForPlayerInputController() {
        inputController.addMapping(UP, new MoveCommand(Direction.UP, collisionHandler));
        inputController.addMapping(W, new MoveCommand(Direction.UP, collisionHandler));
        inputController.addMapping(LEFT, new MoveCommand(Direction.LEFT, collisionHandler));
        inputController.addMapping(A, new MoveCommand(Direction.LEFT, collisionHandler));
        inputController.addMapping(DOWN, new MoveCommand(Direction.DOWN, collisionHandler));
        inputController.addMapping(S, new MoveCommand(Direction.DOWN, collisionHandler));
        inputController.addMapping(RIGHT, new MoveCommand(Direction.RIGHT, collisionHandler));
        inputController.addMapping(D, new MoveCommand(Direction.RIGHT, collisionHandler));
    }

    @Override
    public void render() {
        clearScreen();
        inputController.execute();
        aiController.execute();
        logicLevel.update(graphicsLevel.getDeltaTime());
        graphicsLevel.renderAllObjects();
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
        graphicsLevel.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
