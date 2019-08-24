package com.dantech.escape.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.DrawingUtil;

public class Door {
    Vector2 position;
    float top;
    float bottom;
    float left;
    float right;
    float width = left-right;
    float height = top-bottom;
    TextureRegion region = Assets.instance.escapeAssets.door;

    public Door(Vector2 position){
        this.position = position;
        this.left = position.x;
        this.right = position.x + region.getRegionWidth()-1;
        this.bottom = position.y;
        this.top = position.y + region.getRegionHeight()-1;
    }

    public void render(SpriteBatch sb){
        DrawingUtil.drawTextureRegion(sb,region,position.x, position.y);
    }
}
