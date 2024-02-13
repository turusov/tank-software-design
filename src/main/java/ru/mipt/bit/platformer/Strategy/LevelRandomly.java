package ru.mipt.bit.platformer.Strategy;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Models.TankModel;
import ru.mipt.bit.platformer.Models.TreeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LevelRandomly implements Strategy {
    private List<GameEntity> listObjects;
    private int treesNum;

    private Random random;

    public LevelRandomly(int numbersObjects) {
        this.listObjects = new ArrayList<>();
        this.random = new Random();
        this.treesNum = numbersObjects-1;
    }

    @Override
    public List<GameEntity> generate() {
        listObjects.add(generateTank());
        for (int i = 0; i < treesNum; i++) {
            listObjects.add(generateTree());
        }
        return listObjects;
    }


    private GridPoint2 generateCoordinates() {
        int bound = 5;
        int x = random.nextInt(bound);
        int y = random.nextInt(bound);
        GridPoint2 coordinates = new GridPoint2(x, y);
        return coordinates;
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
}