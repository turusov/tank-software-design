package ru.mipt.bit.platformer.Graphics;

import com.badlogic.gdx.graphics.g2d.Batch;
import ru.mipt.bit.platformer.util.TileMovement;

public interface GraphicsEntity {
    void dispose();
    void render(Batch batch);
    void calculateInterpolatedScreenCoordinates(TileMovement tileMovement);
}
