package ru.mipt.bit.platformer.Controllers;

import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.LevelShape;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CollisionHandler {
    private final int busy = 1;
    private final int free = 0;
    private final int _width;
    private final int _height;
    private final Deque<int[]> toUpdateQueue;
    private final List<GameEntity> entities;
    private int[][] field;

    public CollisionHandler(List<GameEntity> entites, LevelShape levelDesc) {
        this.entities = entites;
        _width = levelDesc.get_width();
        _height = levelDesc.get_height();
        field = new int[_width][_height];
        fillingCells();
        toUpdateQueue = new ArrayDeque<>();
    }
    private void fillingCells() {
        Arrays.stream(field).forEach(row -> Arrays.fill(row, free));
        for (GameEntity gameEntity : entities) {
            int x = gameEntity.getCoordinates().x;
            int y = gameEntity.getCoordinates().y;
            field[x][y] = busy;
        }
    }

    public void update(){
        while (!toUpdateQueue.isEmpty()){
            int[] position;
            position = toUpdateQueue.pop();
            field[position[0]][position[1]] = free;
        }
    }
    public boolean ifCollides(GameEntity gameEntity, Direction direction) {
        int to_x = direction.add(gameEntity.getCoordinates()).x;
        int to_y = direction.add(gameEntity.getCoordinates()).y;
        if (exitOutBoundsField(to_x, to_y))
            return false;
        boolean collides = field[to_x][to_y] == busy;
        if (!collides) {
            updateCellState(gameEntity, direction);
            return true;
        }
        return false;
    }

    private void updateCellState(GameEntity gameEntity, Direction direction) {
        int x = (gameEntity.getCoordinates().x);
        int y = (gameEntity.getCoordinates().y);
        int to_x = direction.add(gameEntity.getCoordinates()).x;
        int to_y = direction.add(gameEntity.getCoordinates()).y;
        field[to_x][to_y] = busy;
        toUpdateQueue.add(new int[]{x, y});
    }
    private boolean exitOutBoundsField(int x, int y) {
        return ifOutOfBound(x,_width) || ifOutOfBound(y, _height);
    }
    private boolean ifOutOfBound(int coordinate, int bound) {
        return coordinate >= bound || coordinate < 0;
    }
}
