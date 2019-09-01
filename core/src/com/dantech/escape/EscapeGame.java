package com.dantech.escape;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.dantech.escape.utilities.LevelGenerator;

public class EscapeGame extends Game {

    @Override
    public void create() {

        //Check save data exists, if not create file

        FileHandle file = Gdx.files.local("gameprefs/prefs.json");
        if (!file.exists()) {
            file.writeString("{\n" +
                    "  \"levelUnlocked\": 1,\n" +
                    "  \"sound\": true\n" +
                    "}", false);
        }


        //If game state hasn't been saved, create default settings
//		if(PreferenceManager.getLevelsUnlocked() == 0){
//			PreferenceManager.setMusicOn();
//			PreferenceManager.setSoundEffectsOn();
//			PreferenceManager.setLevelUnlocked(1);
//			PreferenceManager.showAllPrefs();
//		}

        setScreen(new LevelScreen());
    }

}
