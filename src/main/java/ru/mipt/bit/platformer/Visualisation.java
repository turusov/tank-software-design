package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.util.GdxGameUtils;

public class Visualisation {

    private final Texture texture;
    private final TextureRegion textureRegion;
    private final Rectangle rectangle;

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    public Visualisation(String texturePath) {
        this.texture = new Texture(texturePath);
        this.textureRegion = new TextureRegion(this.texture);
        this.rectangle = GdxGameUtils.createBoundingRectangle(this.textureRegion);
    }
}

