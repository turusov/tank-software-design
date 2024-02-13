package ru.mipt.bit.platformer.Strategy;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Controllers.Direction;
import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Models.TankModel;
import ru.mipt.bit.platformer.Models.TreeModel;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelFromFile implements Strategy {
    private String filepath;
    private List<GameEntity> entities;
    private Integer width = 0;
    private Integer height = 0;

    public LevelFromFile(String pathToFile) {
        this.filepath = pathToFile;
        this.entities = new ArrayList<>();
    }

    @Override
    public List<GameEntity> generate() {
        parse(filepath);
        return entities;
    }

    private void parse(String filepath) {
        try (Scanner scanner = new Scanner(Paths.get(filepath))) {
            String line;
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                width = line.length();
                for (int x = 0; x < width; x++) {
                    char symbol = line.charAt(x);
                    switch (symbol) {
                        case '_':
                            break;
                        case 'T':
                            entities.add(new TreeModel(new GridPoint2(x, height)));
                            break;
                        case 'P':
                            entities.add(new TankModel(new GridPoint2(x, height), Direction.UP));
                            break;
                    }
                }
                height++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}