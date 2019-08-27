package com.dantech.escape.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.dantech.escape.utilities.Assets;


public class SolidPlatform extends Platform {

    float width;
    float height;
    float initialLeft;

    public SolidPlatform(float left, float top, float width, float height, boolean moving) {
        super(left, top, width, height, moving);
        this.initialLeft = this.left;
    }

    public void render(SpriteBatch sb) {
        width = right - left;
        height = top - bottom;

        if(moving) {
            updatePlatformPosition();
        }
        Assets.instance.platformAssets.platformNinePatch1.draw(sb, left - 1, bottom - 1, width + 2, height + 2);
    }

    private void updatePlatformPosition() {
        this.traversalDistance = leftBound - rightBound;
        long travelTimePassed = (TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - creationTime)% travelTime;
        float xDisplacement;

        if(travelTimePassed < travelTime/2){
            xDisplacement = (float)(-(traversalDistance * (2*(double)travelTimePassed /travelTime)));
            Gdx.app.log("pos",String.valueOf(xDisplacement));
        } else {
            xDisplacement = (float)-(2*traversalDistance - (traversalDistance * (2*(double)travelTimePassed /travelTime)));;
            Gdx.app.log("neg",String.valueOf(xDisplacement));
        }

        this.left = initialLeft + xDisplacement;
        this.right = initialLeft + this.width + xDisplacement;
    }
}
