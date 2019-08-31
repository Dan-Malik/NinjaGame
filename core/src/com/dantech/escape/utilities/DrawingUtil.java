package com.dantech.escape.utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DrawingUtil {

    public static void drawTextureRegion(SpriteBatch sb, TextureRegion region, float x, float y) {
        sb.draw(
                region.getTexture(),
                x,
                y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    //Strictly for gear
    public static void drawTextureRegion(SpriteBatch sb, TextureRegion region, float x, float y, float scaleFactor, float rotation) {
        sb.draw(
                region.getTexture(),
                x,
                y,
                (region.getRegionWidth()*scaleFactor)/2,
                (region.getRegionHeight()*scaleFactor)/2,
                region.getRegionWidth()*scaleFactor,
                region.getRegionHeight()*scaleFactor,
                1,
                1,
                rotation,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }
}
