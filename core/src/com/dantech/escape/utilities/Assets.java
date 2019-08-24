package com.dantech.escape.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable, AssetErrorListener {

    //Singleton class containing sprite assets

    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    public EscapeAssets escapeAssets;

    public HazardAssets hazardAssets;

    public PlatformAssets platformAssets;

    private AssetManager assetManager;

    private Assets() {
    }

    public void init() {
        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        escapeAssets = new EscapeAssets(atlas);
        platformAssets = new PlatformAssets(atlas);
        hazardAssets = new HazardAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class EscapeAssets {

        public final AtlasRegion standingRight;
        public final AtlasRegion standingLeft;
        public final AtlasRegion jumpingRight;
        public final AtlasRegion jumpingLeft;
        public final AtlasRegion walkingRight;
        public final AtlasRegion walkingLeft;

        public final Animation walkingLeftAnimation;
        public final Animation walkingRightAnimation;

        public final AtlasRegion door;

        public final AtlasRegion pixel;



        public EscapeAssets(TextureAtlas atlas) {
            standingRight = atlas.findRegion(Constants.STANDING_RIGHT);
            standingLeft = atlas.findRegion(Constants.STANDING_LEFT);
            jumpingRight = atlas.findRegion(Constants.JUMPING_RIGHT);
            jumpingLeft = atlas.findRegion(Constants.JUMPING_LEFT);
            walkingRight = atlas.findRegion(Constants.WALKING_RIGHT_2);
            walkingLeft = atlas.findRegion(Constants.WALKING_LEFT_2);

            door = atlas.findRegion(Constants.DOOR);
            pixel = atlas.findRegion("pixel");

            Array<AtlasRegion> walkingLeftSprites = new Array<AtlasRegion>();

            walkingLeftSprites.add(atlas.findRegion(Constants.WALKING_LEFT_2));
            walkingLeftSprites.add(atlas.findRegion(Constants.WALKING_LEFT_1));
            walkingLeftSprites.add(atlas.findRegion(Constants.WALKING_LEFT_2));
            walkingLeftSprites.add(atlas.findRegion(Constants.WALKING_LEFT_3));

            walkingLeftAnimation = new Animation(Constants.WALK_LOOP_TIME, walkingLeftSprites, Animation.PlayMode.LOOP);

            Array<AtlasRegion> walkingRightSprites = new Array<AtlasRegion>();

            walkingRightSprites.add(atlas.findRegion(Constants.WALKING_RIGHT_2));
            walkingRightSprites.add(atlas.findRegion(Constants.WALKING_RIGHT_1));
            walkingRightSprites.add(atlas.findRegion(Constants.WALKING_RIGHT_2));
            walkingRightSprites.add(atlas.findRegion(Constants.WALKING_RIGHT_3));

            walkingRightAnimation = new Animation(Constants.WALK_LOOP_TIME, walkingRightSprites, Animation.PlayMode.LOOP);


        }
    }

    public class HazardAssets {
        public final Animation burningFlame;
        public final AtlasRegion fire1;
        public final AtlasRegion fire2;
        public final AtlasRegion spike;



        public HazardAssets(TextureAtlas atlas){

            fire1 = atlas.findRegion(Constants.FIRE_1);
            fire2 = atlas.findRegion(Constants.FIRE_2);
            spike = atlas.findRegion(Constants.SPIKE);


            Array<AtlasRegion> burningFlameSprites = new Array<AtlasRegion>();

            burningFlameSprites.add(fire1);
            burningFlameSprites.add(fire2);
            burningFlameSprites.add(atlas.findRegion(Constants.FIRE_3));

            burningFlame = new Animation(Constants.FIRE_LOOP_TIME,burningFlameSprites, Animation.PlayMode.LOOP);
        }
    }

    public class PlatformAssets {

        public final NinePatch platformNinePatch1;
        public final NinePatch platformNinePatch2;


        public PlatformAssets(TextureAtlas atlas) {

            int edge = Constants.PLATFORM_EDGE_SIZE;

            AtlasRegion region1 = atlas.findRegion(Constants.PLATFORM_SPRITE_1);
            platformNinePatch1 = new NinePatch(region1, edge, edge, edge, edge);

            AtlasRegion region2 = atlas.findRegion(Constants.PLATFORM_SPRITE_2);
            platformNinePatch2 = new NinePatch(region2, edge, edge, edge, edge);

        }
    }
}