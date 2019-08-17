package com.dantech.escape.entities;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.dantech.escape.utilities.Assets;

public class Platform {

    float top;
    float bottom;
    float left;
    float right;
    boolean moving;


    public Platform(float left, float top, float width, float height, boolean moving) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.moving = moving;
    }

    public void render(SpriteBatch sb) {
        float width = right - left;
        float height = top - bottom;
        Assets.instance.platformAssets.platformNinePatch1.draw(sb, left - 1, bottom - 1, width + 2, height + 2);
    }

}