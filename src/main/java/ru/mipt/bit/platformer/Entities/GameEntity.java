package ru.mipt.bit.platformer.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.Direction;

public interface GameEntity {
    GridPoint2 getCoordinates();
    void drawEntityTexture(Batch batch);
    void dispose();
}
