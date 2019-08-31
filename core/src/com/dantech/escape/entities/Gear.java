package com.dantech.escape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.DrawingUtil;

public class Gear extends Hazard{

    TextureRegion region = Assets.instance.hazardAssets.gear;
    float rotation = 0;
    float scalingFactor = 0.5f;
    float radius = (region.getRegionWidth()*scalingFactor)/2;
    Vector2 centre;
    Circle collisionCircle;

    public Gear(Vector2 position) {
        super(position);
        this.animated = true;
        this.centre = new Vector2(position.x + radius,position.y+radius);
    }

    public void render(SpriteBatch sb){
        this.rotation += 5;
        DrawingUtil.drawTextureRegion(sb,region,position.x, position.y,scalingFactor,rotation);

    }
}
