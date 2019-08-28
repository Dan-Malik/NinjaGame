package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
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

public class LevelScreen extends ScreenAdapter {
    Texture menuBackground;
    Texture logo;
    SpriteBatch sb;
    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    float topMargin = screenHeight/20;
    float leftMargin = screenWidth/26;


    Stage stage;
    Label outputLabel;
    Skin skin;
    ///

    private void generateButtonRows(int numberOfLevels){
        float buttonGap = screenWidth/26;
        float buttonWidth = screenWidth/13;
        float buttonHeight = screenHeight/10;

        double numberOfRows = Math.ceil((double)numberOfLevels/8);

        //Loop through each row
        for(int i=1;i<=numberOfRows;i++){
            //Each cell of the row
            for(int j=1;j<=8;j++){
                String cellValue = String.valueOf((i-1)*8+j);
                TextButton button = new TextButton(cellValue,skin,"default");
                button.setHeight(buttonHeight);
                button.setWidth(buttonWidth);
                button.setPosition(leftMargin+(j-1)*(buttonWidth+buttonGap), screenHeight-(i* (2*buttonGap) + buttonHeight));
                stage.addActor(button);
            }
        }
    }

    @Override
    public void show() {

        ///
        ///

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skins/uiskin.json"));

        generateButtonRows(24);

//        final TextButton level1 = new TextButton("1", skin, "default");
//        level1.setHeight(Gdx.graphics.getHeight()/10);
//        level1.setWidth(Gdx.graphics.getWidth()/12);
//        level1.setPosition(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/2-level1.getHeight()/2);
//
//        final TextButton level2 = new TextButton("2", skin, "default");
//        level2.setHeight(Gdx.graphics.getHeight()/10);
//        level2.setWidth(Gdx.graphics.getWidth()/12);
//        level2.setPosition(Gdx.graphics.getWidth() /2, Gdx.graphics.getHeight()/2-level1.getHeight()*2);

        /// BUTTON TEXT TOO SMALL, ESPECIALLY ON PHONE

//        level1.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y){
//                ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
//
//            }
//        });
//
//        stage.addActor(level1);
//        stage.addActor(level2);

        generateButtonRows(24);

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
        sb.draw(logo, (-Gdx.graphics.getWidth() - logo.getWidth()) / 2, -Gdx.graphics.getHeight() / 8*5, logo.getWidth(), logo.getHeight());
        ///

        stage.draw();

        ///

        sb.end();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            ((Game) Gdx.app.getApplicationListener()).setScreen(new PlayScreen());
        }
    }

    @Override
    public void dispose() {
        sb.dispose();
        menuBackground.dispose();
    }
}
