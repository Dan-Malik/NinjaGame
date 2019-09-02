package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dantech.escape.utilities.Assets;
import com.dantech.escape.utilities.Constants;
import com.dantech.escape.utilities.SoundManager;

public class LevelScreen extends ScreenAdapter {
    Texture menuBackground;
    Texture logo;
    SpriteBatch sb;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    float topMargin = screenHeight / 20;
    float leftMargin = screenWidth / 26;


    Stage stage;
    Label outputLabel;
    Skin skin;
    ///

    private void generateButtonRows(int numberOfLevels) {
        float buttonGap = screenWidth / 26;
        float buttonWidth = screenWidth / 13;
        float buttonHeight = screenHeight / 10;

        double numberOfRows = Math.ceil((double) numberOfLevels / 8);

        //Loop through each row
        for (int i = 1; i <= numberOfRows; i++) {
            //Each cell of the row
            for (int j = 1; j <= 8; j++) {
                final int levelNumber = (i - 1) * 8 + j;
                if(levelNumber<=PreferenceManager.getLevelsUnlocked()){
                String cellValue = String.valueOf(levelNumber);
                TextButton button = new TextButton(cellValue, skin, "default");
                button.setHeight(buttonHeight);
                button.setWidth(buttonWidth);
                button.setPosition(leftMargin + (j - 1) * (buttonWidth + buttonGap), screenHeight - (i * (2 * buttonGap) + buttonHeight));
                button.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if (PreferenceManager.getSoundSettings()) {
                            SoundManager.playClickSound();
                            if(SoundManager.checkThemePlaying()){
                                SoundManager.stopThemeMusic();
                                SoundManager.disposeThemeMusic();
                            }
//
//                            SoundManager.disposeClickSound();
                        }
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(levelNumber));

                    }
                });

                stage.addActor(button);
            }
            }
        }
    }

    @Override
    public void show() {
        /////////
        //MUSIC//
        /////////
        if(PreferenceManager.getSoundSettings()) {
            try {
            SoundManager.playThemeMusic();
            SoundManager.initialiseClickSound();
            } catch (Exception ex) {
            SoundManager.initialiseThemeMusic();
            SoundManager.playThemeMusic();
            SoundManager.initialiseClickSound();
            }
        }
        ///
        ///

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        ///

        generateButtonRows(PreferenceManager.getLevelsUnlocked());

        /// BUTTON TEXT TOO SMALL, ESPECIALLY ON PHONE

//        level1.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y){
//                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
//
//            }
//        });


        generateButtonRows(PreferenceManager.getLevelsUnlocked());

        Gdx.input.setInputProcessor(stage);

        ///
        ///


        menuBackground = new Texture(Constants.MENU_BG + ".png");
        logo = new Texture(Constants.LOGO + ".png");

        sb = new SpriteBatch();

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        sb.begin();
        sb.draw(menuBackground, 0, 0, Gdx.graphics.getWidth() * 1.2f, Gdx.graphics.getHeight() * 1.2f);
        sb.draw(logo, (-Gdx.graphics.getWidth() - logo.getWidth()) / 2, -Gdx.graphics.getHeight() / 8 * 5, logo.getWidth(), logo.getHeight());
        ///

        stage.draw();

        ///

        sb.end();

    }

    @Override
    public void dispose() {
        sb.dispose();
        menuBackground.dispose();
    }
}
