package ru.mipt.bit.platformer.Generators;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.LogicLevel;
import ru.mipt.bit.platformer.Entities.LevelShape;
import ru.mipt.bit.platformer.Entities.TankEntity;
import ru.mipt.bit.platformer.Entities.TreeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelRandomGenerator implements LevelGenerator {
    private List<GameEntity> listEntities;

    private final int treeNum;
    private final int tankNum;
    private final LevelShape levelShape;
    private TankEntity playerTank;
    private Random random;

    public LevelRandomGenerator(LevelShape levelShape, int treeNum, int tankNum) {
        this.listEntities = new ArrayList<>();
        this.random = new Random();
        this.tankNum = tankNum;
        this.treeNum = treeNum;
        this.levelShape = levelShape;
    }
    private void generatePlayerObject(){
        playerTank = new TankEntity(generateCoordinates(), Direction.UP);
        listEntities.add(playerTank);
    }
    private void generateTanks() {
        for (int i = 0; i < tankNum; i++) {
            listEntities.add(generateTank());
        }
    }

    private void generateTrees() {
        for (int i = 0; i < treeNum; i++) {
            listEntities.add(generateTree());
        }
    }

    private GridPoint2 generateCoordinates() {
        int width = levelShape.get_width();
        int height = levelShape.get_height();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        GridPoint2 coordinates = new GridPoint2(x, y);
        while (cellIsEmpty(coordinates)) {
            x = random.nextInt(width);
            y = random.nextInt(height);
            coordinates = new GridPoint2(x, y);
        }
        return coordinates;
    }
    private boolean cellIsEmpty(GridPoint2 coordinates) {
        for (GameEntity entity : listEntities) {
            if (entity.getCoordinates().equals(coordinates)) {
                return true;
            }
        }
        return false;
    }
    private TankEntity generateTank() {
        GridPoint2 coordinates = generateCoordinates();
        Direction[] listValuesDirection = Direction.values();
        int numberDirection = random.nextInt(listValuesDirection.length);
        return new TankEntity(coordinates, listValuesDirection[numberDirection]);
    }

    private TreeEntity generateTree() {
        GridPoint2 coordinates = generateCoordinates();
        return new TreeEntity(coordinates);
    }

    @Override
    public PlayerLogicLevel generatePlayerLogicLevel() {
        generateTanks();
        generateTrees();
        generatePlayerObject();
        return new PlayerLogicLevel(new LogicLevel(listEntities), playerTank);
    }
}