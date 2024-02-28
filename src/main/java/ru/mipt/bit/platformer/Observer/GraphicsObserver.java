package ru.mipt.bit.platformer.Observer;

import ru.mipt.bit.platformer.Entities.GameEntity;
import ru.mipt.bit.platformer.Graphics.GraphicsLevel;

public class GraphicsObserver implements Observer{
    private final GraphicsLevel graphicsLevel;

    public GraphicsObserver(GraphicsLevel graphicsLevel) {
        this.graphicsLevel = graphicsLevel;
    }
    @Override
    public void onRemoveEntity(GameEntity gameEntity) {
        graphicsLevel.removeGraphicsEntity(gameEntity);
    }
    @Override
    public void onAddEntity(GameEntity gameEntity) {
        graphicsLevel.addGraphicsEntity(gameEntity);
    }

}
