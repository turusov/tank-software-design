package ru.mipt.bit.platformer.Entities;

public class LevelShape {
    private final int _height;
    private final int _width;

    public LevelShape(int _height, int _width) {
        this._height = _height;
        this._width = _width;
    }

    public int get_height() {
        return _height;
    }

    public int get_width() {
        return _width;
    }
}
