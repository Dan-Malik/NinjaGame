package com.dantech.escape.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.DrawingUtil;

public class Fire extends Hazard {

    long loopStartTime;
    public boolean animated = true;
    TextureRegion region = Assets.instance.hazardAssets.fire1;

    public Fire(Vector2 position) {
        super(position);
        this.region = Assets.instance.hazardAssets.fire1;
        this.loopStartTime = TimeUtils.nanoTime();
    }

    public void update(float delta){
        float loopCurrentTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - loopStartTime);
        this.region = (TextureRegion) Assets.instance.hazardAssets.burningFlame.getKeyFrame(loopCurrentTime);

    }

    public void render(SpriteBatch sb){
        DrawingUtil.drawTextureRegion(sb,region,position.x, position.y);
    }
}
