package com.dantech.escape.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dantech.escape.utilities.DrawingUtil;

public class Decoration {

    Vector2 position;

    TextureRegion region;

    public Decoration(Vector2 position){
        this.position = position;
    }

    public Decoration (Vector2 position, TextureRegion region){
        this.position = position;
        this.region = region;
    }

    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public void render(SpriteBatch sb){
        DrawingUtil.drawTextureRegion(sb,region,position.x, position.y);

    }
}
