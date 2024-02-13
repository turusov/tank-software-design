package ru.mipt.bit.platformer.Interfaces;

import java.util.List;

public interface Action {
    public void apply(ModelObject object, float deltaTime);
    public boolean isPossibleDoAction(ModelObject object, List<ModelObject> objects);
}
