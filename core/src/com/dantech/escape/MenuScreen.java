package com.dantech.escape;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.dantech.escape.utilities.Constants;

public class MenuScreen extends ScreenAdapter {
    Texture menuBackground;
    Texture logo;
    SpriteBatch sb;
    ShapeRenderer renderer;
    float logoWidth;
    float logoHeight;

    @Override
    public void show(){
        menuBackground = new Texture(Constants.MENU_BG + ".png");
        logo = new Texture(Constants.LOGO + ".png");
        sb = new SpriteBatch();
        renderer = new ShapeRenderer();
        logoWidth = Gdx.graphics.getWidth()/2;
        logoHeight = (logoWidth/logo.getWidth())*logo.getHeight();
    }

    @Override
    public void render(float delta){
        pulsateLogo();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(menuBackground,0,0,Gdx.graphics.getWidth()*1.2f,Gdx.graphics.getHeight()*1.2f);
        sb.draw(logo,Gdx.graphics.getWidth()/2 - logoWidth/2,Gdx.graphics.getHeight()/2,logoWidth,logoHeight);
        sb.end();
    }

    public void pulsateLogo(){
        if(TimeUtils.nanosToMillis(TimeUtils.nanoTime())%3000>1500){
            logoWidth *= 1.0001;
            logoHeight *=1.0001;
        } else {
            Gdx.app.log("","");
            logoHeight *= 0.9999f;
            logoWidth *= 0.9999f;
        }
    }

    @Override
    public void dispose(){
        sb.dispose();
        menuBackground.dispose();
        renderer.dispose();
        logo.dispose();
    }
}
