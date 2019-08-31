package com.dantech.escape.entities;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.dantech.escape.utilities.Assets;

public class Platform {

    float top;
    float bottom;
    float left;
    float right;
    long creationTime;
    long travelTime;
    float leftBound;
    float rightBound;
    float traversalDistance;
    float width;
    float height;
    public boolean moving;


    public Platform(float left, float top, float width, float height, boolean moving) {
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
        this.moving = moving;
        this.creationTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }

    public float getLeftBound() {
        return leftBound;
    }

    public void setLeftBound(float leftBound) {
        this.leftBound = leftBound;
    }

    public float getRightBound() {
        return rightBound;
    }

    public void setRightBound(float rightBound) {
        this.rightBound = rightBound;
    }

    public long getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(long travelTime) {
        this.travelTime = travelTime;
    }

    public void render(SpriteBatch sb) {
        width = right - left;
        height = top - bottom;

            Assets.instance.platformAssets.platformNinePatch1.draw(sb, left - 1, bottom - 1, width + 2, height + 2);
    }

}