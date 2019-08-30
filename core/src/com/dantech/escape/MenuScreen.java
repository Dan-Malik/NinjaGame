package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dantech.escape.utilities.Constants;

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

    ///
    Stage stage;
    Label outputLabel;
    Skin skin;
    ///

    @Override
    public void show() {

        ///
        ///

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));


        final TextButton playButton = new TextButton("PLAY", skin, "default");
        playButton.setHeight(Gdx.graphics.getHeight()/10);
        playButton.setWidth(Gdx.graphics.getWidth()/3);
        playButton.setPosition(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/2-playButton.getHeight()/2);

        final TextButton infoButton = new TextButton("INFO", skin, "default");
        infoButton.setHeight(Gdx.graphics.getHeight()/10);
        infoButton.setWidth(Gdx.graphics.getWidth()/3);
        infoButton.setPosition(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/2-playButton.getHeight()*2);

        /// BUTTON TEXT TOO SMALL, ESPECIALLY ON PHONE

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(1));

            }
        });

        stage.addActor(playButton);
        stage.addActor(infoButton);

        Gdx.input.setInputProcessor(stage);

        ///
        ///


        menuBackground = new Texture(Constants.MENU_BG + ".png");
        logo = new Texture(Constants.LOGO + ".png");
        playBtn = new Texture(Constants.MENU_PLAY_BTN + ".png");
        settingsBtn = new Texture(Constants.MENU_SETTINGS_BTN + ".png");
        questionBtn = new Texture(Constants.MENU_QUESTION_BTN + ".png");
//        bgNinja = new Texture(Constants.STANDING_RIGHT + ".png");
        sb = new SpriteBatch();
        logoWidth = Gdx.graphics.getWidth() / 4 * 3;
        logoHeight = (logoWidth / logo.getWidth()) * logo.getHeight();

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        pulsateLogo();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        sb.begin();
        sb.draw(menuBackground, 0, 0, Gdx.graphics.getWidth() * 1.2f, Gdx.graphics.getHeight() * 1.2f);
//        sb.draw(bgNinja,0,-Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/2,1.33333f*Gdx.graphics.getWidth()/2);
//        sb.draw(playBtn, Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 4f, Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 6);
//        sb.draw(settingsBtn, Gdx.graphics.getWidth() / 2f - Gdx.graphics.getHeight() / 12, Gdx.graphics.getHeight() / 4f, Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 6);
//        sb.draw(questionBtn, Gdx.graphics.getWidth() / 4f * 3 - Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 4f, Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 6);
        sb.draw(logo, (Gdx.graphics.getWidth() - logoWidth) / 2, Gdx.graphics.getHeight() / 8*5, logoWidth, logoHeight);
        sb.draw(questionBtn, Gdx.graphics.getWidth() / 4f * 3 - Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 4f, Gdx.graphics.getHeight() / 6, Gdx.graphics.getHeight() / 6);

        ///

        stage.draw();

        ///

        sb.end();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(1));
        }
    }

    public void pulsateLogo() {
        if (TimeUtils.nanosToMillis(TimeUtils.nanoTime()) % 3000 > 1500) {
            logoWidth *= 1.0003;
            logoHeight *= 1.0003;
        } else {
            logoHeight *= 0.9997f;
            logoWidth *= 0.9997f;
        }
    }

    @Override
    public void dispose() {
        sb.dispose();
        menuBackground.dispose();
        logo.dispose();
    }
}
