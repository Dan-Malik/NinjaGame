package com.dantech.escape.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.DrawingUtil;

public class Hazard {

    Vector2 position;
    ShapeRenderer renderer;
    float top;
    float bottom;
    float left;
    float right;
    public boolean animated = false;
    float width = left-right;
    float height = top-bottom;
    TextureRegion region = Assets.instance.hazardAssets.spike;

    public Hazard(Vector2 position){
        this.position = position;
        this.left = position.x;
        this.right = position.x + region.getRegionWidth()-1;
        this.bottom = position.y;
        this.top = position.y + region.getRegionHeight()-1;
    }

    public void render(SpriteBatch sb){
        DrawingUtil.drawTextureRegion(sb,region,position.x, position.y);
    }

    public void update(float delta) {
    }
}
