package ru.mipt.bit.platformer.Generators;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.PlayerLevel;
import ru.mipt.bit.platformer.Entities.LevelShape;
import ru.mipt.bit.platformer.Entities.TankModel;
import ru.mipt.bit.platformer.Entities.TreeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelRandomly implements LevelGenerator {
    private List<GameEntity> listEntities;

    private final int treeNum;
    private final int tankNum;
    private final int _width;
    private final int _height;
    private TankModel playerTank;
    private Random random;

    public LevelRandomly(LevelShape levelShape, int treeNum, int tankNum) {
        this.listEntities = new ArrayList<>();
        this.random = new Random();
        this.tankNum = tankNum;
        this.treeNum = treeNum;
        this._height = levelShape.get_height();
        this._width = levelShape.get_width();
        System.out.println("LevelRandomly instanced");
    }
    private void generatePlayerObject(){
        playerTank = new TankModel(generateCoordinates(), Direction.UP);
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
        int x = random.nextInt(_width);
        int y = random.nextInt(_height);
        GridPoint2 coordinates = new GridPoint2(x, y);
        while (cellIsEmpty(coordinates)) {
            x = random.nextInt(_width);
            y = random.nextInt(_height);
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
    private TankModel generateTank() {
        GridPoint2 coordinates = generateCoordinates();
        Direction[] listValuesDirection = Direction.values();
        int numberDirection = random.nextInt(listValuesDirection.length);
        return new TankModel(coordinates, listValuesDirection[numberDirection]);
    }

    private TreeModel generateTree() {
        GridPoint2 coordinates = generateCoordinates();
        return new TreeModel(coordinates);
    }

    @Override
    public LevelDesc generateLevelDesc() {
        generateLevel();
        return new LevelDesc(new PlayerLevel(listEntities), playerTank);
    }
    private void generateLevel(){
        generateTanks();
        generateTrees();
        generatePlayerObject();
    }
}