package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Tree {
    private final GridPoint2 coordinates;
    private Visualisation visualisation;
    public Tree(Visualisation visualisation, GridPoint2 coordinates){
        this.visualisation = visualisation;
        this.coordinates = coordinates;
    }

    public GridPoint2 getCoordinates() {
        return coordinates;
    }

    public Visualisation getVisualisation() {
        return visualisation;
    }
}
