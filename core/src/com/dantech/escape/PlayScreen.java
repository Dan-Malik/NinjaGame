package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.Constants;
import com.dantech.escape.utilities.FollowCam;
import com.dantech.escape.utilities.LevelGenerator;

class PlayScreen extends ScreenAdapter {

    public static final String TAG = PlayScreen.class.getName();

    Level level;
    SpriteBatch sb;
    ExtendViewport viewport;

    private FollowCam followCam;

    @Override
    public void show(){

        Assets.instance.init();
        sb = new SpriteBatch();
        level  = LevelGenerator.generate(1,viewport);
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        followCam = new FollowCam(viewport.getCamera(), level.player);
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
    }

    @Override
    public void dispose(){
        Assets.instance.dispose();
    }

    @Override
    public void render(float delta){
        level.update(delta);
        viewport.apply();

        followCam.update();

        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.setProjectionMatrix(viewport.getCamera().combined);
        level.render(sb);

    }

}
