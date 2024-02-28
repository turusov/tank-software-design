package ru.mipt.bit.platformer.Generators;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Entities.LogicLevel;
import ru.mipt.bit.platformer.Entities.TankEntity;
import ru.mipt.bit.platformer.Entities.TreeEntity;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelFromFile implements LevelGenerator {
    private String filepath;
    private List<GameEntity> entities;
    private int _width = 5;
    private int _height = 5;
    private GameEntity playerEntity;

    public LevelFromFile(String filepath) {
        this.filepath = filepath;
        this.entities = new ArrayList<>();
        parse(filepath);
    }

    private void parse(String filepath) {
        try (Scanner scanner = new Scanner(Paths.get(filepath))) {
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                _width = line.length();
                for (int x = 0; x < _width; x++) {
                    char symbol = line.charAt(x);
                    switch (symbol) {
                        case '_':
                            break;
                        case 'T':
                            entities.add(new TreeEntity(new GridPoint2(x, _height)));
                            break;
                        case 'P':
                            entities.add(new TankEntity(new GridPoint2(x, _height), Direction.UP));
                            break;
                    }
                }
                _height++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public PlayerLogicLevel generatePlayerLogicLevel() {
        return new PlayerLogicLevel(new LogicLevel(entities), playerEntity);
    }
}