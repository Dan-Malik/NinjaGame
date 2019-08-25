package com.dantech.escape.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.dantech.escape.utilities.Assets;

public class Spike extends Hazard {

    TextureRegion region = Assets.instance.hazardAssets.spike;

    public Spike(Vector2 position) {
        super(position);
    }
}
