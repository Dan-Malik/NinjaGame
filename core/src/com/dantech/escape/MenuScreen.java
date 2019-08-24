package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.TimeUtils;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.Constants;
import com.dantech.escape.utilities.DrawingUtil;

public class MenuScreen extends ScreenAdapter {
    Texture menuBackground;
    Texture logo;
    Texture playBtn;
    Texture settingsBtn;
    Texture questionBtn;
    Texture bgNinja;
    SpriteBatch sb;
    float logoWidth;
    float logoHeight;

    @Override
    public void show(){
        menuBackground = new Texture(Constants.MENU_BG + ".png");
        logo = new Texture(Constants.LOGO + ".png");
        playBtn = new Texture(Constants.MENU_PLAY_BTN + ".png");
        settingsBtn = new Texture(Constants.MENU_SETTINGS_BTN +".png");
        questionBtn = new Texture(Constants.MENU_QUESTION_BTN +".png");
//        bgNinja = new Texture(Constants.STANDING_RIGHT + ".png");
        sb = new SpriteBatch();
        logoWidth = Gdx.graphics.getWidth()/4*3;
        logoHeight = (logoWidth/logo.getWidth())*logo.getHeight();


    }

    @Override
    public void render(float delta){
        pulsateLogo();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.begin();
        sb.draw(menuBackground,0,0,Gdx.graphics.getWidth()*1.2f,Gdx.graphics.getHeight()*1.2f);

//        sb.draw(bgNinja,0,-Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/2,1.33333f*Gdx.graphics.getWidth()/2);

        sb.draw(logo,(Gdx.graphics.getWidth() - logoWidth)/2,Gdx.graphics.getHeight()/2,logoWidth,logoHeight);


        sb.draw(playBtn, Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/4f, Gdx.graphics.getHeight()/6,Gdx.graphics.getHeight()/6);
        sb.draw(settingsBtn, Gdx.graphics.getWidth()/2f- Gdx.graphics.getHeight()/12,Gdx.graphics.getHeight()/4f, Gdx.graphics.getHeight()/6,Gdx.graphics.getHeight()/6);
        sb.draw(questionBtn, Gdx.graphics.getWidth()/4f*3 - Gdx.graphics.getHeight()/6,Gdx.graphics.getHeight()/4f, Gdx.graphics.getHeight()/6,Gdx.graphics.getHeight()/6);
        sb.end();
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
        }
    }

    public void pulsateLogo(){
        if(TimeUtils.nanosToMillis(TimeUtils.nanoTime())%3000>1500){
            logoWidth *= 1.0003;
            logoHeight *=1.0003;
        } else {
            Gdx.app.log("","");
            logoHeight *= 0.9997f;
            logoWidth *= 0.9997f;
        }
    }

    @Override
    public void dispose(){
        sb.dispose();
        menuBackground.dispose();
        logo.dispose();
    }
}
