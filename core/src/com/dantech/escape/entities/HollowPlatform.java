package com.dantech.escape.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dantech.escape.utilities.Assets;

public class HollowPlatform extends Platform {

    public HollowPlatform(float left, float top, float width, float height, boolean moving) {
        super(left, top, width, height, moving);
    }
    public void render(SpriteBatch sb) {
        float width = right - left;
        float height = top - bottom;
        Assets.instance.platformAssets.platformNinePatch2.draw(sb, left - 1, bottom - 1, width + 2, height + 2);
    }
}
